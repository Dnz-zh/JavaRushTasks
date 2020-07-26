package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        if (args.length < 3) {
            return;
        }
        String fileName = args[0];
        long filePos = Long.valueOf(args[1]);
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        String text = sb.toString().trim();
        try(RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            if (filePos > raf.length()) {
                raf.seek(raf.length());
            } else if (filePos < 0) {
                raf.seek(0);
            } else {
                raf.seek(filePos);
            }
            byte[] buffer = new byte[8 * 1024];
            raf.read(buffer, 0, text.length());
            String newString = new String(buffer).trim();
            raf.seek(raf.length());
            if (text.equals(newString)) {
                raf.write("true".getBytes());
            } else {
                raf.write("false".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
