package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            return;
        }

        FileInputStream reader = new FileInputStream(args[0]);
        FileOutputStream writer = new FileOutputStream(args[1]);

        byte[] buffer = new byte[reader.available()];
        reader.read(buffer);

        String str = new String(buffer, "Windows-1251");
        buffer = str.getBytes(StandardCharsets.UTF_8);
        writer.write(buffer);
        reader.close();
        writer.close();
    }
}
