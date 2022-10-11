package lecture2;

//scanner
import java.util.Scanner;

public class Point {
    // class variables
    private double x;
    private double y;

    // class methods
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // other methods
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

// Write a testing class, a Java program with main method, to test the class
// 'Point' you
// have created:
// a) Create the point (1.5, -2.5) with its value hardcoded.
// b) Create another two points, with values interactively entered through
// keyboard.
// c) Display the details of all three points.

class Testing {
    public static void main(String[] args) {
        // Point 1 is hardcoded
        Point p1 = new Point(1.5, -2.5);
        System.out.println(p1);

        // Point 2 and 3 must be entered by user
        Point p2 = new Point(0, 0);
        Point p3 = new Point(0, 0);

        // get user input
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the x coordinates of point 2");
        p2.setX(input.nextDouble());
        System.out.println("Enter the y coordinates of point 2");
        p2.setY(input.nextDouble());

        System.out.println("Enter the x coordinates of point 3");
        p3.setX(input.nextDouble());
        System.out.println("Enter the y coordinates of point 3");
        p3.setY(input.nextDouble());

        // close scanner
        input.close();

        // display the points
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        // not covering edge-cases where user inputs literally anything other than a
        // double
    }
}
