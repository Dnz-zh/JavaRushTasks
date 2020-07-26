package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        Path dir = Paths.get(fileName);
        if (!Files.isDirectory(dir)) {
            System.out.println(dir.toString() + " - не папка");
            return;
        }
        Map<String, Long> result = new HashMap<>();
        result.put("directoryCount", -1L);
        result.put("filesCount", 0L);
        result.put("directorySize", 0L);
        Files.walkFileTree(dir, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (attrs.isDirectory()) {
                    long value = result.get("directoryCount");
                    result.put("directoryCount", ++value);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (attrs.isRegularFile()) {
                    long value = result.get("filesCount");
                    result.put("filesCount", ++value);
                    value = result.get("directorySize");
                    value += attrs.size();
                    result.put("directorySize", value);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.SKIP_SUBTREE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        System.out.println("Всего папок - " + result.get("directoryCount"));
        System.out.println("Всего файлов - " + result.get("filesCount"));
        System.out.println("Общий размер - " + result.get("directorySize"));
    }
}
