package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            return;
        }
        String tagName = args[0];
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileReader fileIn = new FileReader(fileName);
        StringBuilder sb = new StringBuilder();
        char ch;
        while (fileIn.ready()) {
            ch = (char) fileIn.read();
            if (ch != '\n') {
                sb.append(ch);
            }
        }
        fileIn.close();
        Pattern pattern = Pattern.compile("<" + tagName + ">?+|</" + tagName + ">+");
        Matcher matcher = pattern.matcher(sb.toString());
        List<String> tags = new LinkedList<>();
        while (matcher.find()) {
            tags.add(matcher.group());
        }
        Map<Integer, Boolean> tagsMap = new TreeMap<>();
        int ind = 0;
        int startFrom = 0;
        boolean isCLosedTag;
        for (String tag : tags) {
            isCLosedTag = tag.startsWith("</");
            startFrom = sb.toString().indexOf(tag, ind);
            ind = sb.toString().indexOf(tag, ind) + 1;
            tagsMap.put(startFrom, isCLosedTag);
        }
        List<Integer> indexes = new LinkedList<>();
        for (Map.Entry<Integer, Boolean> entry : tagsMap.entrySet()) {
            indexes.add(entry.getKey());
        }
        int skip = 1;
        for (int i = 0; i < indexes.size() - 1; i ++) {
            startFrom = indexes.get(i);
            if (tagsMap.get(indexes.get(i))) {
                continue;
            }
            for (int j = i + 1; j < indexes.size(); j++) {
                if (!tagsMap.get(indexes.get(j))) {
                    skip++;
                } else {
                    skip--;
                    if (skip == 0) {
                        System.out.println(
                            sb.toString().substring(
                                startFrom,
                                indexes.get(j) + String.valueOf("</" + tagName + ">").length()
                            )
                        );
                        skip = 1;
                        break;
                    }
                }
            }
        }
    }
}
