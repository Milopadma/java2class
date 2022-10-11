package lecture2;

public class Sandwich {
    // class variables (instance variables)
    private String filling;
    private String bread;
    private double price;

    // class methods
    // constructor
    public Sandwich(String filling, String bread, double price) {
        this.filling = filling;
        this.bread = bread;
        this.price = price;
    }

    // getters
    public String getFilling() {
        return filling;
    }

    public String getBread() {
        return bread;
    }

    public double getPrice() {
        return price;
    }

    // setters
    public void setFilling(String filling) {
        this.filling = filling;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString
    public String toString() {
        return "Sandwich [Filling= " + filling + ", bread = " + bread + ", price = " + price + "]";
    }
}

// SandwichTest class
class SandwichTest {
    public static void main(String[] args) {
        Sandwich sandwich1 = new Sandwich("tuna", "white", 20300);
        Sandwich sandwich2 = new Sandwich("cheese", "wheat", 20.20);
        Sandwich sandwich3 = new Sandwich("ham", "rye", 3.00);

        System.out.println(sandwich1);
        System.out.println(sandwich2);
        System.out.println(sandwich3);
    }
}