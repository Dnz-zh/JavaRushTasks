package com.javarush.task.task20.task2006;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Как сериализовать?
*/
public class Solution {
    public static class Human implements Serializable {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Human human = new Human("Ivanov", new Asset("home"), new Asset("car"));
        System.out.println("Original object" + human.assets);

        FileOutputStream fileOut = new FileOutputStream("human.dat");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(human);
        fileOut.close();
        objectOut.close();

        FileInputStream fileIn = new FileInputStream("human.dat");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Object object = objectIn.readObject();
        fileIn.close();
        objectIn.close();

        Human human1 = (Human) object;

        System.out.println("Loaded object from file" + human1.assets);
    }
}
