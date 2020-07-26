package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.Solution;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeForGettingIds(Shortener shortener,
        Set<String> strings, Set<Long> ids) {
        Date start = new Date();
        ids = Solution.getIds(shortener, strings);
        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener,
        Set<Long> ids, Set<String> strings) {
        Date start = new Date();
        strings = Solution.getStrings(shortener, ids);
        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();
        long timeIds1 = getTimeForGettingIds(shortener1, origStrings, ids1);
        long timeIds2 = getTimeForGettingIds(shortener2, origStrings, ids2);
        Assert.assertTrue(timeIds1 > timeIds2);
        Set<String> strings1 = new HashSet<>();
        Set<String> strings2 = new HashSet<>();
        long timeStrings1 = getTimeForGettingStrings(shortener1, ids1, strings1);
        long timeStrings2 = getTimeForGettingStrings(shortener2, ids2, strings2);
        Assert.assertEquals(timeStrings1, timeStrings2, 30);
    }

}
