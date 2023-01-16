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
}

class inheritance extends aggregationInheritance {
    // this class inherits from aggregationInheritance
    // class fields
    private int z;

    // class constructor
    public inheritance(int x, int y, String name, String address, int z) {
        super(x, y, name, address); // calls the super class constructor
        this.z = z;
    }
}