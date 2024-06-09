package twentyfour.spring.oop.group2.lesson5.assignment1.m23w7314;

public class Student {
    public final String number;
    public String name;
    public String surname;
    public  String major;
    public int year;

    public Student(String number, String name, String surname, int year, String major) {
        this.number = number;
        this.name = name;
        this. surname = surname;
        this.year = year;
        this.major = major;

    }

    public void passYear() {
    this.year++;
    }

}


