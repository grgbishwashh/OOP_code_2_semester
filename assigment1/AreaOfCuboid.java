package twentyfour.spring.oop.group2.lesson1.assigment1.m23w7314;

import java.util.Scanner; //importing Scanner class to take user input

public class AreaOfCuboid {
    public static void main(String[] args) {
        // Creating new object 'box1' to take input for the variables
        Scanner box1 = new Scanner(System.in);

        //Declaration of identifiers to store input
        float length;
        float breadth;
        float height;
        float total_surface_area;

        //Question display on output screen, to ask user input
        System.out.println("Enter the length of box");

        //asking user input for cuboid length
        length=box1.nextFloat();

        System.out.println("Enter the breadth of box");
        breadth = box1.nextFloat();

        System.out.println("Enter the height of the box");
        height = box1.nextFloat();

        //Calculation of total surface area of cuboid using formula
        total_surface_area = 2*(length*breadth+length*height+breadth*height);

        //Displaying the result i.e area of cuboid
        System.out.println("The total surface area of the box is "+ total_surface_area);

        box1.close();
    }
}
