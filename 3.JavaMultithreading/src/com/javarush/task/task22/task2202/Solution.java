package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        try {
            int cnt = 0;
            int index = -1;
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == ' ') {
                    cnt++;
                    if (cnt > 5) {
                        break;
                    }
                    index = i;
                }
            }
            if (cnt < 4) {
                throw new TooShortStringException();
            } if (cnt == 4) {
                return string.substring(string.indexOf(' ') + 1);
            } else {
                return string.substring(string.indexOf(' ') + 1, index);
            }
        } catch (Exception e) {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
