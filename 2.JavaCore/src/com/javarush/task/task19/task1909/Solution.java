package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String fileIn = reader.readLine();
        String fileOut = reader.readLine();
        reader.close();
        StringBuilder sb = new StringBuilder();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileIn));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileOut));
        String s;
        while ((s = fileReader.readLine()) != null) {
            sb.append(s).append('\n');
        }
        fileWriter.write(sb.toString().replace('.', '!'));
        fileReader.close();
        fileWriter.close();
    }
}
