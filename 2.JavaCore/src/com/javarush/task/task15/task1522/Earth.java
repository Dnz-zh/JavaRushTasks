package com.javarush.task.task15.task1522;

/**
 * Created by aleksandr on 07.03.17.
 */
public class Earth implements Planet {

    private static Earth instance;

    private Earth() {
    }

    public static Earth getInstance() {
        if (instance == null) {
            instance = new Earth();
        }
        return instance;
    }
}
