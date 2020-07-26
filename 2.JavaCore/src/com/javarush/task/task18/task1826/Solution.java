package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            return;
        }
        BufferedInputStream inFile = new BufferedInputStream(
            new FileInputStream(args[1]));
        BufferedOutputStream outFile = new BufferedOutputStream(
            new FileOutputStream(args[2]));
        byte[] buffer = new byte[inFile.available()];
        inFile.read(buffer);
        byte[] result = null;
        switch (args[0]) {
            case "-e": {
                result = Base64.getEncoder().encode(buffer);
            }
                break;
            case "-d": {
                result = Base64.getDecoder().decode(buffer);
            }
        }
        if (result != null) {
            outFile.write(result);
        }
        inFile.close();
        outFile.close();
    }
}
