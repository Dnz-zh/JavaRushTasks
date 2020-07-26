package com.javarush.task.task26.task2603;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static void main(String[] args) {
        TestClass first=new TestClass("aaa","bbb","ccc");
        TestClass second=new TestClass("aaa","ccc","hrt");
        TestClass third=new TestClass("aaa","ccc","aaa");
        ArrayList<TestClass> list=new ArrayList<>();
        list.add(first);list.add(second);list.add(third);
        list.sort(new CustomizedComparator<TestClass>(
            Comparator.comparing(o -> o.x),
            Comparator.comparing(o3 -> o3.y),
            Comparator.comparing(o2 -> o2.z)
        ));

        for (TestClass test:list) {
            test.consoleout();
        }
    }

    public static class CustomizedComparator<T> implements Comparator<T> {

        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T> ... vararg) {
            comparators = vararg;
        }

        @Override
        public int compare(T o1, T o2) {
            return Arrays.stream(comparators)
                .reduce((tt1, tt2) -> 0, Comparator::thenComparing)
                .compare(o1, o2);
        }
    }

    public static class TestClass {
        public String x;
        public String y;
        public String z;
        public TestClass(String x, String y, String z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public void consoleout(){
            System.out.println(x+" "+y+" "+z);

        }
    }
}
