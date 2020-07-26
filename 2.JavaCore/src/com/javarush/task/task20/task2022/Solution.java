package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String fileName;

    public Solution() {
    }

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution("test.txt");
        solution.writeObject("Test num 1");
        solution.close();
        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("solution.out"));
        out.writeObject(solution);
        out.close();
        ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("solution.out"));
        Solution solution1 = (Solution) in.readObject();
        solution1.writeObject("Another new data");
        solution1.close();
    }
}
