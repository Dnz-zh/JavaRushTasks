package com.javarush.task.task26.task2607;

//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;

/*
Вежливость - это искусственно созданное хорошее настроение
*/
public class Solution {
    public static void main(String[] args) {
//        ExecutorService exec = Executors.newFixedThreadPool(10);
//        IntegerHolder holder = new IntegerHolder();
//        for (int i = 0; i < 10; i++) {
//            exec.submit(() -> {
//                for (int j = 0; j < 10; j++) {
//                    holder.set(holder.get() + j);
//                    System.out.println(Thread.currentThread().getName() + " " + holder.get());
//                }
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        exec.shutdown();
    }

    public static class IntegerHolder {
        private int value;

        synchronized public int get() {
            return value;
        }

        synchronized public void set(int value) {
            this.value = value;
        }
    }
}
