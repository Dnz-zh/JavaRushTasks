package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileReader fileIn = new FileReader(fileName);
        StringBuilder sb = new StringBuilder();
        char ch;
        while (fileIn.ready()) {
            ch = (char) fileIn.read();
            if (ch != '\n') {
                sb.append(ch);
            } else {
                System.out.println(sb.reverse().toString());
                sb = new StringBuilder();
            }
        }
        if (!"".equals(sb.toString())) {
            System.out.println(sb.reverse().toString());
        }
        fileIn.close();
    }
}
