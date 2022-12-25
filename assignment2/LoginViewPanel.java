
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
        JLabel title_label = new JLabel(title);

        AdminButton.setText("Admin");
        VolunteerButton.setText("Volunteer");

        JPanel center_panel = new JPanel();
        // center panel layout, items are stacked vertically
        center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.Y_AXIS));
        center_panel.add(title_label);
        center_panel.add(AdminButton);
        center_panel.add(VolunteerButton);
        center_panel.add(ExitButton);

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, 2));

        // to center the center panel
        setLayout(new GridBagLayout());
        // to add the center panel to the parent panel
        add(center_panel, new GridBagConstraints());

        // event handlers
        ExitButton.addActionListener(e -> System.exit(0));
    }
}

// notes
// adding the centerpanel to another parent panel seems to just cover the empty
// parent panel
