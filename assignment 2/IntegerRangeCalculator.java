package twentyfour.spring.oop.group2.lesson2.assignment1.m23w7314;
import java.lang.Math;
public class IntegerRangeCalculator {
    public static void main(String[] args) {
        // Declare and initialize the constant for the special ASCII character "±"
        final char SPECIAL_CHAR = '\u00B1';

        //Example of earth radius to store maximum number range
        double radius_earth=63710009999.9;

        // import of math class and using PI value
        double pi = Math.PI;

        //use of power method from Math Class.
        double volume_earth=pi*Math.pow(radius_earth,2);

        //type casting the value of volume_earth from double to integer. So integer_range can store maximum value it can.
        int integer_range= (int)volume_earth;
        System.out.println("This the volume of earth in double data type "+volume_earth);
        System.out.println("The integer data type range is approximately \""+SPECIAL_CHAR+integer_range+"\".");

        //here in integer data type, increment operator is used to increase the value of max, unless the value becomes overflow
        // and value becomes negative, loop gets exit, at last 1 value is subtracted to get actual value
        int max=0;
       while (max>=0)
       {
           max=max+1;
       }
       max=max-1;


        //here in integer data type, decrement operator is used to decrease the value of min, unless the value becomes overflow
        // and value becomes positive, loop gets exit, at last 1 value is added to get actual value
       int min=0;
       while (min<=0)
        {
            min=min-1;
        }
       min=min+1;

        System.out.println("Integer maximum value is: \"+"+ max +"\".");
        System.out.println("Integer minimum value is: \""+ min+"\".");

    }
}
