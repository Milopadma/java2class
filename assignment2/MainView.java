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

    // save the previous panel to be able to go back to it using the Back button
    static JPanel current_previous_panel;

    // GUI component views
    static UserChoiceViewPanel user_choice_view_panel;
    static LoginPanel admin_login_view_panel;
    static LoginPanel volunteer_login_view_panel;

    // the generic back button for all views
    static JButton back_button = new JButton("Back");

    // all admin-related view panels
    static MultibuttonInputPanel admin_schools_view_panel;
    static MultifieldInputPanel admin_register_school_view_panel;
    static MultifieldInputPanel admin_register_school_view_panel_2;
    static StandardDialogPanel admin_register_school_view_panel_3;
    static ThreestepTimelinePanel admin_timeline_view_panel;

    // all volunteer-related view panels
    // todo

    // the sidemenu views
    static SidemenuView admin_sidemenu_view_panel;
    static SidemenuView volunteer_sidemenu_view_panel;

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

    // class methods, every GUI class actions will eventually call one of these
    // public methods to keep the application flow

    // * */ USER SELECT AND LOGIN STAGE
    // show LoginView (pick and choose User type)
    public static void showLoginView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();
        user_choice_view_panel = new UserChoiceViewPanel();
        main_frame.add(user_choice_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // show AdminLoginView (enter username and password for school admins)
    public static void showAdminLoginView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();
        admin_login_view_panel = new LoginPanel(SchoolHELPGUI.isFirstTimeLogin(), SchoolHELPGUI.getCurrentUser());
        main_frame.add(admin_login_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // show VolunteerLoginView (enter username and password for volunteers)
    public static void showVolunteerLoginView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();
        volunteer_login_view_panel = new LoginPanel(SchoolHELPGUI.isFirstTimeLogin(), SchoolHELPGUI.getCurrentUser());
        main_frame.add(volunteer_login_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // * */ ADMIN USER FUNCTIONALITY STAGE
    // * THIS STAGE HAS ALL METHODS AND showX() METHODS RELATED TO THE ADMIN USER */
    // * DIRECTLY CORRELATES WITH THE DECLARED PUBLIC STATIC FIELDS OF THIS CLASS */
    // show AdminMenuView (admin menu with dashboard)
    public static void showAdminMenuView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();
        admin_sidemenu_view_panel = new SidemenuView(SchoolHELPGUI.getCurrentUser());
        // the buttons and their event handlers
        String button_labels[] = { "Register a new School", "Register a new School Admin", "View all Schools" };
        // an array of functions to be called when the buttons are clicked
        Runnable button_functions[] = { MainView::showAdminRegisterSchoolView,
                MainView::showAdminRegisterSchoolAdminView,
                MainView::showAdminSchoolsView };

        admin_schools_view_panel = new MultibuttonInputPanel(button_labels, button_functions);

        main_frame.add(admin_sidemenu_view_panel, BorderLayout.WEST);
        main_frame.add(admin_schools_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    };

    public static void showAdminRegisterSchoolView() {
        // todo
        System.out.println("showAdminRegisterSchoolView() called");
    }

    public static void showAdminRegisterSchoolAdminView() {
        // todo
    }

    public static void showAdminSchoolsView() {
        // todo
    }

}

// !notes
// all exceptions handled by their own component classes
// all GUI elements are initialized in the constructor
// all GUI elements are added to the main_frame in the show methods
// all GUI elements are removed from the main_frame in the show methods
// all GUI elements must only transfer data to SchoolHELPGUI and not direct to
// the data classes
// all GUI elements must only transfer data from SchoolHELPGUI and not direct
// from the data classes (by method calls)
// all inputs are validated by the GUI elements that asked for it
// all inputs are wrapped in a try-catch block to handle exceptions