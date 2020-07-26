package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        String str = outputStream.toString().trim();
        String[] strings = str.split(" ");
        System.setOut(consoleStream);
        int result = -1;
        switch (strings[1]) {
            case "+": {
                result = Integer.valueOf(strings[0]) + Integer.valueOf(strings[2]);
            }
            break;
            case "-": {
                result = Integer.valueOf(strings[0]) - Integer.valueOf(strings[2]);
            }
            break;
            case "*": {
                result = Integer.valueOf(strings[0]) * Integer.valueOf(strings[2]);
            }
        }
        System.out.print(str + " " +String.valueOf(result));
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

