package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends Client.SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(":")) {
                String[] messages = message.split(": ");
                SimpleDateFormat dateFormat = null;
                if ("дата".equalsIgnoreCase(messages[1].trim())) {
                    dateFormat = new SimpleDateFormat("d.MM.YYYY");
                } else if ("день".equalsIgnoreCase(messages[1].trim())) {
                    dateFormat = new SimpleDateFormat("d");
                } else if ("месяц".equalsIgnoreCase(messages[1].trim())) {
                    dateFormat = new SimpleDateFormat("MMMM");
                } else if ("год".equalsIgnoreCase(messages[1].trim())) {
                    dateFormat = new SimpleDateFormat("YYYY");
                } else if ("время".equalsIgnoreCase(messages[1].trim())) {
                    dateFormat = new SimpleDateFormat("H:mm:ss");
                } else if ("час".equalsIgnoreCase(messages[1].trim())) {
                    dateFormat = new SimpleDateFormat("H");
                } else if ("минуты".equalsIgnoreCase(messages[1].trim())) {
                    dateFormat = new SimpleDateFormat("m");
                } else if ("секунды".equalsIgnoreCase(messages[1].trim())) {
                    dateFormat = new SimpleDateFormat("s");
                }
                if (dateFormat != null) {
                    sendTextMessage("Информация для "
                        + messages[0].trim()
                        + ": "
                        + dateFormat.format(Calendar.getInstance().getTime())
                    );
                }
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int)(Math.random() * 100);
    }
}
