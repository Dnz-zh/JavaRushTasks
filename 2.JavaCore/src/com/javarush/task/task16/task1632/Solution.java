package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new Thread1());
        threads.add(new Thread2());
        threads.add(new Thread3());
        threads.add(new Thread4());
        threads.add(new Thread5());
    }

    public static void main(String[] args) {
    }

    public static class Thread1 extends Thread {
        @Override
        public void run() {
            while (true) {
            }
        }
    }

    public static class Thread2 extends Thread {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                }
                throw new InterruptedException();
            } catch (InterruptedException err) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class Thread3 extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    sleep(500);
                }
            } catch (InterruptedException err) {
            }
        }
    }

    public static class Thread4 extends Thread implements Message {

        @Override
        public void showWarning() {
            try {
                interrupt();
                join();
            } catch (InterruptedException err) {

            }
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
            }
        }
    }

    public static class Thread5 extends Thread {
        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String data;
            int summ = 0;
            try {
                while (true) {
                    data = reader.readLine();
                    if ("N".equals(data)) {
                        System.out.println(summ);
                        break;
                    }
                    summ += Integer.parseInt(data);
                }
            } catch (IOException e) {
            }
        }
    }
}