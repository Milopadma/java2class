package lecture2;

public class Box {
    // class variables
    private double height;
    private double width;
    private double depth;
    private boolean isFull;

    // class methods
    // constructor
    public Box(double height, double width, double depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.isFull = false;
    }

    // getters
    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDepth() {
        return depth;
    }

    public boolean isFull() {
        return isFull;
    }

    // setters
    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    // toString
    public String toString() {
        return "Box [height = " + height + ", width = " + width + ", depth = " + depth + ", is it full? = " + isFull
                + "]";
    }
}

// BoxTest class
class BoxTest {
    public static void main(String[] args) {
        Box box1 = new Box(12, 23, 35);
        Box box2 = new Box(24, 56, 89);
        Box box3 = new Box(122, 14, 42);
        System.out.println(box1);
        System.out.println(box2);
        System.out.println(box3);
    }
}
