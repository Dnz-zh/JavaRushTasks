package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            TreeSet<Character> treeSet = new TreeSet<>();
            try(BufferedReader bufReader = new BufferedReader(new FileReader(args[0]))) {
                String s;
                while ((s = bufReader.readLine()) != null) {
                    s = s.toLowerCase();
                    char[] chars = s.toCharArray();
                    for (Character ch : chars) {
                        if (ch >= 'A' && ch <= 'z') {
                            treeSet.add(ch);
                        }
                    }
                }
            }
            treeSet.stream().limit(5).forEach(System.out::print);
        }
    }
}
