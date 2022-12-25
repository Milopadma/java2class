
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import java.awt.*;
import javax.swing.*;

public class LoginViewPanel extends JPanel {
    // a generic 2 choice button for the initial login screen
    // class fields
    private JButton ExitButton = new JButton("Exit");

    // constructor
    public LoginViewPanel(JButton AdminButton, JButton VolunteerButton, String title) {
        // different labels and button functionalities
        JLabel title_label = new JLabel(title);
        AdminButton.setText("Admin");
        VolunteerButton.setText("Volunteer");

        JPanel thisPanel = new JPanel();
        thisPanel.setLayout(new BoxLayout(thisPanel, BoxLayout.Y_AXIS));
        thisPanel.add(title_label);
        thisPanel.add(AdminButton);
        thisPanel.add(VolunteerButton);
        thisPanel.add(ExitButton);

        // add the thisPanel to the main panel
        add(thisPanel);

        // event handlers
        ExitButton.addActionListener(e -> System.exit(0));
    }
}
