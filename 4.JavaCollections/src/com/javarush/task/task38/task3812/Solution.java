package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if (c.getAnnotation(PrepareMyTest.class) == null) {
            return false;
        }
        PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
        Arrays.stream(prepareMyTest.fullyQualifiedNames()).forEach(System.out::println);
        return true;
    }

    public static boolean printValues(Class c) {
        if (c.getAnnotation(PrepareMyTest.class) == null) {
            return false;
        }
        PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
        for (Class cls : prepareMyTest.value()) {
            System.out.println(cls.getSimpleName());
        }
        return true;
    }
}
