package com.example.demo.student;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
@EqualsAndHashCode
@NoArgsConstructor
public class Student {


    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int age;
    private double gpa;
    private String major;

    public Student(String name) {

        this.name = name;
    }

    public Student(int id, String name, int age, double gpa, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.major = major;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}