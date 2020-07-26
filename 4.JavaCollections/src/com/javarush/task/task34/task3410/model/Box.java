package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {

    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
        graphics.drawLine(getX(), getY(), getX() + Model.FIELD_CELL_SIZE, getY() + Model.FIELD_CELL_SIZE);
        graphics.drawLine(getX(), getY() + Model.FIELD_CELL_SIZE, getX() + Model.FIELD_CELL_SIZE, getY());
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }
}
