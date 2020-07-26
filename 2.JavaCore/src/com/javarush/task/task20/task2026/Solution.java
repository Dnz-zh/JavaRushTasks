package com.javarush.task.task20.task2026;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };
        byte[][] a3 = new byte[][] {
            {1,0,0,0,0,0,0,0},
            {0,0,0,1,1,1,1,0},
            {0,0,0,1,1,1,1,0},
            {0,0,0,1,1,1,1,0},
            {0,0,0,0,0,0,0,0},
            {0,1,1,0,0,0,0,0},
            {0,1,1,0,0,0,0,0},
            {0,0,0,0,0,1,1,0},
        };
        byte[][] a4 = new byte[][] {
            {1,1,0,0,0,0,0,0},
            {0,0,0,1,1,1,1,0},
            {1,1,0,1,1,1,1,0},
            {1,1,0,1,1,1,1,0},
            {0,0,0,0,0,0,0,0},
            {0,1,1,0,1,1,0,1},
            {0,1,1,0,0,0,0,0},
            {0,0,0,0,0,1,1,0},
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
        int count3 = getRectangleCount(a3);
        System.out.println("count = " + count3 + ". Должно быть 4");
        int count4 = getRectangleCount(a4);
        System.out.println("count = " + count4 + ". Должно быть 7");
    }

    public static int getRectangleCount(byte[][] a) {
        byte[][] ax = new byte[a.length][a.length];
        for (int i = 0; i < ax.length; i++) {
            for (int j = 0; j < ax.length; j++) {
                ax[i][j] = a[i][j];
            }
        }
        int result = 0;
        for (int i = 0; i < ax.length; i++) {
            for (int j = 0; j < ax.length; j++) {
                if (ax[i][j] == 1) {
                    result++;
                    int ii = i;
                    int jj = j;
                    while (ax[i][jj] == 1) {
                        jj++;
                        if (jj > ax.length - 1) {
                            jj--;
                            break;
                        }
                    }
                    while (ax[ii][j] == 1) {
                        ii++;
                        if (ii > ax.length - 1) {
                            ii--;
                            break;
                        }
                    }
                    for (int ix = i; ix <= ii; ix++) {
                        for (int jx = j; jx <= jj; jx++) {
                            ax[ix][jx] = 0;
                        }
                    }
                }
            }
        }
        return result;
    }
}
