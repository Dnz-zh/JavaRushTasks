package com.javarush.task.task29.task2909.human;

public class Soldier extends Human {
    protected boolean isSoldier;

    public Soldier(String name, int age) {
        super(name, age);
        this.isSoldier = false;
    }

    public void live() {
        fight();
    }

    public void fight() {
    }
}
