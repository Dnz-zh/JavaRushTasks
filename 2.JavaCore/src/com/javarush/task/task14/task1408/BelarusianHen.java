package com.javarush.task.task14.task1408;

/**
 * Created by aleksandr on 21.02.17.
 */
public class BelarusianHen extends Hen {

    @Override
    public int getCountOfEggsPerMonth() {
        return 9;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + BELARUS
            + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
