package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileInput = "";
        String fileOutput1 = "";
        String fileOutput2 = "";
        try {
            fileInput = reader.readLine();
            fileOutput1 = reader.readLine();
            fileOutput2 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream fis1 = null;
        FileOutputStream fos1 = null;
        FileOutputStream fos2 = null;
        try {
            fis1 = new FileInputStream(fileInput);
            fos1 = new FileOutputStream(fileOutput1);
            fos2 = new FileOutputStream(fileOutput2);
            int fileInputSize = fis1.available();
            int readCnt = 0;
            while (fis1.available() > 0) {
                if (fileInputSize % 2 == 0) {
                    if (readCnt < (fileInputSize / 2)) {
                        fos1.write(fis1.read());
                    } else {
                        fos2.write(fis1.read());
                    }
                } else {
                    if (readCnt <  (fileInputSize - (fileInputSize / 2))) {
                        fos1.write(fis1.read());
                    } else {
                        fos2.write(fis1.read());
                    }
                }
                readCnt++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fis1 != null;
                fis1.close();
                assert fos1 != null;
                fos1.close();
                assert fos2 != null;
                fos2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
