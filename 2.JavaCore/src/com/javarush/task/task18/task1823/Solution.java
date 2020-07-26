package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
            String fileName;
            while (true) {
                fileName = reader.readLine();
                if ("exit".equals(fileName)) {
                    break;
                }
                ReadThread rthread = new ReadThread(fileName);
                rthread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ReadThread extends Thread {

        private FileInputStream fis = null;
        private int[] data = new int[256];
        private String fileName;

        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
            try {
                fis = new FileInputStream(fileName);
                for (int i = 0; i < 256; i++) {
                    data[i] = 0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // implement file reading here - реализуйте чтение из файла тут
        @Override
        public void run() {
            if (fis != null) {
                try {
                    while (fis.available() > 0) {
                        int readByte = fis.read();
                        data[readByte] = ++data[readByte];
                    }
                    fis.close();
                    int max = 0;
                    int id = 0;
                    for (int i = 0; i < 256; i++) {
                        if (data[i] > max) {
                            max = data[i];
                            id = i;
                        }
                    }
                    Solution.resultMap.put(fileName, id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
