package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> list = new ArrayList<>();
        String s;
        int cnt = 1;
        while ((s = reader.readLine()) != null) {
            list.add(s);
            cnt++;
            if (cnt % 2 != 0) {
                list.add("JavaRush - курсы Java онлайн");
            }
        }
        reader.close();
        System.setOut(consoleStream);
        for (String str : list) {
            System.out.println(str);
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
