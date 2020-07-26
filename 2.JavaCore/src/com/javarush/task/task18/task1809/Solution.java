package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileInput = "";
        String fileOutput = "";
        try {
            fileInput = reader.readLine();
            fileOutput = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(fileInput);
            fos = new FileOutputStream(fileOutput);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            for (int i = buffer.length - 1; i >= 0; i--) {
                System.out.print((char)buffer[i]);
                fos.write(buffer[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fis != null;
                fis.close();
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
