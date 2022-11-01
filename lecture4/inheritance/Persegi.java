package lecture4.inheritance;

public class Persegi extends BangunDatar{
    //constructor
    public Persegi(float s) {
    }

    //class variables
    float sisi;

    //class methods
    @Override
    public double luas() {
        float luas = sisi * sisi;
        System.out.println("Luasnya adalah: " + luas);
        return luas;
    }

    @Override
    public float keliling() {
        float keliling = 4 * sisi;
        System.out.println("Kelilingnya adalah: " + keliling);
        return keliling;
    }

} 
