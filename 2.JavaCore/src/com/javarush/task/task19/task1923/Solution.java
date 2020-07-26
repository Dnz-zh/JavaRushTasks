package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            return;
        }
        FileReader fileIn = new FileReader(args[0]);
        FileWriter fileOut = new FileWriter(args[1]);
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char ch;
        while (fileIn.ready()) {
            ch = (char) fileIn.read();
            if (ch != '\n') {
                sb.append(ch);
            } else {
                list.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if (!"".equals(sb.toString())) {
            list.add(sb.toString());
        }
        fileIn.close();
        sb = new StringBuilder();
        for (String str : list) {
            String[] strings = str.split(" ");
            for (String str1 : strings) {
                Pattern pattern = Pattern.compile("[0-9]");
                Matcher matcher = pattern.matcher(str1);
                if (matcher.find()) {
                    sb.append(str1).append(" ");
                }
            }
        }
        fileOut.write(sb.toString().trim());
        fileOut.close();
    }
}
