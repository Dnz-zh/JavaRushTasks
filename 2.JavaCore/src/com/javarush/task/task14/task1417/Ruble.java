package com.javarush.task.task14.task1417;

/**
 * Created by aleksandr on 26.02.17.
 */
public class Ruble extends Money {

    public Ruble(double amount) {
        super(amount);
    }

    @Override
    public String getCurrencyName() {
        return "RUB";
    }
}
