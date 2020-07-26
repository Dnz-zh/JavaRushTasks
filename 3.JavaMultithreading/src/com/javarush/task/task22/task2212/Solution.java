package com.javarush.task.task22.task2212;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null || telNumber.isEmpty()) {
            return false;
        }
        int digCount = 0;
        for (int i = 0; i < telNumber.length(); i++) {
            if (Character.isDigit(telNumber.charAt(i))) {
                digCount++;
            }
        }
        String strPattern;
        switch (digCount) {
            case 10: {
                if (telNumber.contains("(") && telNumber.contains(")")) {
                    strPattern = "\\(?[\\d]{3}\\)?[\\d]{3}-?\\d{2}-?[\\d]{2}$";
                } else {
                    strPattern = "[\\d]{3}[\\d]{3}-?\\d{2}-?[\\d]{2}$";
                }
            }
            break;
            case 12: {
                if (telNumber.contains("(") && telNumber.contains(")")) {
                    strPattern = "\\+[\\d]{2}\\(?[\\d]{3}\\)?[\\d]{3}-?\\d{2}-?[\\d]{2}$";
                } else {
                    strPattern = "\\+[\\d]{2}[\\d]{3}[\\d]{3}-?\\d{2}-?[\\d]{2}$";
                }
            }
            break;
            default: {
                strPattern = "";
            }
        }
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(telNumber);
        return matcher.matches();
    }

    public static void main(String[] args) {
/*
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("+38(05012345-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("0501234567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
        System.out.println(checkTelNumber(null));
        System.out.println(checkTelNumber(""));
*/
    }
}