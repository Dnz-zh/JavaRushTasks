package com.javarush.task.task14.task1419;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //it's first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        //напишите тут ваш код

        try
        {
            int[] a = new int[5];

            for (int i = 0; i < 10; i++) {
                a[i] = i;
            }

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            String s = null;
            String m = s.toLowerCase();
        } catch (Exception e) {
            exceptions.add(e);
        }

        try
        {
            FileInputStream fis = new FileInputStream("home/aleksandr/test.txt");
        } catch (Exception e) {
            exceptions.add(e);
        }

        try
        {
            Object[] s = new Integer[4];
            s[0] = 4.4;
        } catch (Exception e) {
            exceptions.add(e);
        }

        try
        {
            Object obj = new Integer(100);
            System.out.println((String) obj);
        } catch (Exception e) {
            exceptions.add(e);
        }

        try
        {
            throw new IllegalArgumentException("The parent path cannot be null!");
        } catch (Exception e) {
            exceptions.add(e);
        }

        try
        {
            Class loadedClass = Class.forName("main.java.Utils");
            System.out.println("Class " + loadedClass + " found successfully!");
        } catch (Exception e) {
            exceptions.add(e);
        }

        try
        {
            int i = -10;
            int[] arr = new int[i];
        } catch (Exception e) {
            exceptions.add(e);
        }

        try
        {
            int i = Integer.parseInt("abd");
            System.out.println("Value parsed :" + i);
        } catch (Exception e) {
            exceptions.add(e);
        }

    }
}
