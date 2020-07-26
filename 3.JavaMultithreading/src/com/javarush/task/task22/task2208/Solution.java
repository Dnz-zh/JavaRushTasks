package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> query = new HashMap<>();
        query.put("name", null);
        query.put("country", null);
        query.put("city", null);
        query.put("age", null);
        query.put("date", null);
        System.out.println(getQuery(query));
    }
    public static String getQuery(Map<String, String> params) {
        Set<String> keys = params.keySet();
        StringBuilder sb = new StringBuilder();
        for (String str : keys) {
            if (params.get(str) != null) {
                if (!params.get(str).equals("null")) {
                    sb.append(str).append(" = '").append(params.get(str))
                        .append("' and ");
                }
            }
        }
        if (sb.length() != 0) {
            sb.delete(sb.lastIndexOf(" and "), sb.length());
        }
        return sb.toString();
    }
}
