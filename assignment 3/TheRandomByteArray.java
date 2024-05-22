package twentyfour.spring.oop.group2.lesson3.assignment1.m23w7314;
import java.util.Random;

/**
 * Id: M23W7314
 * Name:Gurung Bishwas
 * Assignment: KCGI/OOP/A3
 * Note: -
 */


public class TheRandomByteArray {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNo = random.nextInt(11) -5;
        int myarray[] = {1,2,randomNo,-4,-5,6};
        int count = 0;
        int prod = 1;

        for (int i =0; i<6; i++)
        {
            if (i==2)
            {
                if (myarray[2]==0)
                {
                    System.out.println("Index : " + i + "," + "- note value is 0, skipping");
                    continue;
                }
            }

            if (i==2)
            {
                if(myarray[2]==4)
                {
                    System.out.println("Index : " + i + "," + "- four detected!");
                    break;
                }
            }

            if (myarray[i]<0)
            {
                count=count+1;
            }



            if (i%2==0) {
                System.out.println("Index : " + i + "," + "  Value:" + myarray[i]+ "  Note this is even index");
                prod=prod*myarray[i];
            }
            else
            {
                System.out.println("Index : " + i + "," + "  Value:" + myarray[i]);
            }
        }

        System.out.println("The multiplication number is "+ prod);
        System.out.println("The number of negative numbers is : "+ count);

        }
}
