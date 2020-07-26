package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize = -1;
    private int maxSize = -1;
    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        String strContent = new String(content);
        byte flags = 0;
        byte fileEqualsFlag = 0;
        if (partOfName == null && partOfContent == null && minSize == -1 && maxSize == -1) {
            flags = -1;
        }
        if (partOfName != null && !partOfName.isEmpty()) {
            flags += 1;
            if (file.toString().toLowerCase().contains(partOfName.toLowerCase())) {
                fileEqualsFlag += 1;
            }
        }
        if (partOfContent != null && !partOfContent.isEmpty()) {
            flags += 2;
            if (strContent.toLowerCase().contains(partOfContent.toLowerCase())) {
                fileEqualsFlag += 2;
            }
        }
        if (minSize != -1) {
            flags += 4;
            if (content.length >= minSize) {
                fileEqualsFlag += 4;
            }
        }
        if (maxSize != -1) {
            flags += 8;
            if (content.length <= maxSize) {
                fileEqualsFlag += 8;
            }
        }
        if (flags == fileEqualsFlag) {
            foundFiles.add(file);
        }
        return super.visitFile(file, attrs);
    }


    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
