package com.javarush.task.task01.task0132;

/* 
Сумма цифр трехзначного числа
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(sumDigitsInNumber(546));
    }

    public static int sumDigitsInNumber(int number) {
        //напишите тут ваш код
        char[] charr = String.valueOf(number).toCharArray();
        int summ = 0;
        for (char ch : charr) {
            summ = summ + Integer.parseInt(String.valueOf(ch));
        }
        return summ;
    }
}