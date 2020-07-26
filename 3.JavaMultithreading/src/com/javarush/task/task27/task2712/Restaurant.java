package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Ogima");
        cook1.setQueue(orderQueue);
        cook2.setQueue(orderQueue);
        Thread cook1Thread=new Thread(cook1);
        cook1Thread.start();
        Thread cook2Thread=new Thread(cook2);
        cook2Thread.start();
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            //tablet.addObserver(orderManager);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }
        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);
        RandomOrderGeneratorTask generatorTask = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(generatorTask);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        thread.interrupt();
        cook1Thread.interrupt();
        cook2Thread.interrupt();
        try {
            cook1Thread.join();
            cook2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
