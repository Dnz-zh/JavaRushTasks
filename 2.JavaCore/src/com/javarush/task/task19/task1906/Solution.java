package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String fileNameIn = reader.readLine();
        String fileNameOut = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(fileNameIn);
        FileWriter fileWriter = new FileWriter(fileNameOut);
        int cnt = 1;
        while (fileReader.ready()) {
            int data = fileReader.read();
            if (cnt % 2 == 0) {
                fileWriter.write(data);
            }
            cnt++;
        }
        fileReader.close();
        fileWriter.close();
    }
}
