package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            return;
        }
        FileReader fileIn = new FileReader(args[0]);
        FileWriter fileOut = new FileWriter(args[1]);
        List<String> list = new ArrayList<>();
        char ch;
        StringBuilder sb = new StringBuilder();
        while (fileIn.ready()) {
            ch = (char) fileIn.read();
            if (ch != ' ' && ch != '\n') {
                sb.append(ch);
            } else {
                if (sb.length() > 6) {
                    list.add(sb.toString());
                }
                sb = new StringBuilder();
            }
        }
        if (!"".equals(sb.toString())) {
            if (sb.length() > 6) {
                list.add(sb.toString());
            }
        }

        String[] strings = list.toArray(new String[0]);
        for (int i = 0; i < strings.length - 1; i++) {
            fileOut.write(strings[i] + ",");
        }
        fileOut.write(strings[strings.length - 1]);

        fileIn.close();
        fileOut.close();
    }
}
