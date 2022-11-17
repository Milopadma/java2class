public class SchoolAdmin extends User {
    // this class manages SchoolAdmin data
    // class variables
    private int staffID;
    private String position;

    // class constructor
    public SchoolAdmin(String username, String password, String fullname, String email, int phone, int staffID,
            String position) {
        super(username, password, fullname, email, phone);
        this.staffID = staffID;
        this.position = position;
    }

    // class setter and getter methods
    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // toString method
    @Override
    public String toString() {
        return "SchoolAdmin [staffID=" + staffID + ", position=" + position + "], " + super.toString();
    }
}
