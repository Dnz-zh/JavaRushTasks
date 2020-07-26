package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StatisticManager {
    private static StatisticManager getInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

    public static StatisticManager getInstance() {
        return getInstance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }

    public Map<Date, Double> amountPerDay() {
        Map<Date, Double> result = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> eventDataRowList = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        double amount;
        Date date;
        Calendar calendar;
        for (EventDataRow eventDataRow : eventDataRowList) {
            VideoSelectedEventDataRow adVideo = (VideoSelectedEventDataRow) eventDataRow;
            amount = adVideo.getAmount() / 100.0;
            calendar = Calendar.getInstance();
            calendar.setTime(adVideo.getDate());
            GregorianCalendar gc = new GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            );
            date = gc.getTime();
            if (result.containsKey(date))
                amount += result.get(date);
            result.put(date, amount);
        }
        return result;
    }


    public Map<Date, Map<String, Integer>> cookLoading() {
        List<EventDataRow> eventDataRows = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        Map<Date, Map<String, Integer>> result = new TreeMap<>(Collections.reverseOrder());
        Date date;
        Calendar calendar;
        for (EventDataRow eventDataRow : eventDataRows) {
            CookedOrderEventDataRow cookedOrder = (CookedOrderEventDataRow) eventDataRow;
            calendar = Calendar.getInstance();
            calendar.setTime(cookedOrder.getDate());
            GregorianCalendar gc = new GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            );
            date = gc.getTime();
            if (result.containsKey(date)) {
                Map<String, Integer> cook = result.get(date);
                int value;
                if (cook.containsKey(cookedOrder.getCookName())) {
                    value = cook.get(cookedOrder.getCookName());
                    value += Math.toIntExact(Math.round(cookedOrder.getTime() / 60.0));
                } else {
                    value = Math.toIntExact(Math.round(cookedOrder.getTime() / 60.0));
                }
                cook.put(cookedOrder.getCookName(), value);
            } else {
                Map<String, Integer> cook = new TreeMap<>();
                cook.put(cookedOrder.getCookName(), Math.toIntExact(Math.round(cookedOrder.getTime() / 60.0)));
                result.put(date, cook);
            }
        }
        return result;
    }
}
