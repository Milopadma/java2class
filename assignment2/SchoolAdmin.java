// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
/**
 * 
 * SchoolAdmin class manages the data of SchoolAdmin instances. Always bind to
 * an instance of school through a many-to-one relationship. Child class of
 * User.
 * 
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class SchoolAdmin extends User {
    // this class manages SchoolAdmin data
    // class variables
    private int staffID;
    private String position;

    // a many-to-one relationship with Schoo, since each school has many
    // schooladmins
    private School thisSchool;

    // class constructor
    /**
     * 
     * SchoolAdmin class constructor. With arguments.
     * 
     * @param username
     * @param password
     * @param fullname
     * @param email
     * @param phone
     * @param staffID
     * @param position
     * @param school
     */
    public SchoolAdmin(String username, String password, String fullname, String email, Long phone, int staffID,
            String position, School school) {
        super(username, password, fullname, email, phone);
        this.staffID = staffID;
        this.position = position;
        this.thisSchool = school;
    }

    // class setter and getter methods
    /**
     * 
     * Method that returns the staffID of this SchoolAdmin class.
     * 
     * @return int
     */
    public int getStaffID() {
        return staffID;
    }

    /**
     * 
     * Method that sets the staffID of this SchoolAdmin class.
     * 
     * @param staffID
     */
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    /**
     * 
     * Method that returns the position of this SchoolAdmin class.
     * 
     * @return String
     */
    public String getPosition() {
        return position;
    }

    /**
     * 
     * Method that sets the position of this SchoolAdmin class.
     * 
     * @param position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 
     * Method that returns the thisSchool of this SchoolAdmin class.
     * 
     * @return School
     */
    public School getSchool() {
        return thisSchool;
    }

    /**
     * 
     * Method that sets the thisSchool of this SchoolAdmin class.
     * 
     * @param school
     */
    public void setSchool(School thisSchool) {
        this.thisSchool = thisSchool;
    }

    // toString method
    /**
     * 
     * Method that returns the string representation of this SchoolAdmin class.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "School Admin | [Staff ID: " + staffID + ", position: " + position + "], " + super.toString();
    }
}
