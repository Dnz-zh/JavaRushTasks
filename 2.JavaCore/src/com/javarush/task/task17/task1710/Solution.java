package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        if (args.length != 0) {
            String action = args[0];
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",
                Locale.ENGLISH);
            switch (action.toLowerCase()) {
                case "-c":
                    if ((args.length - 1) % 3 == 0) {
                        try {
                            if ("м".equals(args[2].toLowerCase())) {
                                allPeople.add(Person.createMale(
                                    args[1],
                                    sdf.parse(args[3])
                                    )
                                );
                            }
                            if ("ж".equals(args[2].toLowerCase())) {
                                allPeople.add(Person.createFemale(
                                    args[1],
                                    sdf.parse(args[3])
                                    )
                                );
                            }
                        } catch (ParseException err) {
                            err.printStackTrace();
                        }
                        System.out.println(allPeople.size() - 1);
                    }
                    break;
                case "-u":
                    if ((args.length - 1) % 4 == 0) {
                        try {
                            int index = Integer.parseInt(args[1]);
                            if (index >= 0 && index < allPeople.size()) {
                                Person person = allPeople.get(index);
                                Sex sex = null;
                                if ("м".equals(args[3].toLowerCase())) {
                                    sex = Sex.MALE;
                                }
                                if ("ж".equals(args[3].toLowerCase())) {
                                    sex = Sex.FEMALE;
                                }
                                if (sex == null) return;
                                person.setName(args[2]);
                                person.setSex(sex);
                                person.setBirthDay(sdf.parse(args[4]));
                            }
                        } catch (NumberFormatException | ParseException err) {
                            err.printStackTrace();
                        }
                    }
                    break;
                case "-d":
                    if (args.length - 1 == 1) {
                        try {
                            int index = Integer.parseInt(args[1]);
                            if (index >= 0 && index < allPeople.size()) {
                                Person person = allPeople.get(index);
                                person.setName(null);
                                person.setSex(null);
                                person.setBirthDay(null);
                            }
                        } catch (NumberFormatException err) {
                            err.printStackTrace();
                        }
                    }
                    break;
                case "-i":
                    if (args.length - 1 == 1) {
                        try {
                            int index = Integer.parseInt(args[1]);
                            if (index >= 0 && index < allPeople.size()) {
                                SimpleDateFormat sdf1 = new SimpleDateFormat(
                                    "dd-MMM-yyyy",
                                    Locale.ENGLISH
                                );
                                Person person = allPeople.get(index);
                                StringBuilder sb = new StringBuilder();
                                sb.append(person.getName()).append(" ");
                                switch (person.getSex()) {
                                    case MALE:
                                        sb.append("м").append(" ");
                                        break;
                                    case FEMALE:
                                        sb.append("ж").append(" ");
                                        break;
                                }
                                sb.append(sdf1.format(person.getBirthDay()));
                                System.out.println(sb.toString());
                            }
                        } catch (NumberFormatException err) {
                            err.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }
}
