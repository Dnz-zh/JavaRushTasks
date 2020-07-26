package com.javarush.task.task14.task1408;

/**
 * Created by aleksandr on 21.02.17.
 */
public class MoldovanHen extends Hen {

    @Override
    public int getCountOfEggsPerMonth() {
        return 5;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + MOLDOVA
            + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
