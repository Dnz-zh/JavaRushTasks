package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        if (args.length != 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",
                Locale.ENGLISH);
            switch (args[0]) {
                case "-c":
                    synchronized (allPeople) {
                        if ((args.length - 1) % 3 == 0) {
                            int counter = 1;
                            while (counter < args.length) {
                                try {
                                    if ("м".equals(args[counter + 1].toLowerCase())) {
                                        allPeople.add(Person.createMale(
                                            args[counter],
                                            sdf.parse(args[counter + 2])
                                            )
                                        );
                                    }
                                    if ("ж".equals(args[counter + 1].toLowerCase())) {
                                        allPeople.add(Person.createFemale(
                                            args[counter],
                                            sdf.parse(args[counter + 2])
                                            )
                                        );
                                    }
                                    System.out.println(allPeople.size() - 1);
                                    counter += 3;
                                } catch (ParseException err) {
                                    err.printStackTrace();
                                }
                            }
                        }
                        break;
                    }
                case "-u":
                    synchronized (allPeople) {
                        if ((args.length - 1) % 4 == 0) {
                            int counter = 1;
                            try {
                                while (counter < args.length) {
                                    int index = Integer.parseInt(args[counter]);
                                    if (index >= 0 && index < allPeople.size()) {
                                        Person person = allPeople.get(index);
                                        Sex sex = null;
                                        if ("м".equals(args[counter + 2].toLowerCase())) {
                                            sex = Sex.MALE;
                                        }
                                        if ("ж".equals(args[counter + 2].toLowerCase())) {
                                            sex = Sex.FEMALE;
                                        }
                                        if (sex == null) return;
                                        person.setName(args[counter + 1]);
                                        person.setSex(sex);
                                        person.setBirthDay(sdf.parse(args[counter + 3]));
                                    }
                                    counter += 4;
                                }
                            } catch (NumberFormatException | ParseException err) {
                                err.printStackTrace();
                            }
                        }
                    }
                    break;
                case "-d":
                    synchronized (allPeople) {
                        try {
                            int counter = 1;
                            while (counter < args.length) {
                                int index = Integer.parseInt(args[counter]);
                                if (index >= 0 && index < allPeople.size()) {
                                    Person person = allPeople.get(index);
                                    person.setName(null);
                                    person.setSex(null);
                                    person.setBirthDay(null);
                                }
                                counter++;
                            }
                        } catch (NumberFormatException err) {
                            err.printStackTrace();
                        }
                        break;
                    }
                case "-i":
                    synchronized (allPeople) {
                        try {
                            int counter = 1;
                            while (counter < args.length) {
                                int index = Integer.parseInt(args[counter]);
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
                                counter++;
                            }
                        } catch (NumberFormatException err) {
                            err.printStackTrace();
                        }
                    }
            }
        }
    }
}
