package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен.");
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException,
            ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    String user = message.getData();
                    if (user != null && !user.isEmpty() && !connectionMap.containsKey(user)) {
                        connectionMap.put(user, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return user;
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws
            IOException {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                if (!userName.equals(entry.getKey())) {
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws
            IOException, ClassNotFoundException {
            Message message;
            while (true) {
                message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    sendBroadcastMessage(new Message(
                        MessageType.TEXT,
                        userName.concat(": ").concat(message.getData())
                    ));
                } else {
                    ConsoleHelper.writeMessage("Получено не текстовое сообщение");
                }
            }
        }

        @Override
        public void run() {
            String newUserName = null;
            ConsoleHelper.writeMessage(
                "Установлено новое соедние с удалённым адресом "
                    + socket.getRemoteSocketAddress()
            );
            try(Connection connection = new Connection(socket)) {
                newUserName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, newUserName));
                sendListOfUsers(connection, newUserName);
                serverMainLoop(connection, newUserName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage(e.getMessage());
            } finally {
                if (newUserName != null) {
                    connectionMap.remove(newUserName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, newUserName));
                }
            }
            ConsoleHelper.writeMessage("Соединение с удалённым адресом закрыто");
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage(e.getMessage());
            }
        }
    }
}
