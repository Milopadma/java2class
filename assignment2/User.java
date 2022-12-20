// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426

/**
 * 
 * User class is the parent class of Volunteer and SchoolAdmin classes. Manages
 * the data of User instances.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class User {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private Long phone;

    // User class constructor
    /**
     * 
     * User class constructor
     * 
     * @param username
     * @param password
     * @param fullname
     * @param email
     * @param phone
     */
    public User(String username, String password, String fullname, String email, Long phone) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    // class setter and getter methods
    /**
     * 
     * Method that returns the username of this User class.
     * 
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * Method that sets the username of this User class.
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * Method that returns the password of this User class.
     * 
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * Method that sets the password of this User class.
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * Method that returns the fullname of this User class.
     * 
     * @return String
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * 
     * Method that sets the fullname of this User class.
     * 
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * 
     * Method that returns the email of this User class.
     * 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * Method that sets the email of this User class.
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * Method that returns the phone number of this User class.
     * 
     * @return Long
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * 
     * Method that sets the phone number of this User class.
     * 
     * @param phone
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    // toString method
    /**
     * Method that returns the String representation of this User class.
     */
    public String toString() {
        return "User | [Username: " + username + ", password: " + password + ", fullname: " + fullname + ", email: "
                + email
                + ", phone number: " + phone + "]";
    }

    // each user can be a SchoolAdmin or a Volunteer
    // to check if this user is a school admin or not
    /**
     * 
     * Method that checks if the instance of User this is getting called on is a
     * SchoolAdmin instance or not.
     * 
     * @return boolean
     */
    public boolean isSchoolAdmin() {
        if (this instanceof SchoolAdmin) { // basically checks if 'this' is an instance of SchoolAdmin
            return true;
        }
        return false;
    }

    // to check if this user is a volunteer or not
    /**
     * 
     * Method that checks if the instance of User this is getting called on is a
     * Volunteer instance or not.
     * 
     * @return boolean
     */
    public boolean isVolunteer() {
        if (this instanceof Volunteer) { // basically checks if 'this' is an instance of Volunteer
            return true;
        }
        return false;
    }
}
