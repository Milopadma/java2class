// this class is responsible for the main view of the application GUI, all GUI-related classes are based on this class
// GUI elements import

import java.awt.*;
import javax.swing.*;

public class MainView {
    // class GUI elements
    // this is gonna get messy
    static JFrame main_frame = new JFrame();

    // for the loginView
    JButton AdminButton = new JButton();
    JButton VolunteerButton = new JButton();
    LoginViewPanel login_view_panel;

    // constructor
    public MainView() {
        // initialize the frame and GUI elements
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setSize(800, 600);
        main_frame.setLocationRelativeTo(null);
        main_frame.setResizable(true);
        main_frame.setTitle("SchoolHELP");
        main_frame.setLayout(new BorderLayout());
    }

    // class methods
    // show LoginView
    public void showLoginView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();
        login_view_panel = new LoginViewPanel(AdminButton, VolunteerButton, "SchoolHELP Menu");
        main_frame.add(login_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    public static void showAdminLoginView() {
    }

    public static void showVolunteerLoginView() {
    }

}
