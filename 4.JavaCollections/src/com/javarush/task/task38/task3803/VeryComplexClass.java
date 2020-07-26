package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.ArrayList;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object i = (Integer)1;
        System.out.println((String) i);
    }

    public void methodThrowsNullPointerException() {
        ArrayList<Integer> list = null;
        list.add(1);
    }

    public static void main(String[] args) {
        VeryComplexClass veryComplexClass = new VeryComplexClass();
        veryComplexClass.methodThrowsNullPointerException();
    }
}
