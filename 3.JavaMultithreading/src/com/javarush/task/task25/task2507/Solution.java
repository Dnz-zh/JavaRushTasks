package com.javarush.task.task25.task2507;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/* 
Работать в поте лица!
*/
public class Solution extends Thread {
    private static final int BUFFER_SIZE = 2000;    //2000 bytes
    private final Socket socket;
    private final InputStream in;

    public Solution(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    public void interrupt() {
        //implement logic here
        try {
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            super.interrupt();
        }
    }

    public void run() {
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else {
                    if (count > 0) {
                        //process buffer here
                    }
                }
            }
        } catch (IOException ignored) {
        }
    }

    public static void main(String[] args) {
        /*
        try {
            InetAddress inetAddress = InetAddress.getByName("92.42.4.186");
            Socket socket = new Socket(inetAddress, 8006);
            Solution solution = new Solution(socket);
            solution.start();
            Thread.sleep(100);
            solution.interrupt();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        */
    }
}
