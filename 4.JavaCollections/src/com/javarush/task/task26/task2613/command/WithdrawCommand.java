package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {

    private ResourceBundle res= ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                int sum = Integer.parseInt(ConsoleHelper.readString());
                if (sum <= 0) {
                    throw new NumberFormatException();
                }
                if (!currencyManipulator.isAmountAvailable(sum)) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
                Map<Integer, Integer> currencyMap = currencyManipulator.withdrawAmount(sum);
                for (Map.Entry<Integer, Integer> pair : currencyMap.entrySet()) {
                    ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, currencyCode));
                break;

            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            } catch (ConcurrentModificationException ignore) {
            }
        }
    }

}
