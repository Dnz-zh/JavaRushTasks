package com.javarush.task.task18.task1816;

/* 
Английские буквы
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
            Pattern p = Pattern.compile("[a-zA-Z]");
            Matcher m = p.matcher(sb.toString());
            int cnt = 0;
            while (m.find()) {
                cnt += m.group().length();
            }
            System.out.println(cnt);
        } catch (IOException e) {
        }
    }
}
