package lecture4.inheritance;

public class Main {
    public static void main(String[] args) {
        // BangunDatar object
        BangunDatar bangunDatar = new BangunDatar();

        // persegi object
        Persegi persegi = new Persegi(10); // sisi 

        // lingkaran object
        Lingkaran lingkaran = new Lingkaran(10); //radius

        // segitiga object
        Segitiga segitiga = new Segitiga(10, 10); //alas, tinggi

        bangunDatar.luas();
        persegi.luas();
        segitiga.luas();
        lingkaran.luas();
        lingkaran.keliling();


    }
}