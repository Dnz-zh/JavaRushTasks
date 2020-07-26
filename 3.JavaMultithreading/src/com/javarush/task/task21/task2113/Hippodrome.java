package com.javarush.task.task21.task2113;

import java.util.Arrays;
import java.util.List;

public class Hippodrome {

    public static Hippodrome game;

    private List<Horse> horses;

    public static void main(String[] args) {
        Hippodrome.game = new Hippodrome(Arrays.asList(
            new Horse("Red", 3, 0),
            new Horse("Blue", 3, 0),
            new Horse("Green", 3, 0)
        ));
        Hippodrome.game.run();
        Hippodrome.game.printWinner();
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 1; i <=10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        double max = 0;
        Horse horseWinner = null;
        for (Horse horse : horses) {
            if (horse.getDistance() > max) {
                max = horse.getDistance();
                horseWinner = horse;
            }
        }
        return horseWinner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
