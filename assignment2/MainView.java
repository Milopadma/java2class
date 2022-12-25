// this class is responsible for the main view of the application GUI, all GUI-related classes are based on this class
// GUI elements import

import java.awt.*;
import javax.swing.*;

public class MainView {
    // class GUI elements, this is gonna get messy
    static JFrame frame = new JFrame();

    // for the loginView
    static JButton AdminButton = new JButton();
    static JButton VolunteerButton = new JButton();
    static LoginView loginView;

    // constructor
    public MainView() {
        // initialize the frame and GUI elements
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
    }

    // class methods
    // show LoginView
    public static void showLoginView() {
        loginView = new LoginView(frame, AdminButton, VolunteerButton, "SchoolHELP Menu");
        frame.add(loginView);
        frame.setVisible(true);
    }

	public static void showAdminLoginView() {
	}

}
