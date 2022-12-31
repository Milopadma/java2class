
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import javax.swing.*;
import java.awt.*;

// this class handles the user login screen GUI, and it also handles the AdminLogin functionalities,
// takes and processes data from and for the SchoolAdmin class
/**
 * This class is the view for the admin login screen, where the user can input
 * their username and password.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya - E2000426
 *         Date: 2022-24-12
 * 
 */
public class AdminLoginViewPanel extends JPanel {
    public static JTextField usernameField = new JTextField(10);
    public static JPasswordField passwordField = new JPasswordField(10);

    // constructor
    /**
     * This constructor creates the template for the admin login screen GUI.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param Boolean isFirstTimeLogin - this is a boolean that determines whether
     *                the user is logging in for the first time or not.
     */
    public AdminLoginViewPanel(Boolean isFirstTimeLogin) {
        // init the template from LoginPanel
        // title label with string
        String titleString = "<html><h1>SchoolHELP Admin Login</h1></html>";
        JLabel titleLabel = new JLabel(titleString);

        // init the title panel to contain the title label
        // set the title panel layout to BorderLayout to left align
        JPanel title_panel = new JPanel();
        title_panel.setLayout(new BorderLayout());
        title_panel.add(titleLabel, BorderLayout.WEST);

        // center panel layout, items are stacked vertically
        JPanel center_panel = new JPanel();
        center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.Y_AXIS));

        // init the panel for the username and password holding panels
        JPanel username_panel = new JPanel();
        JPanel password_panel = new JPanel();

        // init the container panel for both fields and the corresponding title
        JPanel fields_container_panel = new JPanel();

        // username label and field will be stacked on top of each other, but left
        // aligned
        username_panel.setLayout(new BoxLayout(username_panel, BoxLayout.Y_AXIS));
        // each input field will be left aligned (username)
        JLabel usernameLabel = new JLabel("Username");
        username_panel.add(usernameLabel);
        username_panel.add(usernameField);
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // password label and field will be stacked on top of each other, bt left
        // aligned
        password_panel.setLayout(new BoxLayout(password_panel, BoxLayout.Y_AXIS));
        // each input field will be left aligned (password)
        JLabel passwordLabel = new JLabel("Password");
        password_panel.add(passwordLabel);
        password_panel.add(passwordField);
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // add both the username and password panels to the fields container panel,
        // vertically aligned using BorderLayout
        fields_container_panel.setLayout(new BorderLayout());
        fields_container_panel.add(username_panel, BorderLayout.NORTH);
        fields_container_panel.add(password_panel, BorderLayout.SOUTH);

        // button panel layout, both buttons horizontally aligned
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.X_AXIS));
        // add the buttons to the button panel
        JButton backButton = new JButton("Back");
        JButton loginButton = new JButton("Login");
        button_panel.add(backButton);
        button_panel.add(loginButton);

        // to add the panels to the center panel
        center_panel.add(title_panel);
        center_panel.add(fields_container_panel);
        center_panel.add(button_panel);

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, 2));

        // to center the center panel
        setLayout(new GridBagLayout());

        // to add the center panel to the parent panel, vertical alignment
        add(center_panel, new GridBagConstraints());

        if (isFirstTimeLogin) {
            // using html to make the label multiline
            final String string_label = "First Time Login detected, default username and password is (admin, admin)";
            JLabel label = new JLabel("<html><p style=\"width: 200px;\">" + string_label + "</p></html>");
            // add the label to the center panel from the super constructor
            center_panel.add(label);
        }

        // event handlers
        backButton.addActionListener(e -> {
            MainView.showUserChoiceView();
            destroy();
        });
        loginButton
                .addActionListener(e -> {
                    // check if the fields are empty
                    if (usernameField.getText().equals("") || passwordField.getPassword().length == 0) {
                        JOptionPane.showMessageDialog(null, "Please fill in all the fields");
                    } else if (SchoolHELPGUI.userLogin(usernameField.getText(), passwordField.getPassword(), "ADMIN")) {
                        // show the admin view
                        MainView.showAdminSchoolsMenuView();
                        destroy();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
                });
    }

    /**
     * This method clears the fields of the admin login screen.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     */
    public void destroy() {
        // clear the fields
        usernameField.setText("");
        passwordField.setText("");
        removeAll();
    }
}