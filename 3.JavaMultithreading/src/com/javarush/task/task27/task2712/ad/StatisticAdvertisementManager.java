package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager getInstance = new StatisticAdvertisementManager();
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return getInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public Map<String, Integer> getActive() {
        Map<String, Integer> result = new TreeMap<>();
        List<Advertisement> adList = advertisementStorage.list();
        for (Advertisement ad : adList) {
            if (ad.getHits() > 0) {
                result.put(ad.getName(), ad.getHits());
            }
        }
        return result;
    }

    public List<String> getInactive() {
        List<String> result = new ArrayList<>();
        List<Advertisement> adList = advertisementStorage.list();
        for (Advertisement ad : adList) {
            if (ad.getHits() == 0) {
                result.add(ad.getName());
            }
        }
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        return result;
    }
}
