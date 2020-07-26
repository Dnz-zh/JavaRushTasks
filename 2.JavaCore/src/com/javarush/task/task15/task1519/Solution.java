package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напиште тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String data;
        while (true) {
            data = reader.readLine();
            if ("exit".equals(data)) {
                break;
            }
            try {
                if (data.contains(".")) {
                    print(Double.parseDouble(data));
                } else if (Integer.parseInt(data) > 0 && Integer.parseInt(data) < 128) {
                    print(Short.parseShort(data));
                } else if (Integer.parseInt(data) <= 0 || Integer.parseInt(data) >= 128) {
                    print(Integer.parseInt(data));
                }
            } catch (NumberFormatException ex) {
                print(data);
            }
        }
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
