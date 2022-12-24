
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import javax.swing.*;
import java.awt.*;

// this class handles the admin login screen GUI, and it also handles the AdminLogin functionalities,
// takes and processes data from and for the SchoolAdmin class
public class AdminLogin extends LoginScreen {
    // class fields
    private SchoolAdmin schoolAdmin;

    // class GUI elements
    static JButton loginButton = new JButton("Login");

    // constructor
    public AdminLogin() {
        super("Admin Login", "Username", "Password", loginButton);

        // add an event listener to the login button
        // when the login button is clicked, the login() method is called
        loginButton.addActionListener(e -> login());
    }

    // getters and setters
    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    // abstract methods
    @Override
    public void login() {
        // this method is called wwhen the login button is clicked
        // it checks if the username and password are correct
        if (getUsername().equals(getAdminUsername()) && getPassword().equals(getAdminPassword())) {
            // call the AdminScreen class
        } else {
            // show an error message
            JOptionPane.showMessageDialog(null, "Username or password is incorrect", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub

    }

    @Override
    public void register() {
        // TODO Auto-generated method stub

    }

    @Override
    public void forgotPassword() {
        // TODO Auto-generated method stub

    }

    @Override
    public void changePassword() {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeUsername() {
        // TODO Auto-generated method stub

    }

}
