package com.javarush.task.task14.task1408;

/**
 * Created by aleksandr on 21.02.17.
 */
public abstract class Hen implements Country {

    public abstract int getCountOfEggsPerMonth();
    public String getDescription() {
        return "Я - курица.";
    }
}
