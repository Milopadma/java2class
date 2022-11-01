package lecture4.inheritance;

public class Lingkaran extends BangunDatar {
    //constructor
    public Lingkaran(float r) {
        setRadius(r);
    }
    // class variables
    float radius;

    // class methods
    public void setRadius(float r) {
        radius = r;
    }

    @Override
    public double luas(){
        double luas = Math.PI * radius * radius;
        System.out.println("Luasnya adalah: " + luas);
        return luas;
    }

    public float keliling(){
        float keliling = (float)(2 * Math.PI * radius);
        System.out.println("Kelilingnya adalah: " + keliling);
        return keliling;
    }

}
