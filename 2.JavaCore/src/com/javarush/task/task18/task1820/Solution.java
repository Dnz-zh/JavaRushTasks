package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileNameIn = reader.readLine();
            String filNameOut = reader.readLine();
            reader.close();
            StringBuilder sb = new StringBuilder();
            BufferedReader fileIn = new BufferedReader(new FileReader(fileNameIn));
            String s;
            while ((s = fileIn.readLine()) != null) {
                sb.append(s);
            }
            fileIn.close();
            StringBuilder sbOut = new StringBuilder();
            for (String string : sb.toString().split(" ")) {
                sbOut.append(String.valueOf(Math.round(Double.valueOf(string)))).append(" ");
            }
            FileOutputStream fileOut = new FileOutputStream(filNameOut);
            fileOut.write(sbOut.toString().getBytes());
            fileOut.close();
        } catch (IOException e) {
        }
    }
}
