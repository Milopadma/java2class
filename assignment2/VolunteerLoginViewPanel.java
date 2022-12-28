
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import javax.swing.*;

public class VolunteerLoginViewPanel extends LoginPanel {
    // constructor
    public VolunteerLoginViewPanel() {
        // init the template from LoginPanel
        super("<html><h1>SchoolHELP Volunteer</h1><h1> Login</h1></html>");

        // volunteer has a new button to register
        JButton registerButton = new JButton("Register");
        // add the register button to the button panel
        button_panel.add(registerButton);

        // event handlers
        backButton.addActionListener(e -> MainView.showUserChoiceView());
        loginButton
                .addActionListener(
                        e -> SchoolHELPGUI.volunteerLogin(usernameField.getText(), passwordField.getPassword()));
    }

}