package twentyfour.spring.oop.group2.lesson6.assignment1;

import twentyfour.spring.oop.group2.lesson6.assignment1.m23w7314.Bicycle;

public class BicycleApp {
    public static void main(String[] args) {
        byte discount = 20;
        System.out.println();
        Bicycle myRoadBicycle = new Bicycle("SuperBike", "Y-road", "Shin 105", (short) 2023, 550000, false);
        System.out.println(myRoadBicycle);
        System.out.println("If I am able to get " + discount + "% discount, the price will be " + myRoadBicycle.setDiscount(discount) + "JPY.");
        System.out.println(myRoadBicycle);
        myRoadBicycle.setPrice(450000);
        System.out.println(myRoadBicycle);

        Bicycle myMTB = new Bicycle("Bianca", "Nitro", "Srun Go", (short) 2020, 400000, true);
        System.out.println(myMTB);
        myMTB.setPrice(375000);
        System.out.println(myMTB);
    }
}