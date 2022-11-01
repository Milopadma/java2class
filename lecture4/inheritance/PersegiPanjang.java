package lecture4.inheritance;

public class PersegiPanjang extends BangunDatar{
    float panjang;
    float lebar;

    //class methods
    @Override
    public double luas() {
        float luas = panjang * lebar;
        System.out.println("Luasnya adalah: " + luas);
        return luas;
    }

    @Override
    public float keliling(){
        float keliling = 2 * (panjang + lebar);
        System.out.println("Kelilingnya adalah: " + keliling);
        return keliling;
    }
}
