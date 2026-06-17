package com.studentApp;

import java.util.*;

public class Main2 {
    public static List<Student> studentList;
    public static void main(String[] args) {
        System.out.println("**************Student Management System**************");

        Student.clearStudentRegistry(); // ✅ fresh start every run
        studentList = new ArrayList<>();
        //input from console
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student Name ...");
        String name = scanner.next();//if you want to read the input in the String format use next() method
        System.out.println("You have entered name as  "+ name);
        System.out.println("Enter Student Age...");
        int age = scanner.nextInt();
        System.out.println("Student Age is... " + age);






       // Student result =  findStudentById("S-103");
        //System.out.println("Results "+ result);

        //sortByName();
        //sortByStudentId();
       // sortByAge();



    }

    // ✅ Correct - compares two ages against each other
    private static void sortByAge() {
        Comparator<Student> studentAgeComparator = (o1, o2) -> Integer.compare(o1.getAge(), o2.getAge());
        Collections.sort(studentList, studentAgeComparator);
        System.out.println(studentList);
    }

    private static void sortByStudentId() {
        Comparator<Student> studentIdComparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getStudentId().compareTo(o2.getStudentId());
            }
        };
        Collections.sort(studentList,studentIdComparator);
        System.out.println(studentList);
    }

    private static void sortByName() {
        Comparator<Student> studentNameComparator = (o1,o2) -> o1.getName().compareTo(o2.getName());
        Collections.sort(studentList,studentNameComparator);
        System.out.println(studentList);
    }

    public static Student findStudentById(String studentId) {
        Student results = null; // since Student is non-primitive data type it has to be initialized to default value
        try {
            results = studentList.stream().filter(x -> x.getStudentId().equalsIgnoreCase(studentId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No data found"));


        } catch (RuntimeException e) {
            System.err.println("StudentId is not found");
        }
        return results;
    }

}
