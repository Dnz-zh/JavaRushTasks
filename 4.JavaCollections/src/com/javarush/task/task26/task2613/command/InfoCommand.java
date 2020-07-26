package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command {

    private ResourceBundle res= ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean money = false;

        for (CurrencyManipulator currency : CurrencyManipulatorFactory.getAllCurrencyManipulators())
            if (currency.hasMoney()) {
                if (currency.getTotalAmount() > 0) {
                    ConsoleHelper.writeMessage(currency.getCurrencyCode() + " - " + currency.getTotalAmount());
                    money = true;
                }
            }

        if (!money) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
