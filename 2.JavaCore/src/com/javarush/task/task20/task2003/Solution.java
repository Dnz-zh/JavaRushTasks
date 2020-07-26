package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    private Properties prop = new Properties();

    public void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream fileInProp = new FileInputStream(fileName);
        load(fileInProp);
        fileInProp.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        prop.clear();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            prop.setProperty(entry.getKey(), entry.getValue());
        }
        prop.store(outputStream, "");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        properties.clear();
        prop.clear();
        prop.load(inputStream);
        for (String pn : prop.stringPropertyNames()) {
            properties.put(pn, prop.getProperty(pn));
        }
    }

    public static void main(String[] args) throws Exception {
    }
}
