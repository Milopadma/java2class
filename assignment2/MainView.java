// this class is responsible for the main view of the application GUI, 
// all GUI-related classes are either based on this class, or called from this class
// GUI related class elements are allowed to call SchoolHELPGUI methods to pass data
// to the data classes

// GUI elements import

import java.awt.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    static MultifieldInputPanel admin_register_school_admin_view_panel;
    static StandardInfoPanel admin_school_registration_complete_view_panel;
    static ThreestepTimelinePanel admin_timeline_view_panel;

    // all volunteer-related view panels
    // todo

    // the sidemenu views
    static SidemenuView admin_sidemenu_view_panel;
    static SidemenuView volunteer_sidemenu_view_panel;

    // the right menu panel
    static JPanel right_menu_view_panel = new JPanel();

    // to save the values of the MultifieldInputPanel
    static Map<String, String> multifield_input_panel_values;

    // constructor
    public MainView() {
        // initialize the frame and GUI elements
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setSize(800, 600);
        main_frame.setLocationRelativeTo(null);
        main_frame.setResizable(true);
        main_frame.setTitle("SchoolHELP");
        main_frame.setLayout(new BorderLayout());

        // !Deprecated
        // // back button functionality
        // back_button.addActionListener(e -> {
        // // clear the frame before adding new elements
        // main_frame.getContentPane().removeAll();
        // main_frame.add(current_previous_panel, BorderLayout.CENTER);
        // main_frame.setVisible(true);
        // });

        // TODO: add icon, fade animations, splashscreen

        // show the login view
        showUserChoiceView();

    }

    // class methods, every GUI class actions will eventually call one of these
    // public methods to keep the application flow

    // * HELPER CLASS METHODS */
    // to save the current panel to be able to go back to it using the Back button
    public static void saveCurrentPanel(JPanel panel) {
        // this method is only called on a button press
        current_previous_panel = panel;
        System.out.println("Saved panel: " + current_previous_panel);
    }

    public static void saveMultifieldTextFields(String[] fieldNames, String[] fieldValues) {
        // save fieldNames and fieldValues as a connected hashmap using Stream()
        // and Collectors
        Map<String, String> fieldMap = IntStream.range(0, fieldNames.length)
                .boxed()
                .collect(Collectors.toMap(i -> fieldNames[i], i -> fieldValues[i]));
        multifield_input_panel_values = fieldMap;
    }

    // this helper class method calls into the SchoolHELPGUI class to create a new
    // school by passing in the saved multitextfields of adminschoolregistration
    public static void createNewSchool(Map<String, String> fieldMap) {
        // call the SchoolHELPGUI method to create a new school
        SchoolHELPGUI.createNewSchool(fieldMap);
    }

    // * */ USER SELECT AND LOGIN STAGE
    // show LoginView (pick and choose User type)
    public static void showUserChoiceView() {
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
    // initialize the side menu view
    public static void showSideMenuView() {
        admin_sidemenu_view_panel = new SidemenuView(SchoolHELPGUI.getCurrentUser());
        main_frame.add(admin_sidemenu_view_panel, BorderLayout.WEST);
    }

    // show AdminSchoolsMenuView (school admin menu with sidemenu dashboard)
    public static void showAdminSchoolsMenuView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();

        // init sidemenuview
        showSideMenuView();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new subtitle label
        JLabel subtitle_label = new JLabel("<html><h3>Choose an option below</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // the buttons and their event handlers
        String button_labels[] = { "Register a new School", "Register a new School Admin", "View all Schools" };

        // an array of functions to be called when the buttons are clicked
        Runnable button_functions[] = { MainView::showAdminRegisterSchoolView,
                MainView::showAdminRegisterSchoolAdminView,
                MainView::showAdminSchoolsView };

        // create the panel with the buttons
        admin_schools_view_panel = new MultibuttonInputPanel(button_labels, button_functions);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());

        // add the elements to their respective panels
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_schools_view_panel, BorderLayout.CENTER);

        // for the back button to go back to the admin login view
        back_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showAdminLoginView();
        });

        right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    };

    // to show the view for registering a new school, step 1
    public static void showAdminRegisterSchoolView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);
        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Registering a new School</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        String input_labels[] = { "School Name", "School Address", "School City" };
        String input_field_values[] = { "", "", "", "" };
        Runnable button_functions[] = { MainView::showAdminRegisterSchoolAdminView,
                MainView::showAdminSchoolsMenuView };
        admin_register_school_view_panel = new MultifieldInputPanel(input_labels, input_field_values,
                button_functions);
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_register_school_view_panel, BorderLayout.CENTER);
        // right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        main_frame.setVisible(true);
    }

    // to show the school admin registration panel view
    public static void showAdminRegisterSchoolAdminView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Registering a new School Admin</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        String input_labels[] = { "Admin Name", "Admin Email", "Admin Password", "Admin Fullname", "Admin Phone",
                "Admin StaffID", "Admin Position" };
        String input_field_values[] = { "", "", "", "", "", "", "" };
        Runnable button_functions[] = { MainView::showSchoolAdminAndSchoolCompletionView,
                MainView::showAdminSchoolsMenuView };
        admin_register_school_admin_view_panel = new MultifieldInputPanel(input_labels, input_field_values,
                button_functions);
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_register_school_admin_view_panel, BorderLayout.CENTER);
        // right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        main_frame.setVisible(true);

    }

    public static void showAdminSchoolsView() {
    }

    public static void showAdminRequestsView() {
    }

    public static void showAdminOffersView() {
    }

    // this is shown when both a school and school admin is registered
    public static void showSchoolAdminAndSchoolCompletionView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // create the new school using the saved input fields in this class
        try {
            School newSchool = SchoolHELPGUI.createNewSchool(multifield_input_panel_values);

            // button functions
            Runnable button_functions[] = { MainView::showAdminSchoolsMenuView };

            // panel view content
            admin_school_registration_complete_view_panel = new StandardInfoPanel(
                    "School and SchoolAdmin registration complete!",
                    "You can now login to the SchoolHELP app with the SchoolAdmin credentials you just registered.",
                    button_functions, newSchool);

            JButton view_full_info_button = new JButton("View Full Info");
            view_full_info_button.addActionListener(e -> {
                // show the school info view
                showSchoolInfoView(newSchool);
            });

            JButton back_button = new JButton("Back");
            back_button.addActionListener(e -> {
                // show the school info view
                showAdminRegisterSchoolAdminView();
            });

            right_menu_view_panel.add(title_label, BorderLayout.NORTH);
            right_menu_view_panel.add(admin_school_registration_complete_view_panel, BorderLayout.CENTER);
            right_menu_view_panel.add(view_full_info_button, BorderLayout.SOUTH);
            right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // this is shown when the schoolinfo is requested
    public static void showSchoolInfoView(School newSchool) {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>School Info</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        String input_labels[] = { "School Name", "School Address", "School City", "School Admin Name",
                "School Admin Email", "School Admin Password", "School Admin Fullname", "School Admin Phone",
                "School Admin StaffID", "School Admin Position" };
        String input_field_values[] = { newSchool.getSchoolName(), newSchool.getAddress(), newSchool.getCity(),
                // to view the info of the newly created school admin for this school
                newSchool.getSchoolAdmins().get(0).getUsername(), newSchool.getSchoolAdmins().get(0).getPassword(),
                newSchool.getSchoolAdmins().get(0).getFullname(),
                Long.toString(newSchool.getSchoolAdmins().get(0).getPhone()),
                Integer.toString(newSchool.getSchoolAdmins().get(0).getStaffID()),
                newSchool.getSchoolAdmins().get(0).getPosition() };

        Runnable button_function = MainView::showAdminSchoolsMenuView;
        InfoListViewPanel admin_school_info_view_panel = new InfoListViewPanel(input_labels, input_field_values,
                button_function);

        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_school_info_view_panel, BorderLayout.CENTER);

    }

    // this is shown when a school is registered
    public static void showSchoolCompletionView() {
    }

    // this is shown when a school admin is registered
    public static void showSchoolAdminCompletionView() {
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