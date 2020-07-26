package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            if (args.length != 0 && args.length < 256) {
                System.out.println(getRadix(args[0]));
            }
        } catch (Exception e) {

        }
    }

    private static String getRadix(String number) {
        TreeSet<Integer> result = new TreeSet<>();
        for (int i = 2; i <= 36; i++) {
            try {
                BigInteger bigInteger = new BigInteger(number, i);
                result.add(i);
            } catch (NumberFormatException e) {
            }
        }
        return result.isEmpty() ? "incorrect" : String.valueOf(result.first());
    }
}