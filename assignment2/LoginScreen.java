
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import java.awt.*;
import javax.swing.*;

// this class handles everything related to the login screen, the first class to be called when the program is run
// and it also handles both AdminLogin and VolunteerLogin classes
public abstract class LoginScreen {
    // class fields
    private String username;
    private String password;

    // constructor
    public LoginScreen(String username, String password, String title, String usernameLabel, String passwordLabel,
            JButton loginButton) {
        this.username = username;
        this.password = password;

        // have the login screen GUI here as all Login GUIs are the same structure, just
        // different labels and button functionalities

        // initialize the frame and GUI elements
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(3, 1));

        // add the GUI elements to the frame
        frame.add(new JLabel(usernameLabel));
        frame.add(new JTextField());
        frame.add(new JLabel(passwordLabel));
        frame.add(new JPasswordField());
        frame.add(new JButton("Login"));
    }

    // getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // abstract methods
    public abstract void login();

    public abstract void logout();

    public abstract void register();

    public abstract void forgotPassword();

    public abstract void changePassword();

    public abstract void changeUsername();
}
