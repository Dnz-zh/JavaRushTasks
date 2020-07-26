package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(reader.readLine());
        }
        int indexshort = 0;
        int indexlong = 0;
        int lengthshort = list.get(0).length();
        int lengthlong = list.get(0).length();
        for (int i = 1; i < list.size() - 1; i++) {
            if (list.get(i).length() < lengthshort) {
                lengthshort = list.get(i).length();
                indexshort = i;
            }
            if (list.get(i).length() > lengthlong) {
                lengthlong = list.get(i).length();
                indexlong = i;
            }
        }
        if (indexlong < indexshort) {
            System.out.println(list.get(indexlong));
        } else {
            System.out.println(list.get(indexshort));
        }

    }
}
