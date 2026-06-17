package com.studentApp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
    private String name;
    private int age;
    private String studentId;
    private final List<String> courses;

    // ✅ static = one shared set across ALL Student objects
    private static final Set<String> existingStudentIds = new HashSet<>();

    // ✅ static method so Main can reset it cleanly
    public static void clearStudentRegistry() {
        existingStudentIds.clear();
    }

    public Student(String name, int age, String studentId) {
        // ✅ throw is now in the ELSE (when validation FAILS)
        if (!validateAgeLimit(age) || !validateNamePattern(name) || !validateStudentId(studentId)) {
            throw new IllegalArgumentException("Invalid student data. Student was not created.");
        }
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        this.courses = new ArrayList<>();
    }

    private boolean validateStudentId(String studentId) {
        String studentIdRegex = "S-\\d+$";
        Pattern studentIdPattern = Pattern.compile(studentIdRegex);
        Matcher studentIdMatcher = studentIdPattern.matcher(studentId);

        if (!studentIdMatcher.matches()) {
            System.err.println("Student ID is not in the correct pattern. Should be like S-123");
            return false;
        }

        if (!existingStudentIds.add(studentId)) {
            System.err.println("Student ID " + studentId + " already exists. Must be unique.");
            return false;
        }

        return true;
    }

    public void enrollCourse(String course) {
        if (validateCourses(course)) {
            if (!courses.contains(course)) {
                courses.add(course);
            } else {
                System.err.println(course + " Course is already added");
            }
        }
    }

    public void printStudentInfo() {
        System.out.println("*********Student Information*********");
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
        System.out.println("Student ID: " + studentId);
        System.out.println("Enrolled in: " + courses);
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", studentId=" + studentId + ", courses=" + courses + "]";
    }

    public Boolean validateAgeLimit(int age) {
        if (age >= 19 && age <= 35) {
            return true;
        } else {
            System.err.println("Age is outside limit. Please enter age between 19 and 35");
            return false;
        }
    }

    public Boolean validateNamePattern(String name) {
        String nameRegex = "^[A-Za-z\\s]+$";
        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher nameMatcher = namePattern.matcher(name);
        if (nameMatcher.matches()) {
            return true;
        } else {
            System.err.println("Invalid name! Please enter a name with alphabets only");
            return false;
        }
    }

    public Boolean validateCourses(String course) {
        if (course.equalsIgnoreCase("Java") || course.equalsIgnoreCase("Devops") || course.equalsIgnoreCase("Python")) {
            return true;
        } else {
            System.err.println("Please select valid courses [Java, Dev Ops, Python]. " + course + " is not offered");
            return false;
        }
    }

    public String getName()          { return name; }
    public int getAge()              { return age; }
    public String getStudentId()     { return studentId; }
    public List<String> getCourses() { return courses; }
}