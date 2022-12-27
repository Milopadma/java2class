// this class is responsible for the main view of the application GUI, 
// all GUI-related classes are either based on this class, or called from this class
// GUI related class elements are allowed to call SchoolHELPGUI methods to pass data
// to the data classes

// GUI elements import

import java.awt.*;
import java.util.ArrayList;
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

    private static MultibuttonInputPanel admin_requests_view_panel;

    private static MultibuttonInputPanel admin_offers_view_panel;

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
        System.out.println("Saving multifield text fields: " + fieldNames[0] + " " + fieldValues[0]);
        // ! BUG fieldValues[0] is null
        Map<String, String> fieldMap = IntStream.range(0, fieldNames.length)
                .boxed()
                .collect(Collectors.toMap(i -> fieldNames[i], i -> fieldValues[i]));
        // ! this throws a null exception
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

    // * */ for the sidemenu buttons
    // show AdminSchoolsMenuView (school admin menu with sidemenu dashboard)
    // method 1/3 for the admin sidemenu buttons
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
                MainView::showAllSchoolsView };

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

    // to show the admin version of the Requests menu, this is for the sidemenu
    // "Requests" button
    // method 2/3 for the admin sidemenu buttons
    public static void showAdminRequestsMenuView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Requests</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        String button_labels[] = { "View all Requests for this school", "View every Request from all Schools" };

        // an array of functions to be called when the buttons are clicked
        Runnable button_functions[] = { MainView::showRequestSubmissionForSchoolView, MainView::showAllRequestsView };

        // create the panel with the buttons
        admin_requests_view_panel = new MultibuttonInputPanel(button_labels, button_functions);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());

        // add the elements to their respective panels
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_requests_view_panel, BorderLayout.CENTER);

        // for the back button to go back to the admin login view
        back_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showAdminLoginView();
        });

        right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // to show the admin version of the Offers menu, this is for the sidemenu button
    // method 3/3 for the admin sidemenu buttons
    public static void showAdminOffersMenuView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Requests</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        String button_labels[] = { "View all Offers for this school", "View every Offer from all Schools" };

        // an array of functions to be called when the buttons are clicked
        Runnable button_functions[] = { MainView::showAllOffersForSchoolView, MainView::showAllOffersView };

        // create the panel with the buttons
        admin_offers_view_panel = new MultibuttonInputPanel(button_labels, button_functions);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());

        // add the elements to their respective panels
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_offers_view_panel, BorderLayout.CENTER);

        // for the back button to go back to the admin login view
        back_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showAdminLoginView();
        });

        right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // * */ show METHODS FOR ADMIN SCHOOL MENU
    // to show the view for registering a new school, step
    // method 1/3 of the school side menu for admin
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
    // method 2/3 of the school side menu for admin
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

    // provide a JList of all the schools in the database
    // method 3/3 of the school side menu for admin
    public static void showAllSchoolsView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);
        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Viewing all Schools</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // get data from SchoolHELPGUI class
        ArrayList<School> schools = SchoolHELPGUI.getAllSchools();

        // create a new JList with the data
        JList<School> school_list = new JList<School>(schools.toArray(new School[schools.size()]));
        school_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        school_list.setLayoutOrientation(JList.VERTICAL);
        school_list.setVisibleRowCount(-1);

        // create a scroll pane for the list
        JScrollPane school_list_scroll_pane = new JScrollPane(school_list);

        // add the elements to their respective panels
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(school_list_scroll_pane, BorderLayout.CENTER);

        // for the back button to go back to the admin login view
        back_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showAdminSchoolsMenuView();
        });

        right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
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
                showSchoolInfoView(newSchool, true);
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
    public static void showSchoolInfoView(School newSchool, boolean extraInfo) {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>School Info</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        InfoListViewPanel admin_school_info_view_panel;
        if (extraInfo) {
            // show all info about the school and the school admin
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
            admin_school_info_view_panel = new InfoListViewPanel(input_labels, input_field_values,
                    button_function);
        } else {
            // just show the newly created school admin, without the school info
            String input_labels[] = { "School Admin Name", "School Admin Email", "School Admin Password",
                    "School Admin Fullname", "School Admin Phone", "School Admin StaffID", "School Admin Position" };

            Runnable button_function = MainView::showAdminSchoolsMenuView;
            admin_school_info_view_panel = new InfoListViewPanel(input_labels,
                    // to view the info of the newly created school admin for this school
                    new String[] { newSchool.getSchoolAdmins().get(0).getUsername(),
                            newSchool.getSchoolAdmins().get(0).getPassword(),
                            newSchool.getSchoolAdmins().get(0).getFullname(),
                            Long.toString(newSchool.getSchoolAdmins().get(0).getPhone()),
                            Integer.toString(newSchool.getSchoolAdmins().get(0).getStaffID()),
                            newSchool.getSchoolAdmins().get(0).getPosition() },
                    button_function);
        }
        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_school_info_view_panel, BorderLayout.CENTER);

    }

    // * */ show METHODS FOR ADMIN REQUESTS MENU]
    // all the show methods that relates to the requests menu in the admin menu goes
    // here
    // this method is to submit a request for the school that the school admin is in
    // (local)
    public static void showRequestSubmissionForSchoolView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Request Submissions</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // the buttons and their event handlers
        String button_labels[] = { "Submit a Request" };

        // an array of functions to be called when the buttons are clicked
        Runnable button_functions[] = { MainView::showRequestSubmissionChoiceView };

        // the panel view
        MultibuttonInputPanel admin_request_submission_view_panel = new MultibuttonInputPanel(button_labels,
                button_functions);

        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_request_submission_view_panel, BorderLayout.CENTER);

        // for the back button
        Runnable back_button_function = MainView::showAdminRequestsMenuView;
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> back_button_function.run());

        right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.EAST);
        main_frame.setVisible(true);

    }

    // this method is to show the request submission view
    public static void showRequestSubmissionChoiceView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Request Submission</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // the buttons and their event handlers
        String button_labels[] = { "Tutorial Request", "Resource Request" };

        // an array of functions to be called when the buttons are clicked
        Runnable button_functions[] = { MainView::showTutorialRequestSubmissionView,
                MainView::showResourceRequestSubmissionView };

        // the panel view
        MultibuttonInputPanel admin_request_submission_view_panel = new MultibuttonInputPanel(button_labels,
                button_functions);

        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_request_submission_view_panel, BorderLayout.CENTER);

        // for the back button
        Runnable back_button_function = MainView::showAdminRequestsMenuView;
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> back_button_function.run());

        right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.EAST);
        main_frame.setVisible(true);

    }

    // this method is to show the tutorial request submission view
    public static void showTutorialRequestSubmissionView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Tutorial Request Submission</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // the buttons and their event handlers
        String input_labels[] = { "Tutorial Description", "Proposed Date",
                "Proposed Time", "Student Level", "Student Amount" };

        Runnable button_functions[] = { MainView::showAdminRequestsMenuView, MainView::showRequestSubmissionChoiceView;  };

        RequestSubmissionViewPanel tutorial_request_submission_view_panel = new RequestSubmissionViewPanel(
                input_labels, button_functions);

        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(tutorial_request_submission_view_panel, BorderLayout.CENTER);

        // for the back button
        Runnable back_button_function = MainView::showRequestSubmissionChoiceView;
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> back_button_function.run());

        right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.EAST);
        main_frame.setVisible(true);

    }

    // this method is to show the resource request submission view

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