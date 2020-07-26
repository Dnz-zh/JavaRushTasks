package com.javarush.task.task12.task1209;

/* 
Три метода возвращают минимальное из двух переданных в него чисел
*/

public class Solution {
    public static void main(String[] args) {

    }

    //Напишите тут ваши методы
    public static int min(int a, int b) {
        int result;
        if (a < b) {
            result = a;
        } else {
            result = b;
        }
        return result;
    }

    public static long min(long a, long b) {
        long result;
        if (a < b) {
            result = a;
        } else {
            result = b;
        }
        return result;
    }

    public static double min(double a, double b) {
        double result;
        if (a < b) {
            result = a;
        } else {
            result = b;
        }
        return result;
    }
}
