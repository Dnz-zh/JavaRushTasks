package com.javarush.task.task15.task1530;

/**
 * Created by aleksandr on 08.03.17.
 */
public abstract class DrinkMaker {

    abstract public void getRightCup();
    abstract public void putIngredient();
    abstract public void pour();

    public void makeDrink() {
        getRightCup();
        putIngredient();
        pour();
    }
}
