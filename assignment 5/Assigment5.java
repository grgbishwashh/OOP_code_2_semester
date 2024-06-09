package twentyfour.spring.oop.group2.lesson5.assignment1;

import twentyfour.spring.oop.group2.lesson5.assignment1.m23w7314.Student;

public class Assigment5 {
    public static void main(String[] args) {
        Student john = new Student("M19W000", "John", "Smith", 2020, "Computer networks");
        System.out.println("Student name: " + john.name + " " + "\n" + "Student surname: " + john.surname + "\n" + "Student year:" + john.year);
        john.passYear();
        System.out.println("Student name: " + john.name + " " + "\n" + "Student surname: " + john.surname + "\n" + "Student year:" + john.year);
    }
}
