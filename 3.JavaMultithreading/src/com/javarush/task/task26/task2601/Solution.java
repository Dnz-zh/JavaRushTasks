package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
//        Integer[] result = sort(new Integer[]{13,8,15,5,17,7});
//        for (Integer integer : result) {
//            System.out.print(integer + " ");
//        }
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Integer[] copy = Arrays.copyOf(array, array.length);
        final double median;
        Arrays.sort(copy);
        if (copy.length % 2 == 0)
            median = ((double)copy[copy.length/2] + (double)copy[copy.length/2 - 1])/2;
        else
            median = (double) copy[copy.length/2];
        Comparator<Integer> compareByMedian = (o1, o2) -> {
            double value = Math.abs(o1 - median) - Math.abs(o2 - median);
            if (value == 0) {
                value = o1 - o2;
            }
            return (int) value;
        };
        Arrays.sort(copy, compareByMedian);
        return copy;
    }
}
