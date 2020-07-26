package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String uri = reader.readLine();
        if (uri.indexOf('?') != -1 && uri.indexOf('?') + 1 < uri.length()) {
            String[] params = uri.substring(uri.indexOf('?') + 1).trim().split("&");
            for (String str : params) {
                if (str.indexOf('=') != -1) {
                    System.out.print(str.substring(0, str.indexOf('=')));
                    System.out.print(" ");
                } else {
                    System.out.print(str);
                    System.out.print(" ");
                }
            }
            System.out.println();
            for (String str : params) {
                if (str.indexOf('=') != -1) {
                    if ("obj".equals(str.substring(0, str.indexOf('=')))) {
                        String data = str.substring(str.indexOf('=') + 1);
                        try {
                            alert(Double.parseDouble(data));
                        } catch (NumberFormatException err) {
                            alert(data);
                        }
                    }
                }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
