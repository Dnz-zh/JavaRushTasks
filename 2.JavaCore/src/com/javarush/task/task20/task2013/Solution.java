package com.javarush.task.task20.task2013;

import java.io.Externalizable;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
//import java.io.ObjectInputStream;
import java.io.ObjectOutput;
//import java.io.ObjectOutputStream;
//import java.util.Arrays;
import java.util.List;
//import java.util.concurrent.TimeUnit;

/* 
Externalizable Person
*/
public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person() {
        }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            mother = (Person) in.readObject();
            father = (Person) in.readObject();
            firstName = (String) in.readObject();
            lastName = (String) in.readObject();
            age = in.readInt();
            children = (List<Person>) in.readObject();
            /*
            firstName = in.readLine();
            lastName = in.readLine();
            father = (Person)in.readObject();
            mother = (Person)in.readObject();
            age = in.readInt();
            children = (List)in.readObject();
            */
        }

        /*
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("First name: ").append(firstName).append(";Last name: ")
                .append(lastName).append(";Age: ").append(age);
            return sb.toString();
        }
        */
    }

    public static void main(String[] args) {
        /*
        Person father = new Person("Alexandr", "Alexandrov", 34);
        Person mother = new Person("Olga", "Alexandrova", 34);
        Person daughter1 = new Person("Snejana", "Terenteva", 13);
        Person daughter2 = new Person("Julia", "Alexandrova", 7);
        daughter1.setMother(mother);
        daughter1.setFather(father);
        daughter2.setMother(mother);
        daughter2.setFather(father);
        father.setChildren(Arrays.asList(daughter1, daughter2));
        mother.setChildren(Arrays.asList(daughter1, daughter2));
        System.out.println("Original");
        System.out.println("Father");
        System.out.println(father);
        System.out.println("-----");
        System.out.println("Mother");
        System.out.println(mother);
        System.out.println("-----");
        System.out.println("Daughter1");
        System.out.println(daughter1);
        System.out.println("-----");
        System.out.println("Daughter2");
        System.out.println(daughter2);
        System.out.println("-----");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("family.out"));
        out.writeObject(father);
        out.writeObject(mother);
        out.writeObject(daughter1);
        out.writeObject(daughter2);
        out.close();
        TimeUnit.SECONDS.sleep(1);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("family.out"));
        Person father1 = (Person) in.readObject();
        Person mother1 = (Person) in.readObject();
        Person daughter11 = (Person) in.readObject();
        Person daughter12 = (Person) in.readObject();
        in.close();
        System.out.println();
        System.out.println("Saved");
        System.out.println("Father");
        System.out.println(father1);
        System.out.println("-----");
        System.out.println("Mother");
        System.out.println(mother1);
        System.out.println("-----");
        System.out.println("Daughter1");
        System.out.println(daughter11);
        System.out.println("-----");
        System.out.println("Daughter2");
        System.out.println(daughter12);
        System.out.println("-----");
        */
    }
}
