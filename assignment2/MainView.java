// this class is responsible for the main view of the application GUI, 
// all GUI-related classes are either based on this class, or called from this class
// GUI related class elements are allowed to call SchoolHELPGUI methods to pass data
// to the data classes

// GUI elements import

import java.awt.*;
import javax.swing.*;

public class MainView {
    // class GUI elements
    // this is gonna get messy
    static JFrame main_frame = new JFrame();

    // for the loginView
    static LoginViewPanel login_view_panel;
    static AdminLoginViewPanel admin_login_view_panel;

    // constructor
    public MainView() {
        // initialize the frame and GUI elements
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setSize(800, 600);
        main_frame.setLocationRelativeTo(null);
        main_frame.setResizable(true);
        main_frame.setTitle("SchoolHELP");
        main_frame.setLayout(new BorderLayout());
        // TODO: add icon, fade animations, splashscreen

        // show the login view
        showLoginView();
    }

    // class methods
    // show LoginView
    public static void showLoginView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();
        login_view_panel = new LoginViewPanel();
        main_frame.add(login_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    public static void showAdminLoginView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();
        admin_login_view_panel = new AdminLoginViewPanel();
        main_frame.add(admin_login_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    public static void showVolunteerLoginView() {
    }

    public static void showAdminMenuView() {
    }

}

// !notes
// all exceptions handled by their own component classes
// all GUI elements are initialized in the constructor
// all GUI elements are added to the frame in the show methods
// all GUI elements are removed from the frame in the show methods
// all GUI elements must only transfer data to SchoolHELPGUI and not direct to
// the data classes
// all GUI elements must only transfer data from SchoolHELPGUI and not direct
// from the data classes (by method calls)
// all inputs are validated by the GUI elements that asked for it
// all inputs are wrapped in a try-catch block to handle exceptions