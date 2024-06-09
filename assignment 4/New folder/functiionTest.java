package twentyfour.spring.oop.group2.lesson4.assignment1.m23w7314;

import javax.sound.midi.SysexMessage;

public class functiionTest {
    public static void main(String[] args) {
        myOwnmethod(4);
        method1(15);
        method2(false, 20, "Bishwas");

        System.out.println(add(2,3));
        System.out.println(add(2,3,4));


    }

    public static void myOwnmethod(int no)
    {
        for (int i=1;i<=no;i++)
        System.out.print("Hello  ");
    }

    public static void method1(int value1)
    {
        System.out.println("\nThe value passed is  "+ value1);
    }

    public static void method2(boolean check, int age, String name)
    {
        System.out.println("Are you student "+check+". My age is "+ age+". My name is "+ name);
    }

    public static int add(int a, int b)
    {
        return a+b;
    }

    public static int add(int a, int b, int c)
    {
        return a+b+c;
    }
}
