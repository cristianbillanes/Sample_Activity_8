package com.example.sampleactivity_8;

public class Student {
    private String name;
    private int age;
    private String course;
    private int year;
    public Student(){}

    public Student(String name, int age, String course, int year) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.year = year;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
