
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import javax.swing.*;
import java.awt.*;

// this class handles the user login screen GUI, and it also handles the AdminLogin functionalities,
// takes and processes data from and for the SchoolAdmin class
public class LoginPanel extends JPanel {
    // class fields
    public static JLabel titleLabel = new JLabel("<html><h1>SchoolHELP Admin</h1><h1> Login</h1></html>");
    public static JTextField usernameField = new JTextField(10);
    public static JPasswordField passwordField = new JPasswordField(10);
    public static JButton loginButton = new JButton("Login");
    public static JButton backButton = new JButton("Back");
    public static JLabel usernameLabel = new JLabel("Username");
    public static JLabel passwordLabel = new JLabel("Password");

    // constructor
    public LoginPanel(Boolean isFirstTimeLogin, User user) {
        // init the center panel
        JPanel center_panel = new JPanel();

        // init the title panel to contain the title label
        // set the title panel layout to BorderLayout to left align
        JPanel title_panel = new JPanel();
        title_panel.setLayout(new BorderLayout());
        title_panel.add(titleLabel, BorderLayout.WEST);

        // center panel layout, items are stacked vertically
        center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.Y_AXIS));

        // init the panel for the buttons
        JPanel button_panel = new JPanel();

        // init the panel for the username and password holding panels
        JPanel username_panel = new JPanel();
        JPanel password_panel = new JPanel();

        // init the container panel for both fields and the corresponding title
        JPanel fields_container_panel = new JPanel();

        // username label and field will be stacked on top of each other, but left
        // aligned
        username_panel.setLayout(new BoxLayout(username_panel, BoxLayout.Y_AXIS));
        // each input field will be left aligned (username)
        username_panel.add(usernameLabel);
        username_panel.add(usernameField);
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // password label and field will be stacked on top of each other, but left
        // aligned
        password_panel.setLayout(new BoxLayout(password_panel, BoxLayout.Y_AXIS));
        // each input field will be left aligned (password)
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
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.X_AXIS));
        // add the buttons to the button panel
        button_panel.add(backButton);
        button_panel.add(loginButton);

        // to add the panels to the center panel
        center_panel.add(title_panel);
        center_panel.add(fields_container_panel);
        center_panel.add(button_panel);

        if (isFirstTimeLogin) {
            // using html to make the label multiline
            final String string_label = "First Time Login detected, default username and password is (admin, admin)";
            JLabel label = new JLabel("<html><p style=\"width: 200px;\">" + string_label + "</p></html>");
            // add the label to the center panel
            center_panel.add(label);

        }

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, 2));

        // to center the center panel
        setLayout(new GridBagLayout());

        // to add the center panel to the parent panel, vertical alignment
        add(center_panel, new GridBagConstraints());

        // event handlers
        backButton.addActionListener(e -> MainView.showUserChoiceView());
        // save this panel to the mainview current previous panel
        loginButton.addActionListener(e -> MainView.saveCurrentPanel(this));
        loginButton
                .addActionListener(e -> SchoolHELPGUI.adminLogin(usernameField.getText(), passwordField.getPassword()));
    }

}