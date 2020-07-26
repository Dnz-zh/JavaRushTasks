package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlType(name = "shop")
@XmlRootElement
public class Shop {

    public Goods goods;
    public int count;
    public double profit;
    @XmlElement
    public String[] secretData;

    public static class Goods {
        @XmlElement
        List<String> names = new ArrayList<>();

        @Override
        public String toString() {
            return "Goods{" +
                "names=" + names +
                '}';
        }
    }

    @Override
    public String toString() {
        return "Shop{" +
            "goods=" + goods.names +
            ", count=" + count +
            ", profit=" + profit +
            ", secretData=" + Arrays.toString(secretData) +
            '}';
    }
}
