
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import java.awt.*;
import javax.swing.*;

public class LoginViewPanel extends JPanel {
    // a generic 2 choice button for the initial login screen
    private JPanel thisPanel = new JPanel();

    // class fields
    private JButton ExitButton = new JButton("Exit");

    // constructor
    public LoginViewPanel(JButton AdminButton, JButton VolunteerButton, String title) {
        // different labels and button functionalities
        JLabel titleLabel = new JLabel(title);
        AdminButton.setText("Admin");
        VolunteerButton.setText("Volunteer");

        // set the layout of the panel
        setLayout(new BorderLayout());

        // new thisPanel for the centered elements
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(3, 1));
        centerPanel.add(titleLabel);
        centerPanel.add(AdminButton);
        centerPanel.add(VolunteerButton);

        // add the centered elements to the thisPanel
        thisPanel.add(centerPanel, BorderLayout.CENTER);
        thisPanel.add(ExitButton, BorderLayout.SOUTH);

        // event handlers
        ExitButton.addActionListener(e -> System.exit(0));

        // add the thisPanel to the frame
        add(thisPanel, BorderLayout.CENTER);
    }
}
