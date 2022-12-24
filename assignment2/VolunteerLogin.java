
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import javax.swing.*;
import java.awt.*;

// this class  handles the volunteer login screen GUI, and it also handles the VolunteerLogin functionalities
public class VolunteerLogin extends LoginScreen {
    // class fields
    private String volunteerUsername;
    private String volunteerPassword;

    // class GUI elements
    static JButton loginButton = new JButton("Login");

    // constructor
    public VolunteerLogin(String username, String password) {
        super(username, password, "Volunteer Login", "Username", "Password", loginButton);

        // add an event listener to the login button
        // when the login button is clicked, the login() method is called
        loginButton.addActionListener(e -> login());
    }

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
        try {
            if (getUsername().equals(getVolunteerUsername()) && getPassword().equals(getVolunteerPassword())) {
                // call the VolunteerScreen class
            } else {
                // show an error message
                JOptionPane.showMessageDialog(null, "Username or password is incorrect", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Username or password is incorrect", "Error",
                    JOptionPane.ERROR_MESSAGE);
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
