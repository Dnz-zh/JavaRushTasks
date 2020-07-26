package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        //напишите тут ваш код
        HashMap<String, Integer> map = new HashMap<>();
        map.put("string1", 523);
        map.put("string2", 654);
        map.put("string3", 514);
        map.put("string4", 234);
        map.put("string5", 344);
        map.put("string6", 698);
        map.put("string7", 700);
        map.put("string8", 120);
        map.put("string9", 894);
        map.put("string10", 587);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        for (Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() < 500) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
    }
}