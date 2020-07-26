package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
        solution.createExpression(1234);
        solution.createExpression(19);
        solution.createExpression(463);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        int x = number;
        List<Integer> intermediate = new ArrayList<>();
        while (x >= 3) {
            if (x % 3 == 2) {
                x = (x / 3) + 1;
                intermediate.add(-1);
            } else {
                intermediate.add(x % 3);
                x /= 3;
            }
        }
        if (x == 2) {
            intermediate.add(-1);
            intermediate.add(1);
        } else {
            intermediate.add(x);
        }
        Collections.reverse(intermediate);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < intermediate.size(); i++) {
            int n = (int) Math.pow(3, intermediate.size() - (i + 1)) * intermediate.get(i);
            if (n != 0) {
                result.add(n);
            }
        }
        Collections.reverse(result);
        StringBuilder sb = new StringBuilder();
        for (Integer i : result) {
            if (i >= 0) {
                sb.append(" + ").append(i);
            } else {
                sb.append(" - ").append(Math.abs(i));
            }
        }
        System.out.println(String.format("%d = %s", number, sb.toString().trim()));
    }
}