package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader
            (new InputStreamReader(System.in));
        String fileIn = reader.readLine();
        String fileOut = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileIn));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileOut));
        Pattern pattern = Pattern.compile("\\b[0-9]+\\b");
        String s;
        while ((s = fileReader.readLine()) != null) {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                fileWriter.write(matcher.group() + " ");
            }
        }
        fileReader.close();
        fileWriter.close();
    }
}
