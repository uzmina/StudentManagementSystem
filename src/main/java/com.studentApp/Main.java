package com.studentApp;

import java.util.*;

public class Main {
    public static List<Student> studentList;
    public static Scanner scanner;

    public static void main(String[] args) {
        System.out.println("**************Student Management System**************");

        Student.clearStudentRegistry(); // ✅ fresh start every run
        studentList = new ArrayList<>();
        //input from console
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select Options 1 to 8...");
            System.out.println("1. Register a Student");
            System.out.println("2. Find Student by Student Id");
            System.out.println("3. Find Student by Student Name");
            System.out.println("4. Sort Student by Age");
            System.out.println("5. Sort Student by Name");
            System.out.println("6. Sort Student Student ID");
            System.out.println("7. Print Student details");
            System.out.println("8. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    findStudentById(scanner);
                    break;
                case 3:
                    findStudentByName(scanner);
                    break;
                case 4:
                    sortByAge();
                    break;
                case 5:
                    sortByName();
                    break;
                case 6:
                    sortByStudentId();
                    break;
                case 7:
                    printAllStudentDetail();
                    break;
                case 8:
                    exitApp();
                    break;
                default:
                    System.out.println("Invalid Option selected ...Please select options from 1 to 8");
            }
        }
    }

    private static void printAllStudentDetail() {
        if (!studentList.isEmpty()){
            System.out.println("___________________PRINT ALL STUDENT DETAILS___________________");
            for(Student student:studentList)
                {
                    student.printStudentInfo();
                 }
                System.out.println("----------********----------");
            }else{
                System.err.println("Student list is empty ...no data found");
            }
    }
    //quit
    private static void exitApp() {
        System.exit(0);
    }

    private static void findStudentByName(Scanner scanner3) {
        Student studentFound = null;
        System.out.println("Enter Student name... ");
        String studentName = scanner3.next();
        try {
            studentFound = (studentList.stream().filter(student -> student.getName().equalsIgnoreCase(studentName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No data found")));
        } catch (RuntimeException e) {
            System.err.println("Student Name is not found");
        }
        assert studentFound != null;
        studentFound.printStudentInfo();
    }
    //Registr a student
    private static void addStudent(Scanner scanner1) {
        System.out.println("Enter Student Name ...");
        String name = scanner1.next();
        System.out.println("Enter Student Age...");
        int age = scanner1.nextInt();
        System.out.println("Enter student id.. ");
        String studentId = scanner1.next();
        Student student = new Student(name, age, studentId);
        while(true) {
            System.out.println("Enter course ... To exit type done");
            String courseName = scanner1.next();
            if(courseName.equalsIgnoreCase("done")){
                break;
            }
            student.enrollCourse(courseName);
        }
        try {

            studentList.add(student);
            System.out.println("Student added successfully");
        } catch (IllegalArgumentException e) {
            System.err.println("Student not added: " + e.getMessage());
        }
        student.printStudentInfo();
    }

    // ✅ Correct - compares two ages against each other
    private static void sortByAge() {
        Comparator<Student> studentAgeComparator = (o1, o2) -> Integer.compare(o1.getAge(), o2.getAge());
        studentList.sort(studentAgeComparator);
        System.out.println(studentList);
    }

    private static void sortByStudentId() {
        Comparator<Student> studentIdComparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getStudentId().compareTo(o2.getStudentId());
            }
        };
        studentList.sort(studentIdComparator);
        System.out.println(studentList);
    }

    private static void sortByName() {
        Comparator<Student> studentNameComparator = (o1,o2) -> o1.getName().compareTo(o2.getName());
        studentList.sort(studentNameComparator);
        System.out.println(studentList);
    }

    public static void findStudentById(Scanner scanner2) {
        Student studentFound = null;
        System.out.println("Enter Student ID...");
        String studentId = scanner2.next();
        try {
            studentFound = studentList.stream().filter(student -> student.getStudentId().equalsIgnoreCase(studentId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No data found"));

        } catch (RuntimeException e) {
            System.err.println("StudentId is not found");
        }
        assert studentFound != null;
        studentFound.printStudentInfo();
    }
}
