package com.sms.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String course;
    private int semester;
    private double marks; // CGPA or marks

    public Student() {
    }

    public Student(String id, String name, int age, String gender, String phoneNumber, String email, String address, String course, int semester, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.course = course;
        this.semester = semester;
        this.marks = marks;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getCourse() { return course; }
    public int getSemester() { return semester; }
    public double getMarks() { return marks; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setCourse(String course) { this.course = course; }
    public void setSemester(int semester) { this.semester = semester; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
