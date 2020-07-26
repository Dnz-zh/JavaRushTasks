package com.javarush.task.task20.task2021;

import java.io.*;

/* 
Сериализация под запретом
*/
public class Solution implements Serializable {
    public static class SubSolution extends Solution {
        private void writeObject(ObjectOutputStream stream) throws IOException {
            throw new NotSerializableException();
        }

        private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) throws Exception {
        SubSolution subSol = new SubSolution();
        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("subsol.out"));
        out.writeObject(subSol);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("subsol.out"));
        SubSolution subSol1 = (SubSolution) in.readObject();
        in.close();
        System.out.println(subSol1.getClass().getSimpleName());
    }
}
