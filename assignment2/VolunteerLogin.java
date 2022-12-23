
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import javax.swing.*;
import java.awt.*;

// this class  handles the volunteer login screen GUI, and it also handles the VolunteerLogin functionalities
public class VolunteerLogin extends LoginScreen {
    // constructor
    public VolunteerLogin(String username, String password) {
        super(username, password);

        // initialize the frame and GUI elements
        JFrame frame = new JFrame("Volunteer Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(3, 1));

        // create the GUI elements
        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        // add the GUI elements to the frame
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
    }

    // class fields
    private String volunteerUsername;
    private String volunteerPassword;

    // getters and setters
    public String getVolunteerUsername() {
        return volunteerUsername;
    }

    public String getVolunteerPassword() {
        return volunteerPassword;
    }

    public void setVolunteerUsername(String volunteerUsername) {
        this.volunteerUsername = volunteerUsername;
    }

    public void setVolunteerPassword(String volunteerPassword) {
        this.volunteerPassword = volunteerPassword;
    }

    // abstract methods
    @Override
    public void login() {
        // this method is called wwhen the login button is clicked
        // it checks if the username and password are correct
        if (getUsername().equals(getVolunteerUsername()) && getPassword().equals(getVolunteerPassword())) {
            // call the VolunteerScreen class
        } else {
            // display an error message
        }
    }

    @Override
    public void logout() {
        // this method is called when the logout button is clicked
        // it logs the user out of the system
    }

    @Override
    public void register() {
        // this method is called when the register button is clicked
        // it calls the RegisterScreen class
    }

    @Override
    public void forgotPassword() {
        // this method is called when the forgot password button is clicked
        // it calls the ForgotPasswordScreen class
    }

    @Override
    public void changePassword() {
        // this method is called when the change password button is clicked
        // it calls the ChangePasswordScreen class
    }

    @Override
    public void changeUsername() {
        // this method is called when the change username button is clicked
        // it calls the ChangeUsernameScreen class
    }
}
