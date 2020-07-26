package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int fig = Integer.parseInt(bufferedReader.readLine());
        char[] chars = String.valueOf(fig).toCharArray();
        even = 0;
        odd = 0;
        for (int i = 0; i < chars.length; i++) {
            int j = Integer.parseInt(String.valueOf(chars[i]));
            if (j % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        System.out.println("Even: " + even + " Odd: " + odd);
    }
}
