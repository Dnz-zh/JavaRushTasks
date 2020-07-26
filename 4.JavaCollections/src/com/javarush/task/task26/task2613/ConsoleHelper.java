package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private static ResourceBundle res= ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"common_en");
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {

        String message = null;
        try {
            message = bis.readLine();
        } catch (Exception e) {
        }
        if (message != null) {
            if ("EXIT".equals(message.toUpperCase())) {
                ConsoleHelper.writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        }
        return message;

    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String code;
        writeMessage(res.getString("choose.currency.code"));
        while (true) {
            code = readString();
            if (code.length() == 3)
                break;
            else
                writeMessage(res.getString("invalid.data"));

        }
        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));

        String[] input;
        while (true) {
            input = readString().split(" ");

            int nominal = 0;
            int total = 0;
            try {
                nominal = Integer.parseInt(input[0]);
                total = Integer.parseInt(input[1]);
            } catch (Exception e) {
                writeMessage(res.getString("Please specify valid data"));
                continue;
            }
            if (nominal <= 0 || total <= 0) {
                writeMessage(res.getString("Please specify valid data"));
                continue;
            }
            break;
        }
        return input;

    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            try {
                writeMessage(res.getString("choose.operation") +
                    " (1 - " + res.getString("operation.INFO") +
                    ", 2 — " + res.getString("operation.DEPOSIT") +
                    ", 3 — " + res.getString("operation.WITHDRAW") +
                    ", 4 — " + res.getString("operation.EXIT") + "):");
                return Operation.getAllowableOperationByOrdinal(Integer.valueOf(readString()));
            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect operation");
            }
        }
    }

    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));

    }

}
