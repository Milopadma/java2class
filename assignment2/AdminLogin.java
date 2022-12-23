
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import javax.swing.*;
import java.awt.*;

// this class  handles the admin login screen GUI, and it also handles the AdminLogin functionalities
public class AdminLogin extends LoginScreen {
    // constructor
    public AdminLogin(String username, String password) {
        super(username, password);

        // initialize the frame and GUI elements
        JFrame frame = new JFrame("Admin Login");
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
    private String adminUsername;
    private String adminPassword;

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
