public class SchoolAdmin extends User {
    // this class manages SchoolAdmin data
    // class variables
    private int staffID;
    private String position;

    // a many-to-one relationship with Schoo, since each school has many
    // schooladmins
    private School thisSchool;

    // class constructor
    public SchoolAdmin(String username, String password, String fullname, String email, int phone, int staffID,
            String position, School school) {
        super(username, password, fullname, email, phone);
        this.staffID = staffID;
        this.position = position;
        this.thisSchool = school;
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

    public School getSchool() {
        return thisSchool;
    }

    public void setSchool(School thisSchool) {
        this.thisSchool = thisSchool;
    }

    // toString method
    @Override
    public String toString() {
        return "SchoolAdmin [staffID=" + staffID + ", position=" + position + "], " + super.toString();
    }
}
