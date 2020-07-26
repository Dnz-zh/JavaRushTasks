package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(fileName);
        StringBuilder sb = new StringBuilder();
        while (fileReader.ready()) {
            char ch = (char) fileReader.read();
            sb.append(ch);
        }
        Pattern pattern = Pattern.compile("\\b[Ww][Oo][Rr][Ll][Dd]\\b");
        Matcher matcher = pattern.matcher(sb.toString());
        int cnt = 0;
        while (matcher.find()) {
            cnt++;
        }
        System.out.println(cnt);
        fileReader.close();

    }
}
