package com.javarush.task.task13.task1318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        /*
        BufferedReader reader = null;
        String fileName = "";
        Scanner scanner = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            fileName = reader.readLine();
            scanner = new Scanner(new FileInputStream(fileName));
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found");
        } catch (IOException e) {
            System.out.println("I/O read error");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        }
        */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(fileName);

            while (inputStream.available() > 0) {
                System.out.print((char)inputStream.read());
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally
        {
            if (inputStream != null)
                inputStream.close();
            reader.close();
        }
    }
}