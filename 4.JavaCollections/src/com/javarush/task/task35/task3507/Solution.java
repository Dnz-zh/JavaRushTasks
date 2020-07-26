package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        String packageName = Solution.class.getPackage().getName() + ".data";//pathToAnimals.substring(pathToAnimals.indexOf("/com") + 1).replaceAll("/", ".");
        Set<Animal> result = new HashSet<>();
        File[] files = new File(pathToAnimals).listFiles();
        for (File file : files) {
            Class clazz = new DynamicClassLoader().load(file.toPath(), packageName);
            int score = 0;
            Class[] interfaces = clazz.getInterfaces();
            for (Class c : interfaces) {
                if (c.getSimpleName().contains("Animal")) {
                    score++;
                    break;
                }
            }
            Constructor[] constructors = clazz.getConstructors();
            for (Constructor constructor : constructors) {
                if (constructor.getParameterCount() == 0) {
                    score++;
                }
            }
            try {
                if (score == 2) {
                    Animal animal = (Animal) clazz.newInstance();
                    result.add(animal);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static class DynamicClassLoader extends ClassLoader {
        public Class<?> load(Path path, String packageName) {
            try {
                String className = packageName + "." + path.getFileName().toString().replace(".class", "");
                byte[] buffer = Files.readAllBytes(path);
                return defineClass(className, buffer, 0, buffer.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
