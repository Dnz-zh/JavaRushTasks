package com.javarush.task.task20.task2011;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

/*
Externalizable для апартаментов
*/
public class Solution {

    public static class Apartment implements Externalizable {

        private String address;
        private int year;

        /**
         * Mandatory public no-arg constructor.
         */
        public Apartment() { super(); }

        public Apartment(String adr, int y) {
            address = adr;
            year = y;
        }

        /**
         * Prints out the fields. used for testing!
         */
        public String toString() {
            return("Address: " + address + "\n" + "Year: " + year);
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(address);
            out.writeInt(year);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            address = (String) in.readObject();
            year = in.readInt();
        }
    }

    public static void main(String[] args) throws Exception {
        Apartment apartment = new Apartment("Apart", 2001);
        System.out.println("Original: " + apartment);
        System.out.println("Object \"apartment\" writing to the \"apartment.out\"");
        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("apartment.out"));
        out.writeObject(apartment);
        out.close();
        TimeUnit.SECONDS.sleep(10);
        ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("apartment.out"));
        Object obj = in.readObject();
        in.close();
        Apartment apartment1 = (Apartment) obj;
        System.out.println("Object \"apartment\" reading from \"apartment.out\"");
        System.out.println("Loaded: " + apartment1);
    }
}
