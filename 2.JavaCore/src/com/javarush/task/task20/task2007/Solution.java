package com.javarush.task.task20.task2007;

/*
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* 
Как сериализовать JavaRush?
*/
public class Solution {
    public static class JavaRush implements Serializable {
        public List<User> users = new ArrayList<>();
    }

    public static void main(String[] args) {
        /*
        JavaRush javaRush = new JavaRush();
        System.out.println("Original object" + javaRush.users);

        FileOutputStream fileOut = new FileOutputStream("javarush.dat");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(javaRush);
        fileOut.close();
        objectOut.close();

        FileInputStream fileIn = new FileInputStream("javarush.dat");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Object object = objectIn.readObject();
        fileIn.close();
        objectIn.close();

        JavaRush javaRush1 = (JavaRush) object;

        System.out.println("Loaded object from file" + javaRush1.users);
        */
    }
}
