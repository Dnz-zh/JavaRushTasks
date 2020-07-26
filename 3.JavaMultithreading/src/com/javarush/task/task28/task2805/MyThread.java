package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    {
        int priority = atomicInteger.incrementAndGet();
        if (priority > getThreadGroup().getMaxPriority()) {
            if (priority > Thread.MAX_PRIORITY) {
                atomicInteger.set(1);
                priority = Thread.MIN_PRIORITY;
            } else {
                priority = getThreadGroup().getMaxPriority();
            }
        }
        setPriority(priority);
    }

    public MyThread() {
    }

    public MyThread(Runnable target) {
        super(target);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public MyThread(String name) {
        super(name);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }
}
