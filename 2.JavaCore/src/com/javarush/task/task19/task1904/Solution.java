package com.javarush.task.task19.task1904;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {
        try {
            PersonScannerAdapter personScannerAdapter = new PersonScannerAdapter(
                new Scanner(new FileInputStream("file.dat")));
            List<Person> people = new ArrayList<>();
            Person person;
            while ( (person = personScannerAdapter.read()) != null) {
                people.add(person);
            }
            for (Person pers : people) {
                System.out.println(pers);
            }
            personScannerAdapter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class PersonScannerAdapter implements PersonScanner {

        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            if (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                String[] parseData = data.split(" ");
                System.out.println(data);
                if (parseData.length != 6) {
                    return null;
                }
                String dateStr = parseData[3] + "." + parseData[4] + "." + parseData[5];
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date date;
                try {
                    date = dateFormat.parse(dateStr);
                } catch (ParseException e) {
                    return null;
                }
                return new Person(parseData[1], parseData[2], parseData[0], date);
            } else {
                return null;
            }
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
