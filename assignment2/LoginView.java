
// I GUSTI BAGUS MILO PADMA WIJAYA //e2000426
// class imports
import java.awt.*;
import javax.swing.*;

public class LoginView extends Component {
    // a generic 2 choice button for the initial login screen

    // constructor
    public LoginView(JFrame frame, JButton AdminButton, JButton VolunteerButton, String title) {
        // different labels and button functionalities
        JLabel titleLabel = new JLabel(title);
        AdminButton.setText("Admin");
        VolunteerButton.setText("Volunteer");

        // initialize the frame and GUI elements
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(3, 1));

        // add the GUI elements to the frame
        frame.add(AdminButton);
        frame.add(VolunteerButton);
        frame.add(titleLabel);

    }
}
