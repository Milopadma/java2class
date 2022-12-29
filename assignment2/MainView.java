// this class is responsible for the main view of the application GUI, 
// all GUI-related classes are either based on this class, or called from this class
// GUI related class elements are allowed to call SchoolHELPGUI methods to pass data
// to the data classes

// GUI elements import

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    // all admin-related view panels
    // static MultibuttonInputPanel admin_schools_view_panel;
    // static MultifieldInputPanel admin_register_school_view_panel;
    // static MultifieldInputPanel admin_register_school_admin_view_panel;
    // static StandardInfoPanel admin_school_registration_complete_view_panel;
    // static ThreestepTimelinePanel admin_timeline_view_panel;

    // all volunteer-related view panels
    // todo

    // the sidemenu views
    static SidemenuView admin_sidemenu_view_panel;
    static SidemenuView volunteer_sidemenu_view_panel;

    // the right menu panel
    static JPanel right_menu_view_panel = new JPanel();

    // to save the values of the MultifieldInputPanel
    // static Map<String, String> multifield_input_panel_values;

    // private static MultibuttonInputPanel admin_requests_view_panel;

    // private static MultibuttonInputPanel admin_offers_view_panel;

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

    // public static void saveMultifieldTextFields(String[] fieldNames, String[]
    // fieldValues) {
    // // save fieldNames and fieldValues as a connected hashmap using Stream()
    // // and Collectors
    // System.out.println("Saving multifield text fields: " + fieldNames[0] + " " +
    // fieldValues[0]);
    // // ! BUG fieldValues[0] is null
    // Map<String, String> fieldMap = IntStream.range(0, fieldNames.length)
    // .boxed()
    // .collect(Collectors.toMap(i -> fieldNames[i], i -> fieldValues[i]));
    // // ! this throws a null exception
    // // ? is method this even used?
    // multifield_input_panel_values = fieldMap;
    // }

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
        admin_login_view_panel = new AdminLoginViewPanel(SchoolHELPGUI.isFirstTimeLogin());
        main_frame.add(admin_login_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // show VolunteerLoginView (enter username and password for volunteers)
    public static void showVolunteerLoginView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();
        volunteer_login_view_panel = new VolunteerLoginViewPanel();
        main_frame.add(volunteer_login_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // initialize the side menu view
    public static void showSideMenuView() {
        admin_sidemenu_view_panel = new SidemenuView(SchoolHELPGUI.getCurrentUser());
        main_frame.add(admin_sidemenu_view_panel, BorderLayout.WEST);
    }

    // * */ ADMIN USER FUNCTIONALITY STAGE
    // * THIS STAGE HAS ALL METHODS AND showX() METHODS RELATED TO THE ADMIN USER */
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
        JLabel subtitle_label = new JLabel("<html><h3>Schools - Choose an option below</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // buttons for the admin school menu view
        JButton register_button = new JButton("Register a new School");
        JButton register_admin_button = new JButton("Register a new School Admin");
        JButton back_button = new JButton("Back");

        // action listeners for the buttons
        register_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showAdminRegisterSchoolView();
        });
        register_admin_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showAdminRegisterSchoolAdminView();
        });
        back_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showUserChoiceView();
        });

        // create the array of buttons
        JButton buttons[] = { register_button, register_admin_button, back_button };

        // create the panel with the buttons
        MultibuttonInputPanel admin_schools_view_panel = new MultibuttonInputPanel(buttons);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());

        // add the elements to their respective panels
        main_frame.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_schools_view_panel, BorderLayout.CENTER);

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
        // String button_labels[] = { "View all Requests for this school", "View every
        // Request from all Schools" };

        // // an array of functions to be called when the buttons are clicked
        // Runnable button_functions[] = { MainView::showRequestSubmissionForSchoolView,
        // MainView::showAllRequestsView };

        // buttons for the admin school menu view
        JButton submit_request_button = new JButton("Submit a Request for this School");
        submit_request_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showRequestSubmissionForSchoolView();
        });
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showUserChoiceView();
        });

        // create the array of buttons
        JButton buttons[] = { submit_request_button, back_button };

        // create the panel with the buttons
        MultibuttonInputPanel admin_requests_view_panel = new MultibuttonInputPanel(buttons);

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
        JLabel subtitle_label = new JLabel("<html><h3>Offers</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // buttons for the admin school menu view
        JButton review_offers_for_school_button = new JButton("Review Offers for this School");
        review_offers_for_school_button.addActionListener(e -> {
            showReviewsForOffersView();
        });
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> {
            showAdminSchoolsMenuView();
        });
        JButton buttons[] = { review_offers_for_school_button, back_button };

        // create the panel with the buttons
        MultibuttonInputPanel admin_offers_view_panel = new MultibuttonInputPanel(buttons);

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
    // method 1/2 of the school side menu for admin
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
        String input_labels[] = { "School Name", "School Address", "School Phone Number", "School Email" };
        MultifieldInputPanel admin_register_school_view_panel = new MultifieldInputPanel(input_labels);

        // for the buttons to have a panel
        JPanel buttons_panel = new JPanel();
        // for the next button, saves the input fields locally in this function first
        JButton next_button = new JButton("Next");
        // next button will show the next panel and call the getSavedFields method of
        // the panel and save the fields to a hashmap
        next_button.addActionListener(e -> {
            // get the saved fields and saved to Hashmap
            HashMap<String, String> saved_fields = admin_register_school_view_panel.getSavedFields();
            // call the createSchool method of the SchoolHELPGUI class
            SchoolHELPGUI.createSchool(saved_fields);
            // then invoke the showAdminRegisterSchoolAdminView method
            showAdminRegisterSchoolAdminView();
        });

        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> {
            showAdminSchoolsMenuView();
        });

        // add the buttons to the panel
        buttons_panel.add(next_button);
        buttons_panel.add(back_button);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());

        // add the elements to the panel
        main_frame.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_register_school_view_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(buttons_panel, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // to show the school admin registration panel view
    // method 2/2 of the school side menu for admin
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
        String input_labels[] = { "Username", "Password", "Fullname", "Email",
                "Phone Number", "Staff ID", "Position" };

        // create the panel with the buttons
        MultifieldInputPanel admin_register_school_admin_view_panel = new MultifieldInputPanel(input_labels);

        // for the buttons to have a panel
        JPanel buttons_panel = new JPanel();

        // buttons for this panel
        JButton next_button = new JButton("Next");
        JButton back_button = new JButton("Back");

        // next button will show the next panel and call the getSavedFields method of
        // the panel and save the fields to a hashmap
        next_button.addActionListener(e -> {
            // get the saved fields and saved to Hashmap
            HashMap<String, String> saved_fields = admin_register_school_admin_view_panel.getSavedFields();

            // get the current user's schoool
            School current_school = ((SchoolAdmin) SchoolHELPGUI.getCurrentUser()).getSchool();

            // create a new school object by calling from SchoolHELPGUI
            SchoolHELPGUI.createNewAdminUser(saved_fields, current_school);

            // show the next panel
            showSchoolAdminAndSchoolCompletionView();
        });
        back_button.addActionListener(e -> {
            // show the previous panel
            showAdminSchoolsMenuView();
        });

        // add the buttons to the panel
        buttons_panel.add(next_button);
        buttons_panel.add(back_button);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_register_school_admin_view_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(buttons_panel, BorderLayout.SOUTH);

        main_frame.setVisible(true);
    }

    // this is shown when both a school and school admin is registered
    public static void showSchoolAdminAndSchoolCompletionView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // need to create buttons here so that they can be passed into the
        // standardinfopanel object, and since Runnable[] does not support adding params
        JButton view_full_info_button = new JButton("View Full Info");
        view_full_info_button.addActionListener(e -> {
            // show the school info view
            // showSchoolInfoView(newSchool, true);
        });

        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> {
            // go back to the admin schools menu view
            showAdminSchoolsMenuView();
        });

        // to be able to pass in the view_full_info_button and back_button to the
        // standardinfopanel
        JButton[] buttons = { view_full_info_button, back_button };

        // create the standardinfopanel
        StandardInfoPanel admin_school_registration_complete_view_panel = new StandardInfoPanel(
                "School and SchoolAdmin registration complete!",
                "You can now login to the SchoolHELP app with the SchoolAdmin credentials you just registered.",
                buttons);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());

        // add the elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_school_registration_complete_view_panel, BorderLayout.CENTER);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);

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
        // buttons for this panel
        JButton submit_request_button = new JButton("Submit a Request");
        submit_request_button.addActionListener(e -> showRequestSubmissionChoiceView());
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> showAdminRequestsMenuView());
        JButton buttons[] = { submit_request_button, back_button };

        // the panel view
        MultibuttonInputPanel admin_request_submission_view_panel = new MultibuttonInputPanel(buttons);

        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_request_submission_view_panel, BorderLayout.CENTER);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
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
        // String button_labels[] = { "Tutorial Request", "Resource Request", "Back" };
        JButton tutorial_request_button = new JButton("Tutorial Request");
        tutorial_request_button.addActionListener(e -> showTutorialRequestSubmissionView());
        JButton resource_request_button = new JButton("Resource Request");
        resource_request_button.addActionListener(e -> showResourceRequestSubmissionView());
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> showRequestSubmissionForSchoolView());
        JButton buttons[] = { tutorial_request_button, resource_request_button, back_button };

        // the panel view
        MultibuttonInputPanel admin_request_submission_view_panel = new MultibuttonInputPanel(buttons);

        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_request_submission_view_panel, BorderLayout.CENTER);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
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
        // labels for the input JTextFields
        String input_labels[] = { "Tutorial Description", "Proposed Date", "Proposed Time", "Student Level",
                "Student Amount" };

        // create the panel view object
        MultifieldInputPanel tutorial_request_submission_view_panel = new MultifieldInputPanel(input_labels);

        // buttons for this panel
        JButton done_button = new JButton("Done");
        // done button saves the fields
        done_button.addActionListener(e -> {
            try {
                // get the input fields
                HashMap<String, String> saved_fields = tutorial_request_submission_view_panel.getSavedFields();
                SchoolAdmin current_user = (SchoolAdmin) SchoolHELPGUI.getCurrentUser();
                School thisSchool = current_user.getSchool();
                // call the method to save the request
                SchoolHELPGUI.createNewRequest(saved_fields, thisSchool);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // show the admin requests menu view
            showAdminRequestsMenuView();
        });

        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> showRequestSubmissionChoiceView());

        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(tutorial_request_submission_view_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(done_button, BorderLayout.SOUTH);
        right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);

    }

    // this method is to show the resource request submission view
    public static void showResourceRequestSubmissionView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Resource Request Submission</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // labels for the input JTextFields
        String input_labels[] = { "Resource Description", "Resource Type", "Amount Expected" };

        // create the panel view object
        MultifieldInputPanel resource_request_submission_view_panel = new MultifieldInputPanel(input_labels);

        // buttons for this panel
        JButton done_button = new JButton("Done");
        // done button saves the fields
        done_button.addActionListener(e -> {
            try {
                // get the input fields
                HashMap<String, String> saved_fields = resource_request_submission_view_panel.getSavedFields();
                SchoolAdmin current_user = (SchoolAdmin) SchoolHELPGUI.getCurrentUser();
                School thisSchool = current_user.getSchool();
                // call the method to save the request
                SchoolHELPGUI.createNewRequest(saved_fields, thisSchool);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // show the admin requests menu view
            showAdminRequestsMenuView();
        });

        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> showRequestSubmissionChoiceView());

        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(resource_request_submission_view_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(done_button, BorderLayout.SOUTH);
        right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // * */ show METHODS FOR ADMIN OFFERS MENU]
    // methods below are concerned about the Admin Offers Menu, from the use cases
    // of Reviewing Offers and Accepting/Rejecting Offers for Requests of that
    // school
    /**
     * Only for Admin offers Menu
     */
    public static void showReviewsForOffersView() {
        // using TableModelViewPanel class to display the table, from the Requests data
        // of this school
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Viewing Requests</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // the buttons and their event handlers
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> MainView.showAdminOffersMenuView());

        // add the buttons to the JButton array
        JButton buttons[] = { back_button };

        // get the current user's school's available requests for offers
        ArrayList<Request> thisSchoolRequestsForOffers = new ArrayList<Request>();

        // get the current user
        User currentUser = SchoolHELPGUI.getCurrentUser();

        try {
            if (((SchoolAdmin) currentUser).getSchool().getRequests().size() == 0)
            // if there are no requests for offers
            {
                // display a message
                JOptionPane.showMessageDialog(null, "There are no requests for offers for your school.");

                // show the admin offers menu view
                showAdminOffersMenuView();

            } else {
                // get the requests for offers
                thisSchoolRequestsForOffers = ((SchoolAdmin) currentUser).getSchool().getRequests();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create the column names
        String column_names[] = { "ID", "Status", "Request Date", "Request Type", "City", "Description" };

        // ! TODO VERY unsure that this works
        // function to invoke when a row is clicked, essentially to show the offers of
        // that request
        Runnable row_clicked_function = () -> {
            try {
                // get the selected row
                Object value_as_object = TableModelViewPanel.getSelectedRowValue();

                // get the selected request
                Request selected_request = (Request) value_as_object;

                // show the offers of that request
                showOffersOfRequest(selected_request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        // ! TODO VERY unsure that this works

        // create the table model
        TableModelViewPanel reviews_for_offers_view_panel = new TableModelViewPanel(column_names,
                thisSchoolRequestsForOffers, buttons, row_clicked_function);

        // add these elements to the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(reviews_for_offers_view_panel, BorderLayout.CENTER);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // this method is to show the offers of a request, when its clicked in a table
    // cell
    public static void showOffersOfRequest(Request selected_request) {
        // ask SchoolHELPGUI to search for the request and return the offers of that
        // request object
        if (SchoolHELPGUI.getOffersOfRequest(selected_request) != null) {
            // if there are offers for that request, do this method
            // using TableModelViewPanel class to display the table, from the Requests data
            // of this school
            // clear the right menu panel
            right_menu_view_panel.removeAll();

            // new label as title using html h1
            JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
            title_label.setVerticalAlignment(JLabel.TOP);

            // new label as title using html h1
            JLabel subtitle_label = new JLabel(
                    "<html><h3>Offers of Request</h3></html>" + selected_request.getRequestID());
            subtitle_label.setVerticalAlignment(JLabel.TOP);

            // panel view content
            // the buttons and their event handlers
            JButton back_button = new JButton("Back");
            back_button.addActionListener(e -> MainView.showAdminOffersMenuView());

            // add the buttons to the JButton array
            JButton buttons[] = { back_button };

            // get the current user's school's available requests for offers
            ArrayList<Offer> thisRequestOffers = new ArrayList<Offer>();

            // create the column names
            String column_names[] = { "ID", "Status", "Offer Date", "Volunteer Name", "Remarks" };

            // ! TODO VERY unsure that this works
            // function to invoke when a row is clicked, essentially to show the Offer
            // details of that Offer
            Runnable row_clicked_function = () -> {
                try {
                    // get the selected row
                    Object value_as_object = TableModelViewPanel.getSelectedRowValue();

                    // get the selected request
                    Offer selected_offer = (Offer) value_as_object;

                    // show the offers of that request
                    showOfferDetails(selected_offer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            // ! TODO VERY unsure that thi

            // create the table model
            TableModelViewPanel reviews_for_offers_view_panel = new TableModelViewPanel(column_names,
                    thisRequestOffers, buttons, row_clicked_function);

            // add these elements to the right menu panel
            right_menu_view_panel.setLayout(new BorderLayout());
            right_menu_view_panel.add(title_label, BorderLayout.NORTH);
            right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
            right_menu_view_panel.add(reviews_for_offers_view_panel, BorderLayout.CENTER);

            // then add the right menu panel to the main frame
            main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        } else {
            // show an info view panel that there are no offers for this Request
            // clear the right menu panel
            right_menu_view_panel.removeAll();

            // new label as title using html h1
            JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
            title_label.setVerticalAlignment(JLabel.TOP);

            // new label as title using html h1
            JLabel subtitle_label = new JLabel(
                    "<html><h3>Offers of Request ID: </h3></html>" + selected_request.getRequestID());
            subtitle_label.setVerticalAlignment(JLabel.TOP);

            // panel view content
            // the buttons and their event handlers
            JButton back_button = new JButton("Back");
            back_button.addActionListener(e -> MainView.showAdminOffersMenuView());

            // add the buttons to the JButton array
            JButton buttons[] = { back_button };

            // create the panel view object
            StandardInfoPanel info_view_panel = new StandardInfoPanel("There are no offers", "for this request.",
                    buttons);

            // add these elements to the right menu panel
            right_menu_view_panel.setLayout(new BorderLayout());
            right_menu_view_panel.add(title_label, BorderLayout.NORTH);
            right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
            right_menu_view_panel.add(info_view_panel, BorderLayout.CENTER);

            // then add the right menu panel to the main frame
            main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
            main_frame.setVisible(true);
        }

    }

    private static void showOfferDetails(Offer selected_offer) {
        // just show the details in a JList
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Offer Details</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // the buttons and their event handlers
        JButton reject_button = new JButton("Reject");
        reject_button.addActionListener(e -> SchoolHELPGUI.rejectOffer(selected_offer));

        JButton accept_button = new JButton("Accept");
        accept_button.addActionListener(e -> SchoolHELPGUI.acceptOffer(selected_offer));

        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> MainView.showAdminOffersMenuView());

        // add the buttons to the JButton array
        JButton buttons[] = { reject_button, accept_button, back_button };

        // turn the selected_offer details to a String Array first by means of stream
        // iteration
        String offer_details[] = selected_offer.toString().split(" ");

        // create the panel view object
        StandardListViewPanel list = new StandardListViewPanel(offer_details, buttons);

        // add these elements to the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(list, BorderLayout.CENTER);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // * */ VOLUNTEER USER FUNCTIONALITY STAGE
    // * THIS STAGE HAS ALL METHODS AND showX() METHODS RELATED TO THE VOLUNTEER
    // * USER */

    public static void showVolunteerRegisterView() {
        // this method is called when a user wants to register as a volunteer, this
        // meethod shows the volunteer register view panel
        // clear the right menu panel
        main_frame.getContentPane().removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Volunteer Registration</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Registering as new Volunteer</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // new multifieldinput panel object
        String input_labels[] = { "Your Username", "Your Password", "Fullname",
                "Phone Number", "Email", "Occupation", "Date of Birth" };
        MultifieldInputPanel volunteer_register_field_panel = new MultifieldInputPanel(input_labels);

        // for the next button, saves the input fields locally in this function first
        JButton next_button = new JButton("Next");
        // next button will show the next panel and call the getSavedFields() method of
        // the panel and save the fields to a Hashmap
        next_button.addActionListener(e -> {
            // get the saved fields
            HashMap<String, String> saved_fields = volunteer_register_field_panel.getSavedFields();
            // create a VOLUNTEER USER object from the saved fields
            Volunteer new_volunteer = SchoolHELPGUI.createNewVolunteerUser(saved_fields);
            // go to volunteer successfull register view
            showVolunteerRegisterSuccessView(new_volunteer);
        });

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(volunteer_register_field_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(next_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);

    }

    private static void showVolunteerRegisterSuccessView(Volunteer new_volunteer) {
        // this method is called when a volunteer user is successfully registered, this
        // method shows the volunteer register success view panel

        // clear the frame
        main_frame.getContentPane().removeAll();

        // buttons
        JButton login_button = new JButton("Login");
        login_button.addActionListener(e -> {
            // go to the login view
            showVolunteerLoginView();
        });

        // panel view content
        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Volunteer Registration</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // add the buttons to the JButton array
        JButton buttons[] = { login_button };

        // create the panel view object
        StandardListViewPanel list = new StandardListViewPanel(
                new String[] { "You have successfully registered as a volunteer user.",
                        "Welcome, " + new_volunteer.getFullname() },
                buttons);

        // add these elements to the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(list, BorderLayout.CENTER);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // this method is called when the volunteer user logs in, this method shows the
    // volunteer main menu view panel
    public static void showVolunteerMenuView() {
        // clear the frame
        main_frame.getContentPane().removeAll();

        // init the sidemenuview
        showSideMenuView();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Volunteer Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel(
                "<html><h3>Viewing Requests</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // table view of available requests of all schools
        String[] column_names = { "ID", "Status", "Request Date", "School Name", "City", "Description" };
        ArrayList<Request> requests = SchoolHELPGUI.getAllRequests();

        // for the "Sort By" dropdown menu in the table { School Name, City, Date }
        JButton sort_by_button = new JButton("Sort By");
        // when clicked, open a dialog menu
        sort_by_button.addActionListener(e -> {
            // create a new dialog menu
            JDialog dialog = new JDialog();
            // set the title
            dialog.setTitle("Sort By");
            // set the size
            dialog.setSize(300, 200);
            // set the layout
            dialog.setLayout(new BorderLayout());
            // set the location
            dialog.setLocationRelativeTo(null);
            // set the modality
            dialog.setModal(true);

            // create the buttons
            JButton school_name_button = new JButton("School Name");
            JButton city_button = new JButton("City");
            JButton date_button = new JButton("Date");

            // add the buttons to the button array
            JButton buttons[] = { school_name_button, city_button, date_button };

            // create the panel view object
            JPanel list = new JPanel();
            list.setLayout(new GridLayout(3, 1));

            // add the buttons to the panel
            for (JButton button : buttons) {
                list.add(button);
            }

            // add the list to the dialog
            dialog.add(list, BorderLayout.CENTER);

            // set the dialog to visible
            dialog.setVisible(true);
        });

        JButton buttons[] = { sort_by_button };

        // runnable function
        Runnable Runnable = () -> {
            // get the selected row
            Object selected_row = TableModelViewPanel.getSelectedRowValue();

            // if the selected row is not null
            if (selected_row != null) {
                // get the selected request
                Request selected_request = (Request) selected_row;

                // show the request details
                showRequestDetails(selected_request);
            }
        };

        // create the table view object
        TableModelViewPanel table = new TableModelViewPanel(column_names, requests, buttons, Runnable);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(table, BorderLayout.CENTER);

        main_frame.setVisible(true);
    }

    private static void showRequestDetails(Request selected_request) {
        // this method shows teh request details from the selected request
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Volunteer Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Request Details</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // the buttons and their event handlers
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> showVolunteerMenuView());

        JButton submit_an_offer_button = new JButton("Submit an Offer");
        submit_an_offer_button.addActionListener(e -> showSubmitOfferView(selected_request));

        // add the buttons to the JButton array
        JButton buttons[] = { submit_an_offer_button, back_button };

        // turn the selected_request details to a String Array first by means of stream
        // iteration
        String request_details[] = selected_request.toString().split(" ");

        // create the panel view object
        StandardListViewPanel list = new StandardListViewPanel(request_details, buttons);

        // add these elements to the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(list, BorderLayout.CENTER);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    public static void showSubmitOfferView(Request selected_request) {
        // this method shows the submit offer view panel to allow the user to input
        // remarks into a JTextArea and have it submitted to the system
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Volunteer Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Submit Offer</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // new text area
        JTextArea text_area = new JTextArea();

        // panel view content
        // the buttons and their event handlers
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> showRequestDetails(selected_request));

        // new submit button
        JButton submit_button = new JButton("Submit");
        submit_button.addActionListener(e -> {
            // get the text from the text area
            String remarks = text_area.getText();

            SchoolHELPGUI.createNewOffer(selected_request, remarks);

            // show a dialog box to show that the offer has been submitted
            JOptionPane.showMessageDialog(null, "Offer Submitted Successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // show the volunteer menu view
            showVolunteerMenuView();
        });

        // create the panel view object
        JPanel list = new JPanel();
        list.setLayout(new BorderLayout());
        list.add(text_area, BorderLayout.CENTER);

        // add these elements to the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(list, BorderLayout.CENTER);
        right_menu_view_panel.add(submit_button, BorderLayout.SOUTH);
        right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
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