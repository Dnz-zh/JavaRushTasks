package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> results = detectAllWords(crossword, "home", "same", "poe");
        for (Word word : results) {
            System.out.println(word);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {

        List<Word> wordList = new ArrayList<>();
        boolean isAdded = false;
        for (String str: words) {
            for (Word word : wordList) {
                String[] st = word.toString().split(" ");
                isAdded = st[0].equals(str);
            }
            if (!isAdded) {
                Word result = searchWord(crossword, str);
                if (result != null) {
                    wordList.add(result);
                }
                isAdded = false;
            }
        }
        return wordList;
    }

    private static Word searchWord(int[][] crossword, String word) {
        int x[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int y[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

        int wordLen = word.length();
        char[] wordVec = word.toCharArray();
        Word wordResult;

        for (int col = 0; col < crossword.length; col++) {
            for (int row = 0; row < crossword[0].length; row++) {
                if ((char)crossword[col][row] == wordVec[0]) {
                    wordResult = new Word(word);
                    wordResult.setStartPoint(row, col);
                    for (int dir = 0; dir < 8; dir++) {
                        int rd = row + x[dir];
                        int cd = col + y[dir];
                        int k;
                        for (k = 1; k < wordLen; k++) {
                            if (rd > crossword[0].length || rd < 0 || cd > crossword.length || cd < 0) {
                                break;
                            }
                            try {
                                if ((char)crossword[cd][rd] != wordVec[k]) {
                                    break;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                            rd += x[dir];
                            cd += y[dir];
                        }
                        if (k == wordLen) {
                            wordResult.setEndPoint(rd - x[dir], cd - y[dir]);
                            return wordResult;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
