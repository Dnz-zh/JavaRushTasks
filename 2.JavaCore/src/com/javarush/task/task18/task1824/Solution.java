package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader( System.in));
            String fileName;
            List<FileInputStream> fileInputStreams = new ArrayList<>();
            while (true) {
                fileName = reader.readLine();
                try {
                    fileInputStreams.add(new FileInputStream(fileName));
                } catch (FileNotFoundException e) {
                    System.out.println(fileName);
                    for (FileInputStream streams : fileInputStreams) {
                        streams.close();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
