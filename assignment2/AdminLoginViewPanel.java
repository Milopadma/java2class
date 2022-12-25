
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// this class handles the admin login screen GUI, and it also handles the AdminLogin functionalities,
// takes and processes data from and for the SchoolAdmin class
public class AdminLoginViewPanel extends JPanel {
    // class fields
    public static JTextField usernamefield = new JTextField(10);
    public static JPasswordField passwordfield = new JPasswordField(10);
    public static JButton loginButton = new JButton("Login");
    public static JButton backButton = new JButton("Back");

    // constructor
    public AdminLoginViewPanel() {
        // init the center panel
        JPanel center_panel = new JPanel();

        // center panel layout, items are stacked vertically
        center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.Y_AXIS));

        // init the panel for the buttons
        JPanel button_panel = new JPanel();

        // button panel layout, items are stacked vertically with same width using
        // GridLayout
        button_panel.setLayout(new GridLayout(0, 1, 0, 10));
        button_panel.add(usernamefield);
        button_panel.add(passwordfield);
        button_panel.add(loginButton);
        button_panel.add(backButton);

        // to add the button panel to the center panel
        center_panel.add(button_panel);

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, 2));

        // to center the center panel
        setLayout(new GridBagLayout());

        // to add the center panel to the parent panel
        add(center_panel, new GridBagConstraints());

        // event handlers
        backButton.addActionListener(e -> MainView.showLoginView());
        loginButton.addActionListener(e -> login());
    }

    // this method handles the login process
    public static void login() {
        // get the username and password from the text fields
        String username = usernamefield.getText();
        String password = new String(passwordfield.getPassword());

        try {
            // check if the username and password are correct
            if (SchoolHELPGUI.checkLogin(username, password)) {
                // if the login is successful, show the admin menu
                MainView.showAdminMenuView();
            } else {
                // if the login is unsuccessful, show an error message
                JOptionPane.showMessageDialog(null, "Incorrect username or password", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}