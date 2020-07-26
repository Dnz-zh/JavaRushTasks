package com.javarush.task.task38.task3804;

public class ExceptionFactory {

    public static Throwable getException(Enum enumException) {
        if (enumException == null) return new IllegalArgumentException();
        String result = enumException.name().toLowerCase().replace("_", " ");
        result = result.replaceFirst(String.valueOf(result.charAt(0)), String.valueOf(result.toUpperCase().charAt(0)));
        Throwable resultThrowable;
        if (enumException instanceof ExceptionApplicationMessage) {
            resultThrowable = new Exception(result);
        } else if (enumException instanceof ExceptionDBMessage) {
            resultThrowable = new RuntimeException(result);
        } else if (enumException instanceof ExceptionUserMessage) {
            resultThrowable = new Error(result);
        } else {
            resultThrowable = new IllegalArgumentException();
        }
        return resultThrowable;
    }

}
