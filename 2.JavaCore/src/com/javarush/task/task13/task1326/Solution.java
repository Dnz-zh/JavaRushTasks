package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        Scanner scanner = null;
        ArrayList<Integer> integers = new ArrayList<>();
        try {
            scanner = new Scanner(new FileInputStream(fileName));
            while (scanner.hasNext()) {
                integers.add(scanner.nextInt());
            }
            Collections.sort(integers);
            for (Integer i : integers) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }


    }
}
