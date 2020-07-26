package com.javarush.task.task14.task1421;

/**
 * Created by aleksandr on 26.02.17.
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}
