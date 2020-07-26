package com.javarush.task.task18.task1825;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
            String fileName;
            Set<String> fileNames = new TreeSet<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String[] strings1 = o1.split(".part");
                    String[] strings2 = o2.split(".part");
                    if (strings1.length != 2 || strings2.length != 2) {
                        return 0;
                    } else {
                        Integer index1 = Integer.valueOf(strings1[1]);
                        Integer index2 = Integer.valueOf(strings2[1]);
                        return index1.compareTo(index2);
                    }
                }
            });
            while (true) {
                fileName = reader.readLine();
                if ("end".equals(fileName)) {
                    break;
                }
                fileNames.add(fileName);
            }
            reader.close();
            String outFileName = ((TreeSet<String>) fileNames).first()
                .split(".part")[0];
            FileOutputStream outFile = new FileOutputStream(outFileName);
            for (String inFile : fileNames) {
                BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(inFile));
                byte[] buffer = new byte[fileReader.available()];
                fileReader.read(buffer);
                outFile.write(buffer);
                fileReader.close();
            }
            outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
