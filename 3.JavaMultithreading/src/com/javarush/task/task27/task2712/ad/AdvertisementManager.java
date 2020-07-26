package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {

        List<Advertisement> videos = storage.list();
        if (storage.list().isEmpty())
            throw new NoVideoAvailableException();

        // ищем список видео для показа согласно критериям
        List<Advertisement> bestAds = new VideoHelper().findAllYouNeed();

        // сортируем полученный список
        Collections.sort(bestAds, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement video1, Advertisement video2) {
                long dif = video2.getAmountPerOneDisplaying() - video1.getAmountPerOneDisplaying();
                if (dif == 0) dif = video2.getDuration() - video1.getDuration();
                return (int) dif;
            }
        });
        long amount = 0;
        int totalDuration = 0;
        for (Advertisement ad : bestAds) {
            totalDuration += ad.getDuration();
            amount += ad.getAmountPerOneDisplaying();
        }
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(bestAds, amount, totalDuration));


        // выводим список
        for (Advertisement ad : bestAds) {
            ConsoleHelper.writeMessage(ad.getName() + " is displaying... " +
                ad.getAmountPerOneDisplaying() + ", " +
                1000 * ad.getAmountPerOneDisplaying() / ad.getDuration());
            ad.revalidate();
        }
    }

    private class VideoHelper {
        private int bestPrice = 0;
        private int maxTime = 0;
        private int numberOfClips = 0;
        private List<Advertisement> bestAds = new ArrayList<>();
        private List<Advertisement> candidates = new ArrayList<>();

        public List<Advertisement> findAllYouNeed() {
            // отбор кандидатов
            for (Advertisement ad : storage.list()) {
                if (ad.getDuration() <= timeSeconds && ad.getHits() > 0)
                    candidates.add(ad);
            }
            if (candidates.isEmpty()) {
                throw new NoVideoAvailableException();
            } else findBestAds(new BinaryPattern(candidates.size()));
            return bestAds;
        }

        // рекурсивная функция формирования списка для показа
        public void findBestAds(BinaryPattern pattern) {
            while (true) {
                checkAds(pattern.getPattern());
                if (!pattern.full()) pattern.increment();
                else break;
                findBestAds(pattern);
            }
        }

        // проверка очередного набора видеоклипов
        private void checkAds(int[] pattern) {
            int price = 0;
            int time = 0;
            List<Advertisement> list = new ArrayList<>();
            for (int i = 0; i < candidates.size(); i++) {
                price += candidates.get(i).getAmountPerOneDisplaying() * pattern[i];
                time += candidates.get(i).getDuration() * pattern[i];
                if (pattern[i] == 1) list.add(candidates.get(i));
            }
            if (time > timeSeconds) return;
            if (!(price > bestPrice)) {
                if (!(price == bestPrice && time > maxTime)) {
                    if (!(price == bestPrice && time == maxTime && list.size() < numberOfClips)) {
                        return;
                    }
                }
            }
            bestAds = list;
            bestPrice = price;
            maxTime = time;
            numberOfClips = list.size();
        }

        // формирование двоичных масок для сбора списка видеоклипов
        private class BinaryPattern {
            private int length;
            private int count;

            public BinaryPattern(int size) {
                this.length = size;
                this.count = 0;
            }

            public int[] getPattern() {
                String regString = Integer.toBinaryString(count);
                int dif = length - regString.length();
                int[] pattern = new int[length];
                for (int j = dif; j < pattern.length; j++) {
                    if (regString.charAt(j - dif) == '1') pattern[j] = 1;
                    else pattern[j] = 0;
                }
                return pattern;
            }

            public void increment() {
                count++;
            }

            ;

            public boolean full() {
                return count == (int) Math.pow(2, length) - 1;
            }
        }
    }

}
