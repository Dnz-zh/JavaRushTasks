package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);

    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        ConsoleHelper.writeMessage("Введите название блюда:");

        List<Dish> result = new ArrayList<>();

        String str;
        while (true) {
            str = ConsoleHelper.readString();
            if ("exit".equalsIgnoreCase(str)) {
                return result;
            }
            if (Dish.allDishesToString().contains(str)) {
                result.add(Dish.valueOf(str));
            } else {
                ConsoleHelper.writeMessage("такого блюда нет в списке.");
            }
        }

    }

}
