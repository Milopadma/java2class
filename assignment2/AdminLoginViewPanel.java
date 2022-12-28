
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import javax.swing.*;
import java.awt.*;

public class AdminLoginViewPanel extends LoginPanel {
    // constructor
    public AdminLoginViewPanel(Boolean isFirstTimeLogin) {
        // init the template from LoginPanel
        super("<html><h1>SchoolHELP Admin</h1><h1> Login</h1></html>");

        if (isFirstTimeLogin) {
            // using html to make the label multiline
            final String string_label = "First Time Login detected, default username and password is (admin, admin)";
            JLabel label = new JLabel("<html><p style=\"width: 200px;\">" + string_label + "</p></html>");
            // add the label to the center panel from the super constructor
            center_panel.add(label);
        }

        // event handlers
        backButton.addActionListener(e -> MainView.showUserChoiceView());
        loginButton
                .addActionListener(e -> SchoolHELPGUI.userLogin(usernameField.getText(), passwordField.getPassword()));
    }

}