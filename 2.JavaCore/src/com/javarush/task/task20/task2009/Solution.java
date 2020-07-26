package com.javarush.task.task20.task2009;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
Как сериализовать static?
*/
public class Solution {
    public static class ClassWithStatic implements Serializable {
        public static String staticString = "it's test static string";
        public int i;
        public int j;
        private String str;

        private void writeObject(ObjectOutputStream stream) throws IOException {
            str = staticString;
            stream.defaultWriteObject();
        }

        private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
            stream.defaultReadObject();
            ClassWithStatic.staticString = str;
        }
    }

    public static void main(String[] args) throws Exception {
        com.javarush.task.task20.task2010.Solution.Object obj = new com.javarush.task.task20.task2010.Solution.Object();
        obj.string1 = new com.javarush.task.task20.task2010.Solution.String();
        obj.string1.print();
        obj.string2 = new com.javarush.task.task20.task2010.Solution.String();
        obj.string2.print();

        FileOutputStream fileOut = new FileOutputStream("ownobject.dat");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(obj);
        fileOut.close();
        objectOut.close();

        FileInputStream fileIn = new FileInputStream("ownobject.dat");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Object object = objectIn.readObject();
        fileIn.close();
        objectIn.close();

        com.javarush.task.task20.task2010.Solution.Object obj1 = (com.javarush.task.task20.task2010.Solution.Object) object;

        System.out.println("=============");
        obj1.string1.print();
        obj1.string2.print();

        /*
        ClassWithStatic classWithStatic = new ClassWithStatic();
        classWithStatic.i = 1;
        classWithStatic.j = 2;

        System.out.println("Original object: i = " + classWithStatic.i
            + "; j = " + classWithStatic.j + "; staticString " + ClassWithStatic.staticString);

        FileOutputStream fileOut = new FileOutputStream("javarush.dat");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(classWithStatic);
        fileOut.close();
        objectOut.close();

        ClassWithStatic.staticString = "New string";

        System.out.println("Original object: i = " + classWithStatic.i
            + "; j = " + classWithStatic.j + "; staticString " + ClassWithStatic.staticString);

        FileInputStream fileIn = new FileInputStream("javarush.dat");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Object object = objectIn.readObject();
        fileIn.close();
        objectIn.close();

        ClassWithStatic classWithStatic1 = (ClassWithStatic) object;

        System.out.println("Original object: i = " + classWithStatic1.i
            + "; j = " + classWithStatic1.j + "; staticString " + ClassWithStatic.staticString);
            */
    }
}
