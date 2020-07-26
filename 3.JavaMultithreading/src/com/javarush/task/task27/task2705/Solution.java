package com.javarush.task.task27.task2705;

/* 
Второй вариант deadlock
*/
public class Solution {
    private final Object lock = new Object();

    public synchronized void firstMethod() {
        synchronized (lock) {
            doSomething();
        }
    }

    public void secondMethod() {
        synchronized (lock) {
            synchronized (this) {
                doSomething();
            }
        }
    }

    private void doSomething() {
    }

    public static void main(String[] args) {
        Solution solution1 = new Solution();
        Solution solution2 = new Solution();
        new Thread(solution1::secondMethod).start();
        new Thread(solution2::firstMethod).start();
    }
}