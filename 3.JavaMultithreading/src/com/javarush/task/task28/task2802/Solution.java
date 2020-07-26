package com.javarush.task.task28.task2802;


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    private static final AtomicInteger groupCount = new AtomicInteger(0);

    public static void main(String[] args) {
        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    public static class AmigoThreadFactory implements ThreadFactory {

        private final AtomicInteger threadCount = new AtomicInteger(0);
        private final ThreadFactory threadFactory = Executors.defaultThreadFactory();
        private int grpCnt;

        public AmigoThreadFactory() {
            grpCnt = Solution.groupCount.incrementAndGet();
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = threadFactory.newThread(r);
            String name = thread.getThreadGroup().getName() + "-pool-" + grpCnt + "-thread-" + threadCount.incrementAndGet();
            thread.setName(name);
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }
}
