package com.javarush.task.task15.task1531;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

/* 
Факториал
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(reader.readLine());
        reader.close();

        System.out.println(factorial(input));
    }

    public static String factorial(int n) {
        //add your code here
        String result;

        if (n == 0) {
            result = "1";
        } else if (n < 0) {
            result = "0";
        } else {
            BigInteger data = BigInteger.valueOf(1);
            if (n <= 150) {
                for (int i = 1; i <= n; i++) {
                    data = data.multiply(BigInteger.valueOf(i));
                }
            }
            result = data.toString();
        }

        return result;
    }
}
