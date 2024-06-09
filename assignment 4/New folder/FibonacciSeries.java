package twentyfour.spring.oop.group2.lesson4.assignment1.m23w7314;
import java.util.Scanner;

/**
 * Id: M23W7314
 * Name: Gurung Bishwas
 * Assignment: KCGI/OOP/A4
 * Note: - Fibonacci Series
 */


public class FibonacciSeries {
    public static void main(String[] args) {
        Scanner seriesinput = new Scanner(System.in);
        System.out.println("Enter number of term you want for Fibonacci Series");
        int num = seriesinput.nextInt();
        Series(num);
    }

    public static void Series(int n)
    {
        int a=0;
        int b=1;
        int c;
        System.out.print(a + " "+ b + " ");

        for (int i=1; i<n;i++)
        {
            c=a+b;
            System.out.print(c + " ");
            a=b;
            b=c;

        }
    }
}
