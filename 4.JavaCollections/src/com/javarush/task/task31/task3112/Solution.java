package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));
        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);
        Path tmpFile =  Files.createTempFile("dwn-", ".txt");
        Files.copy(url.openStream(), tmpFile, StandardCopyOption.REPLACE_EXISTING);
        Path newFile = Paths.get(downloadDirectory.toAbsolutePath().toString() + "/" + Paths.get(url.getFile()).getFileName());
        Files.move(tmpFile, newFile, StandardCopyOption.REPLACE_EXISTING);
        return newFile;
    }
}
