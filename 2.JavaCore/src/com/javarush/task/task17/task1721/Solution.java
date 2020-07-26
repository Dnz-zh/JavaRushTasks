package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        Solution solution = new Solution();
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in)
        );
        String fileName1 = "";
        String fileName2 = "";
        try {
            fileName1 = reader.readLine();
            fileName2 = reader.readLine();
        } catch (IOException e) {
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
        BufferedReader fileReader1 = null;
        BufferedReader fileReader2 = null;
        try {
            fileReader1 = new BufferedReader(new FileReader(fileName1));
            fileReader2 = new BufferedReader(new FileReader(fileName2));
            String data;
            while ((data = fileReader1.readLine()) != null) {
                allLines.add(data);
            }
            while ((data = fileReader2.readLine()) != null) {
                forRemoveLines.add(data);
            }
            solution.joinData();
        } catch (FileNotFoundException e) {
        } catch (CorruptedDataException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (fileReader1 != null) {
                    fileReader1.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public void joinData () throws CorruptedDataException {
        ArrayList<String> allList = new ArrayList<>(allLines);
        ArrayList<String> frlList = new ArrayList<>(forRemoveLines);
        boolean isFound = false;
        int count = 0;
        for (String str : frlList) {
            if (allList.contains(str)) {
                count++;
            }
        }
        if (count == frlList.size()) {
            isFound = true;
            for (Iterator<String> iterator = allList.iterator(); iterator.hasNext();) {
                String data = iterator.next();
                if (frlList.contains(data)) {
                    iterator.remove();
                }
            }
        }
        if (!isFound) {
            allLines.clear();
            throw new CorruptedDataException();
        } else {
            allLines.clear();
            allLines.addAll(allList);
            forRemoveLines.clear();
            forRemoveLines.addAll(frlList);
        }
    }
}
