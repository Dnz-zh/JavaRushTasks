package com.javarush.task.task30.task3002;

/*
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        //напишите тут ваш код
        int ss = 10;
        String value = s;
        if (!s.isEmpty()) {
            if (s.length() >= 3) {
                if (s.substring(0, 2).equalsIgnoreCase("0x")) {
                    ss = 16;
                    value = s.substring(2);
                } else if (s.substring(0, 2).equalsIgnoreCase("0b")) {
                    ss = 2;
                    value = s.substring(2);
                } else if (s.charAt(0) == '0') {
                    ss = 8;
                    value = s.substring(1);
                }
            }
        }
        return String.valueOf(Integer.parseInt(value, ss));
    }
}
