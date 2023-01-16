// this class shows the example snippet codes for 
// aggregation and inheritance
public class aggregationInheritance {
    // class fields
    private int x;
    private int y;
    private String name;
    private String address;

    // class constructor
    public aggregationInheritance(int x, int y, String name, String address) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.address = address;
    }

    // class methods
    public void publicvoidmethod() {
        System.out.println("this is just a normal class");
    };
}

class inheritance extends aggregationInheritance {
    // this class inherits from aggregationInheritance
    // class fields
    private int z;
    private String name;
    private String address;

    // class constructor
    public inheritance(int x, int y, String name, String address, int z) {
        super(x, y, name, address); // calls the super class constructor
        this.z = z;
        this.name = name;
        this.address = address;
    }

    // class methods
    public void print() {
        System.out.println(" z: " + z + " name: " + name + " address: " + address);
    }

    // inherited and override method
    @Override
    public void publicvoidmethod() {
        System.out.println("this is an inherited class");
    }
}