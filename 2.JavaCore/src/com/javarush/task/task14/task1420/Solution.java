package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer i1 = 0;
        Integer i2 = 0;

        try {
            while (true) {
                i1 = Integer.parseInt(reader.readLine());
                i2 = Integer.parseInt(reader.readLine());
                if (i1 > 0 && i2 > 0) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> set1 = new ArrayList<>();
        ArrayList<Integer> set2 = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= i1; i++) {
            if ( i1 % i == 0) {
                set1.add(i);
            }
        }
        for (int i = 1; i <= i2; i++) {
            if ( i2 % i == 0) {
                set2.add(i);
            }
        }
        if (set1.size() > set2.size()) {
            for (Integer i : set1) {
                if (set2.contains(i)) {
                    result.add(i);
                }
            }
        } else {
            for (Integer i : set2) {
                if (set1.contains(i)) {
                    result.add(i);
                }
            }
        }
        int max = 0;
        for (Integer i : result) {
            if (i > max) {
                max = i;
            }
        }
        System.out.println(max);
    }
}
