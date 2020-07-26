package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileNameOut = reader.readLine();
            String fileNameIn1 = reader.readLine();
            String fileNameIn2 = reader.readLine();
            FileOutputStream fileOut = new FileOutputStream(fileNameOut);
            FileInputStream fileIn1 = new FileInputStream(fileNameIn1);
            FileInputStream fileIn2 = new FileInputStream(fileNameIn2);
            reader.close();
            byte[] buffer1 = new byte[fileIn1.available()];
            byte[] buffer2 = new byte[fileIn2.available()];
            fileIn1.read(buffer1);
            fileIn2.read(buffer2);
            fileIn1.close();
            fileIn2.close();
            byte[] all = new byte[buffer1.length + buffer2.length];
            System.arraycopy(buffer1, 0, all, 0, buffer1.length);
            System.arraycopy(buffer2, 0, all, buffer1.length, buffer2.length);
            fileOut.write(all);
            fileOut.close();
        } catch (IOException e) {
        }

    }
}
