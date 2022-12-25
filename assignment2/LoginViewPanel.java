
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import java.awt.*;
import javax.swing.*;

public class LoginViewPanel extends JPanel {
    // a generic 2 choice button for the initial login screen
    private JPanel new_panel = new JPanel();
    // class fields
    private JButton ExitButton = new JButton("Exit");

    // constructor
    public LoginViewPanel(JButton AdminButton, JButton VolunteerButton, String title) {

        new_panel.setBackground(Color.RED);
        // different labels and button functionalities
        JLabel title_label = new JLabel(title);
        AdminButton.setText("Admin");
        VolunteerButton.setText("Volunteer");

        JPanel center_panel = new JPanel();
        center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.Y_AXIS));
        center_panel.add(title_label);
        center_panel.add(AdminButton);
        center_panel.add(VolunteerButton);
        center_panel.add(ExitButton);

        new_panel.setLayout(new BorderLayout(1, 1));

        new_panel.add(center_panel, BorderLayout.CENTER);
        // set the layout of the main panel to center thisPanel
        add(new_panel, BorderLayout.CENTER);

        // event handlers
        ExitButton.addActionListener(e -> System.exit(0));
    }
}
