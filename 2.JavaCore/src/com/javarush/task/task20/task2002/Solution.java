package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            User user = new User();
            user.setFirstName("Aleksandr");
            user.setLastName("Aleksandrov");
            user.setBirthDate(dateFormat.parse("23.02.1984"));
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user);
            user = new User();
            user.setFirstName("Julia");
            user.setLastName("Aleksandrova");
            user.setBirthDate(dateFormat.parse("11.05.2011"));
            user.setMale(false);
            user.setCountry(User.Country.OTHER);
            javaRush.users.add(user);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            if (javaRush.equals(loadedObject)) {
                System.out.println("Equals");
            } else {
                System.out.println("Not equals");
            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(outputStream));
            out.writeBoolean(users.isEmpty());
            out.flush();
            if (!users.isEmpty()) {
                out.writeInt(users.size());
                out.flush();
                for (User user : users) {
                    out.writeUTF(user.getFirstName());
                    out.writeUTF(user.getLastName());
                    out.writeLong(user.getBirthDate().getTime());
                    out.writeBoolean(user.isMale());
                    out.writeUTF(user.getCountry().name());
                    out.flush();
                }
            }
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            DataInputStream in = new DataInputStream(
                new BufferedInputStream(inputStream));
            boolean isUsersEmpty = in.readBoolean();
            if (!isUsersEmpty) {
                int cnt = in.readInt();
                for (int i = 0; i < cnt; i++) {
                    User user = new User();
                    user.setFirstName(in.readUTF());
                    user.setLastName(in.readUTF());
                    user.setBirthDate(new Date(in.readLong()));
                    user.setMale(in.readBoolean());
                    user.setCountry(User.Country.valueOf(in.readUTF()));
                    users.add(user);
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
