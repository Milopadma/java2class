
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import java.awt.*;
import javax.swing.*;

public class LoginViewPanel extends JPanel {
    // a generic 2 choice button for the initial login screen
    // class fields
    public JButton AdminButton = new JButton();
    public JButton VolunteerButton = new JButton();
    private JButton ExitButton = new JButton("Exit");

    // constructor
    public LoginViewPanel() {
        JLabel title_label = new JLabel("<html><h1>" + "SchoolHELP Menu" + "</h1></html>");

        AdminButton.setText("Admin Login");
        VolunteerButton.setText("Volunteer Login");

        // init the center panel
        JPanel center_panel = new JPanel();

        // center panel layout, items are stacked vertically
        center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.Y_AXIS));
        center_panel.add(title_label);

        // init the panel for the buttons
        JPanel button_panel = new JPanel();

        // button panel layout, items are stacked vertically with same width using
        // GridLayout
        button_panel.setLayout(new GridLayout(0, 1, 0, 10));
        button_panel.add(AdminButton);
        button_panel.add(VolunteerButton);
        button_panel.add(ExitButton);

        // to add the button panel to the center panel
        center_panel.add(button_panel);

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, 2));

        // to center the center panel
        setLayout(new GridBagLayout());

        // to add the center panel to the parent panel
        add(center_panel, new GridBagConstraints());

        // event handlers
        ExitButton.addActionListener(e -> System.exit(0));
        AdminButton.addActionListener(e -> {
            // propagate data to SchoolHELPGUI class to let them decide what to do
            MainView.showAdminLoginView();
        });

        VolunteerButton.addActionListener(e -> {
            // propagate data to SchoolHELPGUI class to let them decide what to do
            MainView.showVolunteerLoginView();
        });
    }
}

// notes
// adding the centerpanel to another parent panel seems to just cover the empty
// parent panel
