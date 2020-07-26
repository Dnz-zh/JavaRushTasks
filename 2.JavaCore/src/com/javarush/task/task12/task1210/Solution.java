package com.javarush.task.task12.task1210;

/* 
Три метода возвращают максимальное из двух переданных в него чисел
*/

public class Solution {
    public static void main(String[] args) {

    }

    //Напишите тут ваши методы
    public static int max(int a, int b) {
        int result;
        if (a > b) {
            result = a;
        } else {
            result = b;
        }
        return result;
    }

    public static long max(long a, long b) {
        long result;
        if (a > b) {
            result = a;
        } else {
            result = b;
        }
        return result;
    }

    public static double max(double a, double b) {
        double result;
        if (a > b) {
            result = a;
        } else {
            result = b;
        }
        return result;
    }
}
