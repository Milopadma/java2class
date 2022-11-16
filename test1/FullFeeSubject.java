
// I GUSTI BAGUS MILO PADMA WIJAYA // e2000426
public class FullFeeSubject extends Subject {
    // class headers
    private double Fee;

    // class contructors (with args)
    public FullFeeSubject(String name, String code, int points, double Fee) {
        super(name, code, points);
        this.Fee = Fee;
    }

    // class constructor (without args)
    public FullFeeSubject() {
        super();
        this.Fee = 0;
    }

    // class methods (getters and setters)
    public double getFee() {
        return this.Fee;
    }

    public void setFee(double Fee) {
        this.Fee = Fee;
    }

    // getCost method that does receives no parameters, returns the cost of the
    // subject, which is the value
    // of the fee multiplied by the number of points
    public double getCost() {
        return getFee() * getPoints();
    }

    // toString method
    @Override
    public String toString() {
        return "Subject code of " + getCode() + ", name of " + getName() + " with " + getPoints()
                + " credit points. Cost: " + getCost();
    }
}
