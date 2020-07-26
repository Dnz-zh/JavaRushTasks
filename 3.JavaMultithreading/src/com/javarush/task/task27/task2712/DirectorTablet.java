package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DirectorTablet {

    public void printAdvertisementProfit() {
        Map<Date, Double> advertisementProfit = StatisticManager.getInstance().amountPerDay();
        double totalAmount = 0;
        double amount;
        for (Map.Entry<Date, Double> entry : advertisementProfit.entrySet()) {
            amount = entry.getValue();
            ConsoleHelper.writeMessage(String.format("%1$te-%1$tb-%1$tY - %2$.2f", entry.getKey(), amount));
            totalAmount += amount;
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", totalAmount));
    }

    public void printCookWorkloading() {
        Map<Date, Map<String, Integer>> map = StatisticManager.getInstance().cookLoading();
        for (Map.Entry<Date, Map<String, Integer>> entry : map.entrySet()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String date = simpleDateFormat.format(entry.getKey());
            ConsoleHelper.writeMessage(date);
            Map<String, Integer> map1 = entry.getValue();
            for (Map.Entry<String, Integer> entry1 : map1.entrySet()) {
                if (entry1.getValue() > 0) {
                    ConsoleHelper.writeMessage(entry1.getKey() + " - " + entry1.getValue() + " min");
                }
            }
        }
    }

    public void printActiveVideoSet() {
        Map<String, Integer> adActiveVideos = StatisticAdvertisementManager.getInstance().getActive();
        for (Map.Entry<String, Integer> entry : adActiveVideos.entrySet()) {
            ConsoleHelper.writeMessage(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void printArchivedVideoSet() {
        List<String> adInactiveVideos = StatisticAdvertisementManager.getInstance().getInactive();
        for (String name : adInactiveVideos) {
            ConsoleHelper.writeMessage(name);
        }
    }
}
