package com.javarush.task.task15.task1507;

/* 
ООП - Перегрузка
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, "8");
    }

    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }

    public static void printMatrix(short m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value + " short");
            }
            System.out.println();
        }
    }

    public static void printMatrix(short m, short n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value + " short2");
            }
            System.out.println();
        }
    }

    public static void printMatrix(short m, short n, String value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value + " String");
            }
            System.out.println();
        }
    }

    public static void printMatrix(String m, short n, String value)
    {
        System.out.println(m + " " + n + " " + value);
    }

    public static void printMatrix(String m, String n, String value)
    {
        System.out.println("All strings");
        System.out.println(m + " " + n + " " + value);
    }

    public static void printMatrix(short m, String n, String value)
    {
        System.out.println("Short + String + String");
        System.out.println(m + " " + n + " " + value);
    }

    public static void printMatrix(double m, String n, String value)
    {
        System.out.println("double + String + String");
        System.out.println(m + " " + n + " " + value);
    }

    public static void printMatrix(double m, double n, String value)
    {
        System.out.println("double + double + String");
        System.out.println(m + " " + n + " " + value);
    }

}
