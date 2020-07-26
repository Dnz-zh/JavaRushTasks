package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/
public class Solution {

    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод
        for (Thread thread : threads) {
            switch (thread.getState()) {
                case NEW: {
                    thread.start();
                }
                break;
                case BLOCKED:
                case TIMED_WAITING:
                case WAITING: {
                    thread.interrupt();
                }
                break;
                case RUNNABLE: {
                    if (thread.isInterrupted()) {
                        System.out.println(thread.getPriority());
                    }
                }
                break;
                case TERMINATED: {
                    System.out.println(thread.getPriority());
                }
            }
        }
    }

    public static void main(String[] args) {
    }
}
