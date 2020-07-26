package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        if (args.length == 0 || args.length < 4 || !"-c".equals(args[0])) {
            return;
        }
        String quantity = padRight(args[args.length - 1].trim(), 4);
        String price = padRight(args[args.length - 2], 8);
        StringBuilder pn = new StringBuilder();
        for (int i = 1; i < args.length - 2; i++) {
            pn.append(args[i]).append(" ");
        }
        String productName = padRight(pn.toString().trim(), 30);
        BufferedReader inFile = new BufferedReader(
            new InputStreamReader(new FileInputStream(fileName)));
        List<String> strings = new ArrayList<>();
        String s;
        while ((s = inFile.readLine()) != null) {
            strings.add(s);
        }
        inFile.close();
        String id = padRight(String.valueOf(getMaxID(strings) + 1), 8);
        BufferedWriter outFile = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(fileName)));
        for (String str : strings) {
            outFile.write(str);
            outFile.newLine();
        }
        outFile.write(id + productName + price + quantity);
        outFile.close();
    }

    private static String padRight(String src, int length) {
        if (src.length() > length) {
            return src.substring(0, length);
        } if (src.length() < length) {
            int spacesCount = length - src.length();
            StringBuilder sb = new StringBuilder(src);
            for (int i = 0; i < spacesCount; i++) {
                sb.append(" ");
            }
            return sb.toString();
        } else {
            return src;
        }
    }

    private static long getMaxID(List<String> src) {
        if (src.size() != 0) {
            try {
                long max = 0L;
                for (String str : src) {
                    long id = Long.valueOf(str.substring(0, 8).trim());
                    if (max < id) {
                        max = id;
                    }
                }
                return max;
            } catch (Exception e) {
                return 1L;
            }
        } else {
            return 1L;
        }
    }

}
