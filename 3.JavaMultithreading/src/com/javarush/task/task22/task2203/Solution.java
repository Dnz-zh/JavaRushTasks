package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        String result = string;
        try {
            int cnt = 0;
            int index = -1;
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '\t') {
                    cnt++;
                    if (cnt > 2) {
                        break;
                    }
                    index = i;
                }
            }
            if (cnt < 2) {
                throw new TooShortStringException();
            } else {
                result = string.substring(string.indexOf('\t') + 1, index);
            }
        } catch (Exception e) {
            throw new TooShortStringException();
        }
        return result;
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
