package com.javarush.task.task19.task1917;

/* 
Свой FileWriter
*/

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;

public class FileConsoleWriter {

    private FileWriter fileWriter;

    public static void main(String[] args) throws IOException {

        FileConsoleWriter fileConsoleWriter = new FileConsoleWriter("out.txt");

        char[] cbuf = new char[26];
        for (int i = 0; i < cbuf.length; i++) {
            cbuf[i] = (char)(97 + i);
        }

        /*
        fileConsoleWriter.write(cbuf, 3, 5);
        */

        String str = "Hello world!";
        fileConsoleWriter.write(str, 1, 4);

        /*
        fileConsoleWriter.write('a');
        */
        /*
        fileConsoleWriter.write(cbuf);
        */
        fileConsoleWriter.close();
    }

    public FileConsoleWriter(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException {
        fileWriter = new FileWriter(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException {
        fileWriter = new FileWriter(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException {
        fileWriter = new FileWriter(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd) {
        fileWriter = new FileWriter(fd);
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        fileWriter.write(cbuf, off, len);
        StringBuilder sb = new StringBuilder();
        for (int i = off; i < off + len; i++) {
            sb.append(cbuf[i]);
        }
        System.out.println(sb);
    }

    public void write(int c) throws IOException {
        fileWriter.write(c);
        System.out.println(c);
    }

    public void write(String str) throws IOException {
        fileWriter.write(str);
        System.out.println(str);
    }

    public void write(String str, int off, int len) throws IOException {
        fileWriter.write(str, off, len);
        System.out.println(str.substring(off, off + len));
    }

    public void write(char[] cbuf) throws IOException {
        fileWriter.write(cbuf);
        System.out.println(String.valueOf(cbuf));
    }

    public void close() throws IOException {
        fileWriter.close();
    }

}
