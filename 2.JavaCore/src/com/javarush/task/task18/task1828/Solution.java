package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        if (args.length == 0 || args.length < 2) {
            return;
        }
        BufferedReader inFile = new BufferedReader(
            new InputStreamReader(new FileInputStream(fileName)));
        List<String> strings = new LinkedList<>();
        String s;
        while ((s = inFile.readLine()) != null) {
            strings.add(s);
        }
        inFile.close();
        boolean isOk = false;
        switch (args[0]) {
            case "-u": {
                if (args.length < 5) {
                    return;
                }
                StringBuilder entry = new StringBuilder(padRight(args[1], 8));
                StringBuilder pn = new StringBuilder();
                for (int i = 2; i < args.length - 2; i++) {
                    pn.append(args[i]).append(" ");
                }
                entry.append(padRight(pn.toString().trim(), 30))
                    .append(padRight(args[args.length - 2], 8))
                    .append(padRight(args[args.length - 1].trim(), 4));
                isOk = updateEntry(strings, entry);
            }
            break;
            case "-d": {
                if (args.length != 2) {
                    return;
                }
                isOk = deleteEntry(strings, padRight(args[1], 8));
            }
        }
        if (isOk && strings.size() != 0) {
            BufferedWriter outFile = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fileName)));
            outFile.write(((LinkedList<String>) strings).getFirst());
            if (strings.size() > 1) {
                for (int i = 1; i < strings.size(); i++) {
                    outFile.newLine();
                    outFile.write(strings.get(i));
                }
            }
            outFile.close();
        }
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

    private static int getID(List<String> src, String id) {
        int index = -1;
        for (String str : src) {
            if (str.substring(0, 8).equals(id)) {
                index = src.indexOf(str);
                break;
            }
        }
        return index;
    }

    private static boolean deleteEntry(List<String> src, String id) {
        int index = getID(src,id);
        if (index != -1) {
            src.remove(index);
            return true;
        } else {
            return false;
        }
    }

    private static boolean updateEntry(List<String> src, StringBuilder entry) {
        int index = getID(src,entry.substring(0, 8));
        if (index != -1) {
            src.set(index, entry.toString());
            return true;
        } else {
            return false;
        }
    }
}
