package com.javarush.task.task25.task2503;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public enum Column implements Columnable {
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Задает новый порядок отображения колонок, который хранится в массиве realOrder.
     * realOrder[индекс в энуме] = порядок отображения; -1, если колонка не отображается.
     *
     * @param newOrder новая последовательность колонок, в которой они будут отображаться в таблице
     * @throws IllegalArgumentException при дубликате колонки
     */
    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    /**
     * Вычисляет и возвращает список отображаемых колонок в сконфигурированом порядке (см. метод configureColumns)
     * Используется поле realOrder.
     *
     * @return список колонок
     */
    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>();
        /*
        с таким решение валидатор пропустил решение, но решение корявое,
        лучше использовать лямбда функции.
        for (int aRealOrder : realOrder) {
            if (aRealOrder != -1) {
                result.add(null);
            }
        }
        */
        /*
        for (int i = 0; i < realOrder.length; i++) {
            if (values()[i].isShown()) {
                result.set(realOrder[i], values()[i]);
            }
        }
        return result;
        */
        for (int i = 0; i < realOrder.length; i++) {
            if (values()[i].isShown()) {
                result.add(values()[i]);
            }
        }
        return result.stream()
            .sorted(Comparator.comparingInt(e -> realOrder[e.ordinal()]))
            .collect(Collectors.toList());
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public boolean isShown() {
        return realOrder[ordinal()] != -1;
    }

    @Override
    public void hide() {
        for (int i = 0; i < realOrder.length; i++) {
            if (realOrder[i] > realOrder[ordinal()]) {
                realOrder[i]--;
            }
        }
        realOrder[ordinal()] = -1;
    }
}
