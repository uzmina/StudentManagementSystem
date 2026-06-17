package com.studentApp;

import java.util.*;

public class Main {
    public static List<Student> studentList;
    public static void main(String[] args) {
        System.out.println("**************Student Management System**************");

        Student.clearStudentRegistry(); // ✅ fresh start every run
        studentList = new ArrayList<>();

        addStudent("John Doe", 19, "S-101");
        addStudent("Jane", 19, "S-102");
        addStudent("Dan", 28, "S-103");
        addStudent("Dane", 22, "S-100");
        addStudent("Uz", 35, "S-103"); //should throw error

        // Enroll courses after students are created
        if (!studentList.isEmpty()) studentList.getFirst().enrollCourse("Java");
        if (!studentList.isEmpty()) studentList.get(0).enrollCourse("Dev Ops"); // ✅ capital O
        if (!studentList.isEmpty()) studentList.get(0).enrollCourse("Java");
        if (!studentList.isEmpty()) studentList.get(1).enrollCourse("Java");
        if (studentList.size() > 2) studentList.get(2).enrollCourse("Java");


      Student result =  findStudentById("S-103");
      System.out.println("Results "+ result);

      sortByName();
      sortByStudentId();
      sortByAge();



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
        /* Comparator class has method called compare which will compare two object lexographically(in order of alphabet from Student object)
        * Collection call will go through studentList array to sort by getName method
        * once it is sorted student list will be printed alphabetically
        *  */
//        Comparator<Student> studentNameComparator = new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        };
        //same code can be written in Lambda
        Comparator<Student> studentNameComparator = (o1,o2) -> o1.getName().compareTo(o2.getName());
        Collections.sort(studentList,studentNameComparator);
        System.out.println(studentList);
    }

    public static Student findStudentById(String studentId) {
        // what is happening here , we give a list (studentList) to stream which will filter based on the filter studentId,
        // it will search for studentId provided
        // findFirst will return the object found first with that student id by filtering through the studentList and
        // store that in results variable which will in turn will be stored in result variable(under main method)
        // and print when method findStudentById is called in main method
        // else if it does not find the student id then will throw a run time exception
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

    private static void addStudent(String name, int age, String studentId) {
        try {
            Student student = new Student(name, age, studentId);
            studentList.add(student);
        } catch (IllegalArgumentException e) {
            System.err.println("Student not added: " + e.getMessage());
        }
    }
}
