package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Map<Byte, Integer> asciiTable = new TreeMap<>();
            while (fis.available() > 0) {
                byte ch = (byte) fis.read();
                if (asciiTable.containsKey(ch)) {
                    int cnt = asciiTable.get(ch);
                    asciiTable.put(ch, ++cnt);
                } else {
                    asciiTable.put(ch, 1);
                }
            }
            for (Map.Entry<Byte, Integer> sets : asciiTable.entrySet()) {
                char ch = (char) sets.getKey().shortValue();
                System.out.println(ch + " " + sets.getValue());
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
