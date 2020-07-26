package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileReader fileIn = new FileReader(fileName);
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char ch;
        while (fileIn.ready()) {
            ch = (char) fileIn.read();
            if (ch != '\n') {
                sb.append(ch);
            } else {
                lines.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if (!"".equals(sb.toString())) {
            lines.add(sb.toString());
        }
        fileIn.close();
        int wordsCnt = 0;
        for (String str : lines) {
            String[] strings = str.split(" ");
            for (String string : strings) {
                if (words.contains(string)) {
                    wordsCnt++;
                }
            }
            if (wordsCnt == 2) {
                System.out.println(str);
            }
            wordsCnt = 0;
        }
    }
}
