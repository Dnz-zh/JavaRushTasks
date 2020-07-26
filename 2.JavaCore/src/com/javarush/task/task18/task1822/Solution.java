package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
            );
            String fileName = reader.readLine();
            reader.close();
            BufferedReader fileReader = new BufferedReader(
                new FileReader(fileName)
            );
            String data;
            while ((data = fileReader.readLine()) != null) {
                String[] strings = data.split(" ");
                if (strings[0].equals(args[0])) {
                    System.out.println(data);
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
