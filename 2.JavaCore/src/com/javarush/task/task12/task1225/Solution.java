package com.javarush.task.task12.task1225;

/* 
Что это? «Кот», «Тигр», «Лев», «Бык», «Корова», «Животное»
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getObjectType(new Cat()));
        System.out.println(getObjectType(new Tiger()));
        System.out.println(getObjectType(new Lion()));
        System.out.println(getObjectType(new Bull()));
        System.out.println(getObjectType(new Cow()));
        System.out.println(getObjectType(new Animal()));
    }

    public static String getObjectType(Object o) {
        //напишите тут ваш код
        String result = "";
        boolean isFind = false;

        if (o instanceof Cat) {
            result = "Кот";
            isFind = true;
        }
        if (o instanceof Tiger) {
            result = "Тигр";
            isFind = true;
        }
        if (o instanceof Lion) {
            result = "Лев";
            isFind = true;
        }
        if (o instanceof Bull) {
            result = "Бык";
            isFind = true;
        }
        if (o instanceof Cow) {
            result = "Корова";
            isFind = true;
        }
        if (o instanceof Animal && !isFind) {
            result = "Животное";
        }

        return result;
    }

    public static class Cat extends Animal   //<--Классы наследуются!!
    {
    }

    public static class Tiger extends Cat {
    }

    public static class Lion extends Cat {
    }

    public static class Bull extends Animal {
    }

    public static class Cow extends Animal {
    }

    public static class Animal {
    }
}
