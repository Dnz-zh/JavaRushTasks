package com.javarush.task.task20.task2014;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) {
        Solution savedObject = new Solution(4);
        System.out.println("Saved object: " + savedObject);
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("sol.out"));
            out.writeObject(savedObject);
            out.close();
            Solution solution = new Solution(3);
            ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("sol.out"));
            Object obj = in.readObject();
            Solution loadedObject = (Solution) obj;
            System.out.println("Loaded object: " + loadedObject);
            System.out.println("Is equals?: " + savedObject.string.equals(loadedObject.string));
            System.out.println("Solution: " + solution);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private final transient String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;
/*
    private void writeObject(ObjectOutputStream stream)
        throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream)
        throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        String[] strings = string.split(" ");
        temperature = Integer.valueOf(strings[strings.length - 2]);
        currentDate = new Date();
    }
*/
    public Solution() {
    }

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
