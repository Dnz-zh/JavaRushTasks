package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws DownloadException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fis = null;
        while (true) {
            try {
                fis = new FileInputStream(reader.readLine());
                if (fis.available() < 1000) {
                    fis.close();
                    reader.close();
                    throw new DownloadException();
                }
                fis.close();
            } catch (IOException e) {
            }
        }
    }

    public static class DownloadException extends Exception {

    }
}
