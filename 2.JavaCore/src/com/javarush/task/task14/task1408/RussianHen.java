package com.javarush.task.task14.task1408;

/**
 * Created by aleksandr on 21.02.17.
 */
public class RussianHen extends Hen {

    @Override
    public int getCountOfEggsPerMonth() {
        return 10;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + RUSSIA
            + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
