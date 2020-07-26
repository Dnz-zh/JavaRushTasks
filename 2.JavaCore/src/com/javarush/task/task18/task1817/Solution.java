package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
            bufferedReader.close();
            Pattern p = Pattern.compile(" ");
            Matcher m = p.matcher(sb);
            int cnt = 0;
            while (m.find()) {
                cnt++;
            }
            System.out.println(String.format("%.2f", (cnt / (sb.length() * 1.0)) * 100));
        } catch (IOException e) {
        }
    }
}
