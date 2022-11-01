package lecture4.inheritance;

public class Segitiga extends BangunDatar{
    float alas;
    float tinggi;

    //constructor
    public Segitiga(float a, float t) {
        setAlas(a);
        setTinggi(t);
    }

    //class methods
    public void setAlas(float a) {
        alas = a;
    }

    public void setTinggi(float t) {
        tinggi = t;
    }

    @Override
    public float keliling(){
        float luas = (float)0.5 * alas * tinggi;
        System.out.println("Luasnya adalah: " + luas);
        return luas; 
    }
} 
