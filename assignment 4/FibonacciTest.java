package twentyfour.spring.oop.group2.lesson4.assignmet1.m23w7314;
import java.util.Scanner;
/**
 * Id: M23W7314
 * Name:Gurung Bishwas
 * Assignment: KCGI/OOP/A4
 * Note: - Fibonacci Terms
 */
public class FibonacciTest {
    public static void main(String[] args) {
        Scanner fibo1 = new Scanner(System.in);

        System.out.print("\n\n Enter a number to find the nth term of Fibonacci Series ");
        int n = fibo1.nextInt();
        System.out.println("\n The " + n + "th term of Fibonacci Series without overloading is " + fibonacci(n));


        int [] result =new int[n+1];
        System.out.println("\n The " + n + "th term of Fibonacci Series with overloading is " + fibonacci(n, result));
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    // In this above fibonacci(n) method, many calculation are repetitive
    // like if user input n=5, due to recursive function, fibonacci(2) will be calculated for 3 times.
    // the program does end up recalculating the same Fibonacci numbers multiple times.
    // This leads to redundant computation and increases processing time, especially for larger values of n.

    public static int fibonacci(int n, int[] result){
        if (n<=1) {
            return n;
        }
        if (result[n]==0) {
            result[n] = fibonacci(n-1, result) + fibonacci(n-2, result);
        }

        return result[n];
    }
    // so to avoid recalculation
    // we can use technique like to store result of calculated terms
    // and use them later where is the term is repetitive
    // using array to store calculated result
}
