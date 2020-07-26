package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder sb = new StringBuilder();
        if (a < b) {
            for (int i = a; i <=b; i++) {
                sb.append(i).append(" ");
            }
        } else if (a > b) {
            for (int i = a; i >= b; i--) {
                sb.append(i).append(" ");
            }
        } else {
            return Integer.toString(a);
        }
        return sb.toString().trim();
//        if (a > b) {
//            return a + " " + getAllNumbersBetween(a - 1, b);
//        } else {
//            if (a == b) {
//                return Integer.toString(a);
//            }
//            return a + " " + getAllNumbersBetween(a + 1, b);
//        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        //System.out.println("numberA = " + numberA + "; numberB = " +numberB);
        //numberA = 69; numberB = -7025
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}