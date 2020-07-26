package com.javarush.task.task25.task2511;

import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/
public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                /*
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < t.getName().length(); i++) {
                    sb.append("*");
                }
                */
                System.out.println(e.getMessage().replaceAll(
                    t.getName(),
                    t.getName().replaceAll(".", "*")
                    )
                );
            }
        };    //init handler here
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {
        /*
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++) {
                    System.out.println(i);
                }
                int i = 10 / 0;
            }
        };
        Solution solution = new Solution(timerTask);
        Thread thread = new Thread(solution);
        thread.start();
        */
    }
}