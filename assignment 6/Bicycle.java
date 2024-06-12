package twentyfour.spring.oop.group2.lesson6.assignment1.m23w7314;

public class Bicycle {
    //declaring all variable(attributes) as private
    private String brand, model, groupset;
    private short modelYear;
    private int price;
    private boolean used;

    //implemented Bicycle constructor class to assign values directly
    public Bicycle(String brand, String model, String groupset, short modelYear, int price, boolean used){
        this.brand = brand;
        this.model = model;
        this.groupset = groupset;
        this.modelYear = modelYear;
        this.price = price;
        this.used = used;
    }

    //this is public method to set discount on the price
    public int setDiscount(byte discount){
        //if we use (dis/100) as int it gives 0.
        //doing type conversion byte to float, so while dividing (dis/100) we get floating number
        float dis = discount;
        int result = this.price-(int)((dis/100)*this.price); //after calculation again calculation is converted into int to prevent from type error.
        return  result;
    }

    //this method is to get model of Bicycle, but this method is not used from main module
    public String getModel(){

        return this.model;
    }

    //this method is to get price of Bicycle, but this method is not used from main module
    public int getPrice(){

        return this.price;
    }

    //this method is to set price of Bicycle, this is used for 2 times in main module
    public void setPrice(int price){

        this.price = price;
    }

    //this method is used to display all attributes of the class, this method is called itself when object is called
    public String toString(){
        String temp = "Bicycle [brand='"+this.brand+"', model='"+this.model+"', modelYear='"+this.modelYear+"', price='"+this.price+"', used='"+this.used+"']";
        return temp;
    }
}
