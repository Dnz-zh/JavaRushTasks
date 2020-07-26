package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {

    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            int cnt = denominations.get(denomination);
            cnt += count;
            denominations.put(denomination, cnt);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            result += entry.getKey() * entry.getValue();
        }
        return result;
    }

    public boolean hasMoney() {
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    //Списать деньги со счета
    /*должен возвращать минимальное количество банкнот, которыми набирается запрашиваемая сумма.*/
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        /*вернет карту HashMap<номинал, количество>.*/
        /*Убедись, что метод withdrawAmount(int expectedAmount) меняет значение denominations в
         манипуляторе (после снятия всей суммы с банкомата isAmountAvailable должен вернуть false).*/
        Map<Integer, Integer> resultMap = new TreeMap<>(Collections.reverseOrder());//Сортировка - от большего номинала к меньшему.
        Map<Integer, Integer> tempMap = new HashMap<>(denominations);
        List<Integer> denominationsKeyList = new ArrayList<>(tempMap.keySet());// список доступных номиналов
        Collections.sort(denominationsKeyList, Collections.<Integer>reverseOrder());
        for (int i = 0; i < denominationsKeyList.size(); i++) {
            int nominal = denominationsKeyList.get(i);// номинал
            int countOfNominal = tempMap.get(nominal);// количество банкнот с доступным номиналом
            int countOfNominalForPut = 0;//  количество банкнот для выдачи

            for (int k = 0; k < countOfNominal; k++) {
                if (expectedAmount >= nominal)// если введенная сумма для съема больше чем номинал отдельной купюры
                {
                    countOfNominalForPut++;
                    expectedAmount -= nominal;
                } else {
                    break;
                }
            }
            if (countOfNominalForPut != 0)// если есть банкноты , которые можно выдать
            {
                if (countOfNominal - countOfNominalForPut == 0)// если можно выплатить клиенту всю сумму исходя из существующего номинала
                {
                    tempMap.remove(nominal);// отдаем клиенту
                } else {
                    tempMap.put(nominal, countOfNominal - countOfNominalForPut);//
                }
                resultMap.put(nominal, countOfNominalForPut);
            }
        }

        if (expectedAmount != 0) throw new NotEnoughMoneyException();// если невозможно выдать сумму

        denominations.clear();
        denominations.putAll(tempMap);

        return resultMap;
    }

}
