package com.javarush.task.task20.task2012;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/* 
OutputToConsole
*/
public class Solution {
    public static String greeting = "Hello world";

    /**
     * OutputToConsole is the inner base class for improving your attentiveness.
     * An OutputToConsole object encapsulates the information needed
     * for the displaying [greeting] variable to the console.
     * @author JavaRush
     */
    public static class OutputToConsole implements Externalizable {
        private int counter;

        /**
         * @param out A stream for an externalization
         * @throws java.io.IOException
         */
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(counter);
        }

        /**
         * @param in A stream for a de-externalization
         * @throws java.io.IOException
         * @throws ClassNotFoundException
         */
        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            counter = in.readInt();
        }

        public OutputToConsole() {
        }
        /**
         * Class constructor specifying fake private field [i].
         */
        public OutputToConsole(int counter) {
            this.counter = counter;
        }

        /**
         * Prints greeting message to console counter times.
         */
        public void printMessage() {
            for (int i = 0; i < counter; i++) {
                System.out.println(greeting);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        OutputToConsole outputToConsole = new OutputToConsole(5);
        System.out.println("Test original");
        outputToConsole.printMessage();
        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("outtoconsole.out"));
        out.writeObject(outputToConsole);
        out.close();
        ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("outtoconsole.out"));
        Object obj = in.readObject();
        in.close();
        System.out.println("Test saved object");
        OutputToConsole outputToConsole1 = (OutputToConsole) obj;
        outputToConsole1.printMessage();
    }
}
