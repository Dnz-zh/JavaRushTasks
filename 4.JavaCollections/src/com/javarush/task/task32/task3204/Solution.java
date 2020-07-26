package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        List<Character> pass = new ArrayList<>();
        int[] smallAlphabetic = IntStream.rangeClosed('a', 'z').toArray();
        int[] bigAlphabetic = IntStream.rangeClosed('A', 'Z').toArray();
        int[] digits = IntStream.rangeClosed('0', '9').toArray();
        int value;
        pass.add((char)smallAlphabetic[(int) (Math.random() * smallAlphabetic.length)]);
        pass.add((char)bigAlphabetic[(int) (Math.random() * bigAlphabetic.length)]);
        pass.add((char)digits[(int) (Math.random() * digits.length)]);
        for (int i = 4; i <= 8; i++) {
            value = (int) (Math.random() * 4);
            value = value == 0 ? 1 : value;
            switch (value) {
                case 1: {
                    pass.add((char)smallAlphabetic[(int) (Math.random() * smallAlphabetic.length)]);
                }
                break;
                case 2: {
                    pass.add((char)bigAlphabetic[(int) (Math.random() * bigAlphabetic.length)]);
                }
                break;
                case 3: {
                    pass.add((char) digits[(int) (Math.random() * digits.length)]);
                }
                break;
            }
        }
        Collections.shuffle(pass, new Random(8));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (Character ch : pass) {
            baos.write(ch);
        }
        return baos;
    }
}