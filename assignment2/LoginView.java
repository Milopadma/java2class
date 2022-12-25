
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import java.awt.*;
import javax.swing.*;

// this class handles everything related to the login screen, takes data from the User class
// and it also handles both AdminLogin and VolunteerLogin classes
public class LoginView extends Component {
    // constructor
    public LoginView(JFrame frame, String title, String usernameLabel, String passwordLabel) {

        // have the login screen GUI here as all login GUIs are the same structure, just
        // different labels and button functionalities

        // initialize the frame and GUI elements
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

    public static String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    public static String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }

}
