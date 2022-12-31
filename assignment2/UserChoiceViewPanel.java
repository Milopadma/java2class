
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import java.awt.*;
import javax.swing.*;

/**
 * This class is the view for the user choice screen, where the user can choose
 * to login as an admin or a volunteer.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya - E2000426
 *         Date: 2022-24-12
 */
public class UserChoiceViewPanel extends JPanel {
    // a generic 2 choice button for the initial login screen
    // class fields
    private JButton AdminChoiceButton = new JButton();
    private JButton VolunteerChoiceButton = new JButton();
    private JButton ExitButton = new JButton("Exit");

    // constructor
    /**
     * This constructor creates the template for the user choice screen GUI.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public UserChoiceViewPanel() {
        JLabel title_label = new JLabel("<html><h1>" + "SchoolHELP Menu" + "</h1></html>");

        AdminChoiceButton.setText("Admin Login");
        VolunteerChoiceButton.setText("Volunteer Login");

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
        button_panel.add(AdminChoiceButton);
        button_panel.add(VolunteerChoiceButton);
        button_panel.add(ExitButton);

        // to add the button panel to the center panel
        center_panel.add(button_panel);

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, 2));

        // to center the center panel
        setLayout(new GridBagLayout());

        // to add the center panel to the parent panel
        add(center_panel, new GridBagConstraints());

        // action listeners and lambda handlers for the buttons
        // exit button calls the save function and exits the application
        ExitButton.addActionListener(e -> {
            SchoolHELPGUI.saveSchoolHELPInstance();
            // wait 5 seconds, then call the exit
            try {
                Thread.sleep(1500);
                System.exit(0);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        });
        AdminChoiceButton.addActionListener(e -> {
            // call mainview's method to show the admin login view
            MainView.showAdminLoginView();
        });

        VolunteerChoiceButton.addActionListener(e -> {
            // call mainview's method to show the volunteer login view
            MainView.showVolunteerLoginView();
        });
    }
}
