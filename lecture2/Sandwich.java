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

