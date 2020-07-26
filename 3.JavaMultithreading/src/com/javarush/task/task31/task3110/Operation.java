package com.javarush.task.task31.task3110;

public enum Operation {
    CREATE("упаковать файлы в архив"),
    ADD("добавить файл в архив"),
    REMOVE("удалить файл из архива"),
    EXTRACT("распаковать архив"),
    CONTENT("просмотреть содержимое архива"),
    EXIT("выход");

    private String name;

    Operation(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
