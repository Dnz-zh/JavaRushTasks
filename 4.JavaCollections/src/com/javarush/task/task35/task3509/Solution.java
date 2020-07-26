package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
//        ArrayList<Integer> integers = new ArrayList<>();
//        integers = newArrayList(1 ,2 ,3);
//        System.out.println(integers);
//        HashSet<String> strings = new HashSet<>();
//        strings = newHashSet("a", "b", "c");
//        System.out.println(strings);
//        HashMap<Integer, String> map = new HashMap<>();
//        map = newHashMap(Arrays.asList(1, 2), Arrays.asList("a", "b", "c"));
//        System.out.println(map);
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        ArrayList<T> result = new ArrayList<>();
        Collections.addAll(result, elements);
        return result;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        HashSet<T> result = new HashSet<>();
        Collections.addAll(result, elements);
        return result;
    }

    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException();
        }
        HashMap<K, V> result = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            result.put(keys.get(i), values.get(i));
        }
        return result;
    }
}
