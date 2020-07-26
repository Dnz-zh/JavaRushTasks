package com.javarush.task.task20.task2015;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/* 
Переопределение сериализации
*/
public class Solution implements Serializable, Runnable{
    private transient Thread runner;
    private int speed;

    public Solution() {
    }

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        // do something here, does not matter
        try {
            while (speed != 0) {
                System.out.println("Current speed: " + speed);
                TimeUnit.SECONDS.sleep(1);
                speed--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     Переопределяем сериализацию.
     Для этого необходимо объявить методы:
     private void writeObject(ObjectOutputStream out) throws IOException
     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        runner = new Thread(this);
        runner.start();
    }

    public static void main(String[] args) throws Exception {
        Solution savedObject = new Solution(10);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("thread.out"));
        out.writeObject(savedObject);
        out.close();
        TimeUnit.SECONDS.sleep(2);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("thread.out"));
        Solution loadedObject = (Solution) in.readObject();
        in.close();
    }
}
