package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student studentWithAverageGrade = null;
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) {
                studentWithAverageGrade = student;
                break;
            }
        }
        return studentWithAverageGrade;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        double max = 0.0;
        Student studentWithMaxAverageGrade = null;
        for (Student student : students) {
            if (student.getAverageGrade() > max) {
                max = student.getAverageGrade();
                studentWithMaxAverageGrade = student;
            }
        }
        return studentWithMaxAverageGrade;
    }

    public Student getStudentWithMinAverageGrade() {
        double min = Double.MAX_VALUE;
        Student studentWithMinAverageGrade = null;
        for (Student student : students) {
            if (student.getAverageGrade() < min) {
                min = student.getAverageGrade();
                studentWithMinAverageGrade = student;
            }
        }
        return studentWithMinAverageGrade;
    }

    public void expel(Student student) {
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}