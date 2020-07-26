package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int sizes = 0;
        for (List<V> values : map.values()) {
            sizes += values.size();
        }
        return sizes;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        List<V> values;
        if (map.containsKey(key)) {
            values = map.get(key);
            V val = values.get(values.size() - 1);
            if (values.size() < repeatCount) {
                values.add(value);
            } else if (values.size() == repeatCount) {
                values.remove(0);
                values.add(value);
            }
            map.put(key, values);
            return val;
        } else {
            values = new ArrayList<>();
            values.add(value);
            map.put(key, values);
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        V result = null;
        if (map.containsKey((K) key)) {
            List<V> values = map.get((K) key);
            if (values.size() > 0) {
                result = values.get(0);
                values.remove(0);
                if (values.size() == 0) {
                    map.remove(key);
                } else {
                    map.put((K) key, values);
                }
            } else {
                map.remove(key);
            }
        }
        return result;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        ArrayList<V> arrayList = new ArrayList<>();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            arrayList.addAll(entry.getValue());
        }
        return arrayList;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        for (List<V> values : map.values()) {
            if (values.contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}