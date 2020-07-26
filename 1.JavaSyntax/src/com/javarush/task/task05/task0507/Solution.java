package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 0;
        int sum = 0;
        while (true) {
            int i = Integer.parseInt(reader.readLine());
            if (i != -1) {
                sum += i;
                cnt++;
            } else {
                break;
            }
        }
        System.out.println((sum * 1.0f)/cnt);
    }
}

