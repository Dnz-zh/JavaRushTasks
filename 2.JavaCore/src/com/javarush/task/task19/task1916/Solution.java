package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        FileReader file1 = new FileReader(fileName1);
        FileReader file2 = new FileReader(fileName2);
        List<String> strings1 = new LinkedList<>();
        List<String> strings2 = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        char ch;
        while (file1.ready()) {
            ch = (char)file1.read();
            if (ch != '\n') {
                sb.append(ch);
            } else {
                strings1.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if (sb.length() != 0) {
            strings1.add(sb.toString());
        }
        sb = new StringBuilder();
        while (file2.ready()) {
            ch = (char)file2.read();
            if (ch != '\n') {
                sb.append(ch);
            } else {
                strings2.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if (sb.length() != 0) {
            strings2.add(sb.toString());
        }
        file1.close();
        file2.close();
        boolean stop = false;
        int index1 = 0;
        int index2 = 0;
        String str1;
        String str2;
        String str3;
        String str4;
        while (!stop) {
            str1 = index1 > strings1.size() - 1 ? strings1.get(strings1.size() - 1) : strings1.get(index1);
            str2 = index2 > strings2.size() - 1 ? strings2.get(strings2.size() - 1) : strings2.get(index2);
            if (str1.equals(str2)) {
                lines.add(new LineItem(Type.SAME, strings1.get(index1)));
                index1++;
                index2++;
            } else {
                str3 = index1 + 1 > strings1.size() - 1 ? strings1.get(strings1.size() - 1) : strings1.get(index1 + 1);
                str4 = index2 > strings2.size() - 1 ? strings2.get(strings2.size() - 1) : strings2.get(index2);
                if (str3.equals(str4)) {
                    if (index1 < strings1.size()) {
                        lines.add(new LineItem(Type.REMOVED, strings1.get(index1)));
                        index1++;
                    } else {
                        lines.add(new LineItem(Type.ADDED, strings2.get(index2)));
                        index2++;
                    }
                } else {
                    if (index2 < strings2.size()) {
                        lines.add(new LineItem(Type.ADDED, strings2.get(index2)));
                        index2++;
                    } else {
                        lines.add(new LineItem(Type.REMOVED, strings1.get(index1)));
                        index1++;
                    }
                }
            }
            if (index1 > strings1.size() - 1 && index2 > strings2.size() - 1) {
                stop = true;
            }
        }
        for (LineItem lineItem : lines) {
            System.out.println(lineItem.type + " " + lineItem.line);
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
