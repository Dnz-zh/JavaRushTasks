package com.javarush.task.task26.task2604;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/* 
Для того, чтобы усовершенствовать ум, надо больше размышлять, чем заучивать
*/
public class Solution extends Thread {
    public static final String DEFAULT_JAVARUSH_THREAD_NAME = "JavaRushThread";

    private static final AtomicInteger createdThreadIndex = new AtomicInteger();
    private static final AtomicInteger aliveThreadIndex = new AtomicInteger();

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
    }

    private static final Logger log = Logger.getLogger(Solution.class.getName());

    private static volatile boolean debugLifecycle = true;

    public Solution() {
        this(DEFAULT_JAVARUSH_THREAD_NAME);
    }

    public Solution(String name) {
        super(name + "-" + createdThreadIndex.incrementAndGet());
        //System.out.println(name + " " + createdThreadIndex);
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                log.log(Level.SEVERE, "An error occurred in thread " + t.getName(), e);
            }
        });
    }

    public static void main(String[] args) {
        new Solution().start();
        new Solution().start();
        new Solution().start();
    }

    public void run() {
        boolean debug = debugLifecycle;
        if (debug) {
            log.log(Level.INFO, "Created " + getName());
        }
        try {
            aliveThreadIndex.incrementAndGet();
            //System.out.println(Thread.currentThread().getName() + " increment aliveThreadIndex " + aliveThreadIndex);
            log.log(Level.INFO, "Thread " + getName() + " in progress...");
            throw new RuntimeException("Oops " + getName());
        } finally {
            aliveThreadIndex.decrementAndGet();
            //System.out.println(Thread.currentThread().getName() + " decrement aliveThreadIndex " + aliveThreadIndex);
            if (debug) {
                log.log(Level.INFO, "Exiting " + getName());
            }
        }
    }

    public static int getThreadsCreated() {
        return createdThreadIndex.get();
    }

    public static int getThreadsAlive() {
        return aliveThreadIndex.get();
    }

    public static boolean isDebug() {
        return debugLifecycle;
    }

    public static void setDebug(boolean dl) {
        debugLifecycle = dl;
    }
}