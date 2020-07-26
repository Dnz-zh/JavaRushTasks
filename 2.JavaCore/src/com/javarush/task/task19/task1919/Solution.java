package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            return;
        }

        FileReader fileIn = new FileReader(args[0]);
        Map<String, Double> data = new TreeMap<>();
        StringBuilder sb = new StringBuilder();
        char ch;
        while (fileIn.ready()) {
            ch = (char)fileIn.read();
            if (ch != '\n') {
                sb.append(ch);
            } else {
                String[] strings = sb.toString().split(" ");
                if (data.containsKey(strings[0])) {
                    double d = data.get(strings[0]);
                    d = d + Double.valueOf(strings[1]);
                    data.put(strings[0], d);
                } else {
                    data.put(strings[0], Double.valueOf(strings[1]));
                }
                sb = new StringBuilder();
            }
        }
        if (!"".equals(sb.toString())) {
            String[] strings = sb.toString().split(" ");
            if (data.containsKey(strings[0])) {
                double d = data.get(strings[0]);
                d = d + Double.valueOf(strings[1]);
                data.put(strings[0], d);
            } else {
                data.put(strings[0], Double.valueOf(strings[1]));
            }
        }
        fileIn.close();
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
