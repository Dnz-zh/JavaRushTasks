package com.javarush.task.task01.task0133;

/* 
Не думать о секундах…
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getCountSecondsPassedInCurrentHour(12345));
    }

    public static int getCountSecondsPassedInCurrentHour(int seconds) {
        //напишите тут ваш код
        float fig = seconds / 60.0f / 60.0f;
        double fig1 = fig - (int)fig;
        return (int)Math.round(fig1 * 60 * 60);
    }
}