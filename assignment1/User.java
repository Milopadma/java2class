public class User {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private Long phone;

    // User class constructor
    public User(String username, String password, String fullname, String email, Long phone) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    // class setter and getter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    // toString method
    public String toString() {
        return "User | [Username: " + username + ", password: " + password + ", fullname: " + fullname + ", email: "
                + email
                + ", phone number: " + phone + "]";
    }

    // each user can be a SchoolAdmin or a Volunteer
    // to check if this user is a school admin or not
    public boolean isSchoolAdmin() {
        if (this instanceof SchoolAdmin) { // basically checks if 'this' is an instance of SchoolAdmin
            return true;
        }
        return false;
    }

    // to check if this user is a volunteer or not
    public boolean isVolunteer() {
        if (this instanceof Volunteer) { // basically checks if 'this' is an instance of Volunteer
            return true;
        }
        return false;
    }
}
