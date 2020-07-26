package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) {
        //...
        String fileName = "";
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                sb.append(fileReader.readLine());
                sb.append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder result = getLine(sb.toString().trim().split(" "));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder sb = new StringBuilder();
        LinkedList<String> list = new LinkedList<>(Arrays.asList(words));
        LinkedList<String> result = new LinkedList<>();
        if (list.size() != 0) {
            if (list.size() == 1) {
                return sb.append(list.getFirst());
            }
            String startString = list.getFirst();
            char ch = startString.charAt(0);
            int index = 0;
            for (int i = 1; i < list.size(); i++) {
                char ch1 = list.get(i).charAt(0);
                if (ch > ch1) {
                    startString = list.get(i);
                    ch = ch1;
                    index = i;
                }
            }
            list.remove(index);
            result.add(startString);
            boolean isStop = false;
            while (!list.isEmpty() && !isStop) {
                for (int i = 0; i < list.size(); i++) {
                    isStop = true;
                    char ch1 = result.getLast().toLowerCase().charAt(result.getLast().length() - 1);
                    char ch2 = list.get(i).toLowerCase().charAt(0);
                    if (ch1 == ch2) {
                        isStop = false;
                        result.add(list.get(i));
                        list.set(i, "");
                        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
                            String string = iterator.next();
                            if ("".equals(string)) {
                                    iterator.remove();
                            }
                        }
                        break;
                    }
                }
                if (isStop) {
                    for (int i = 0; i < list.size(); i++) {
                        char ch1 = result.getFirst().toLowerCase().charAt(0);
                        char ch2 = list.get(i).toLowerCase().charAt(list.get(i).length() - 1);
                        if (ch1 == ch2) {
                            isStop = false;
                            result.add(0, list.get(i));
                            list.set(i, "");
                            for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
                                String string = iterator.next();
                                if ("".equals(string)) {
                                    iterator.remove();
                                }
                            }
                            break;
                        }
                    }
                }
            }
            if (isStop) {
                result.addAll(list);
            }
        }
        for (String str : result) {
            sb.append(str).append(" ");
        }
        return sb;
    }
}
