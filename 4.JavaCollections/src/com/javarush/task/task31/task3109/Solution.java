package com.javarush.task.task31.task3109;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties result = new Properties();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            if (Paths.get(fileName).getFileName().toString().endsWith(".xml")) {
                result.loadFromXML(fis);
            } else {
                result.load(fis);
            }
        } catch (IOException e) {
        }
        return result;
    }
}
