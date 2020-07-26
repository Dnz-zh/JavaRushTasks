package com.javarush.task.task20.task2020;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.logging.Logger;

/* 
Сериализация человека
*/
public class Solution {

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greetingString;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greetingString = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
    }

    enum Sex {
        MALE,
        FEMALE
    }

    public static void main(String[] args) throws Exception {
        Person person = new Person("Alex", "Bolduin",
            "USA", Sex.MALE);
        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("person.out"));
        out.writeObject(person);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("person.out"));
        Person person1 = (Person) in.readObject();
        in.close();
        System.out.println(person.greetingString);
    }
}
