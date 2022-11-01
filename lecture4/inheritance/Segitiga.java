package lecture4.inheritance;

public class Segitiga extends BangunDatar{
    float alas;
    float tinggi;

    //constructor

    //class methods
    @Override
    public float keliling(){
        float luas = (float)0.5 * alas * tinggi;
        System.out.println("Luasnya adalah: " + luas);
        return luas; 
    }
} 
