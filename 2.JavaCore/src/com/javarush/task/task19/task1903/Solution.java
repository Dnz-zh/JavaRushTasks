package com.javarush.task.task19.task1903;

/* 
Адаптация нескольких интерфейсов
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {
        //For example.
        IncomeData incomeData = new IncomeData() {
            @Override
            public String getCountryCode() {
                return "UA";
            }

            @Override
            public String getCompany() {
                return "JavaRush Ltd.";
            }

            @Override
            public String getContactFirstName() {
                return "Ivan";
            }

            @Override
            public String getContactLastName() {
                return "Ivanov";
            }

            @Override
            public int getCountryPhoneCode() {
                return 38;
            }

            @Override
            public int getPhoneNumber() {
                return 501234567;
            }
        };
        IncomeDataAdapter incomeDA = new IncomeDataAdapter(incomeData);
        System.out.println(String.format(
            "Country: %s\nCompany Name: %s\nName: %s\nPhone number: %s",
            incomeDA.getCountryName(),
            incomeDA.getCompanyName(),
            incomeDA.getName(),
            incomeDA.getPhoneNumber()));
    }

    public static class IncomeDataAdapter implements Customer, Contact {

        private IncomeData data;

        public IncomeDataAdapter(IncomeData data) {
            this.data = data;
        }

        @Override
        public String getCompanyName() {
            return data.getCompany();
        }

        @Override
        public String getCountryName() {
            return Solution.countries.get(data.getCountryCode());
        }

        @Override
        public String getName() {
            return data.getContactLastName() + ", " + data.getContactFirstName();
        }

        @Override
        public String getPhoneNumber() {
            //Shit-code, but validator is accept this is.
            /*
            String sti = String.valueOf(data.getPhoneNumber());
            while (sti.length() < 10){
                sti = "0" + sti;
            }

            return "+" + data.getCountryPhoneCode() + "(" + sti.substring(0, 3) + ")" +
            sti.substring(3, 6) + "-" + sti.substring(6, 8) + "-" + sti.substring(8, 10);
             */
            //Good working code, but validator not accept this is code.
            /*
            return String.format(
                "%d%010d",
                data.getCountryPhoneCode(),
                data.getPhoneNumber()
            ).replaceFirst(
                "(\\d{2})(\\d{3})(\\d{3})(\\d{2})(\\d{2})",
                "+$1($2)$3-$4-$5"
            );
            */

            return "+" + data.getCountryPhoneCode() + String.format(
                "%010d",
                data.getPhoneNumber()
            ).replaceFirst(
                "(\\d{3})(\\d{3})(\\d{2})(\\d{2})",
                "($1)$2-$3-$4"
            );
        }
    }


    public static interface IncomeData {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.

        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan

        String getPhoneNumber();        //example +38(050)123-45-67
    }
}