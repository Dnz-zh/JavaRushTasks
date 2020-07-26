package com.javarush.task.task26.task2613;

public enum Operation {
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        Operation result;
        if (i == 0) {
            throw new IllegalArgumentException();
        }
        try {
            result = Operation.values()[i];
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
