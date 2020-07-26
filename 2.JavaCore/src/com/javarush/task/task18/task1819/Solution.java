package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName1 = reader.readLine();
            String fileName2 = reader.readLine();
            FileInputStream fileIn1 = new FileInputStream(fileName1);
            FileInputStream fileIn2 = new FileInputStream(fileName2);
            byte[] buffer1 = new byte[fileIn1.available()];
            byte[] buffer2 = new byte[fileIn2.available()];
            byte[] all = new byte[fileIn1.available() + fileIn2.available()];
            fileIn1.read(buffer1);
            fileIn2.read(buffer2);
            fileIn1.close();
            fileIn2.close();
            FileOutputStream fileOut1 = new FileOutputStream(fileName1);
            System.arraycopy(buffer2, 0, all, 0, buffer2.length);
            System.arraycopy(buffer1, 0, all, buffer2.length, buffer1.length);
            fileOut1.write(all);
            fileOut1.close();
        } catch (IOException e) {
        }
    }
}
