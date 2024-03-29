
// GUI elements import
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

/**
 * 
 * this class is responsible for the main view of the application GUI,
 * all GUI-related classes are either based on this class, or called from this
 * class
 * GUI related class elements are allowed to call SchoolHELPGUI methods to pass
 * data to the data classes
 * 
 * @author I Gusti Bagus Milo Padma Wijaya - E2000426
 *         Date: 2022-24-12
 */
public class MainView {
    /**
     * The main frame of the application. Holds all the GUI elements. Acts as
     * container.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static JFrame main_frame = new JFrame();

    /**
     * The menu panel on the right side of the application. Holds most of the GUI
     * elements except for the side menu view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static JPanel right_menu_view_panel = new JPanel();

    /**
     * The constructor of the MainView class. Initializes the main frame attributes
     * and calls the first showUserChoiceView() method to show the login view.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public MainView() {
        // initialize the frame and GUI elements
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setSize(800, 600);
        main_frame.setLocationRelativeTo(null);
        main_frame.setResizable(true);
        main_frame.setTitle("SchoolHELP");
        main_frame.setLayout(new BorderLayout());

        // set the icon on the top left corner of the frame
        main_frame.setIconImage(new ImageIcon("images/helplogo.jpg").getImage());

        // show the splashscreen with HELP logo
        main_frame.add(new JLabel(null, new ImageIcon("images/helplogo.jpg"), JLabel.CENTER),
                BorderLayout.CENTER);
        main_frame.setVisible(true);

        // wait 2 seconds
        try {
            Thread.sleep(2000);
            // clear the frame
            main_frame.getContentPane().removeAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // show the login view
        showUserChoiceView();
    }

    // class methods, every GUI class actions will eventually call one of these
    // public methods to keep the application flow

    // * */ USER SELECT AND LOGIN STAGE
    /**
     * This method is responsible for showing the user choice view panel.
     * It clears the main frame and adds the user choice view panel to the main
     * frame.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showUserChoiceView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();
        UserChoiceViewPanel user_choice_view_panel = new UserChoiceViewPanel();
        main_frame.add(user_choice_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // show AdminLoginView (enter username and password for school admins)
    /**
     * This method is responsible for showing the admin login view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showAdminLoginView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();
        AdminLoginViewPanel admin_login_view_panel = new AdminLoginViewPanel(SchoolHELPGUI.isFirstTimeLogin());
        main_frame.add(admin_login_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // show VolunteerLoginView (enter username and password for volunteers)
    /**
     * This method is responsible for showing the volunteer login view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showVolunteerLoginView() {
        // clear the frame before adding new elements
        main_frame.getContentPane().removeAll();
        VolunteerLoginViewPanel volunteer_login_view_panel = new VolunteerLoginViewPanel();
        main_frame.add(volunteer_login_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // initialize the side menu view
    /**
     * This method is responsible for showing the side menu view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showSideMenuView() {
        SidemenuView admin_sidemenu_view_panel = new SidemenuView(SchoolHELPGUI.getCurrentUser());
        main_frame.add(admin_sidemenu_view_panel, BorderLayout.WEST);
    }

    // * */ ADMIN USER FUNCTIONALITY STAGE
    // * THIS STAGE HAS ALL METHODS AND showX() METHODS RELATED TO THE ADMIN USER */
    // * */ for the sidemenu buttons
    // show AdminSchoolsMenuView (school admin menu with sidemenu dashboard)
    // method 1/4 for the admin sidemenu buttons
    /**
     * This method is responsible for showing the admin schools menu view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showAdminSchoolsMenuView() {
        // clear the frame
        right_menu_view_panel.removeAll();
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
            School newSchool = null;
            // clear the frame before adding new elements
            showAdminRegisterSchoolAdminView(newSchool);
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
    // method 2/4 for the admin sidemenu buttons
    /**
     * This method is responsible for showing the admin requests menu view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showAdminRequestsMenuView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Requests</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // buttons for the admin school menu view
        JButton submit_request_button = new JButton("Submit a Request for this School");
        submit_request_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showRequestSubmissionChoiceView();

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
            showAdminSchoolsMenuView();
        });

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // to show the admin version of the Offers menu, this is for the sidemenu button
    // method 3/4 for the admin sidemenu buttons
    /**
     * This method is responsible for showing the admin offers menu view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
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
            if (((SchoolAdmin) SchoolHELPGUI.getCurrentUser()).getSchool().getRequests().size() == 0)
            // if there are no requests for offers
            {
                // display a message
                JOptionPane.showMessageDialog(null, "There are no requests for offers for your school.");
            } else {
                // clear the frame before adding new elements
                showReviewsForOffersView();
            }
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

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // this method shows the view for editing the profile of the school admin user,
    // just a multifieldinput panel
    /**
     * This method is responsible for showing the edit profile view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showEditProfileView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JPanel title_panel = new JPanel();
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);
        JLabel subtitle_label = new JLabel("<html><h3>Edit Profile</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);
        title_panel.setLayout(new BoxLayout(title_panel, BoxLayout.Y_AXIS));
        title_panel.add(title_label);
        title_panel.add(subtitle_label);

        // panel view content
        String input_labels[] = { "Username", "Password", "Fullname", "Email", "Phone Number", "Staff ID", "Position" };
        MultifieldInputPanel edit_profile_view_panel = new MultifieldInputPanel(input_labels);

        // buttons !
        JPanel buttons_panel = new JPanel();
        JButton save_button = new JButton("Save");
        save_button.addActionListener(e -> {
            try {
                SchoolAdmin thisSchoolAdmin = (SchoolAdmin) SchoolHELPGUI.getCurrentUser();
                HashMap<String, String> inputs = edit_profile_view_panel.getSavedFields();
                // check if the inputs are valid
                boolean wasSuccess = SchoolHELPGUI.updateAdminProfile(inputs, thisSchoolAdmin);
                if (!wasSuccess) {
                    JOptionPane.showMessageDialog(null, "Profile Update was unsuccessful.");
                } else {
                    JOptionPane.showMessageDialog(null, "Profile updated successfully");
                    showAdminLoginView();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                showAdminLoginView();
            }
        });
        JButton back_button = new JButton("Back");
        buttons_panel.setLayout(new BoxLayout(buttons_panel, BoxLayout.Y_AXIS));
        buttons_panel.add(save_button);
        buttons_panel.add(back_button);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());

        // add the elements to their respective panels
        right_menu_view_panel.add(title_panel, BorderLayout.NORTH);
        right_menu_view_panel.add(edit_profile_view_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(buttons_panel, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // * */ show METHODS FOR ADMIN SCHOOL MENU
    // to show the view for registering a new school, step
    // method 1/2 of the school side menu for admin
    /**
     * This method is responsible for showing the register school view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
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
        MultifieldInputPanel admin_register_school_view_panel = new MultifieldInputPanel(input_labels);

        // for the buttons to have a panel
        JPanel buttons_panel = new JPanel();
        // for the next button, saves the input fields locally in this function first
        JButton next_button = new JButton("Next");
        // next button will show the next panel and call the getSavedFields method of
        // the panel and save the fields to a hashmap
        next_button.addActionListener(e -> {
            try {
                if (admin_register_school_view_panel.checkFields() == false) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields");
                    return;
                } else {
                    // get the saved fields and saved to Hashmap
                    HashMap<String, String> saved_fields = admin_register_school_view_panel.getSavedFields();
                    // call the createSchool method of the SchoolHELPGUI class
                    School newSchool = SchoolHELPGUI.createSchool(saved_fields);
                    // then invoke the showAdminRegisterSchoolAdminView method
                    showAdminRegisterSchoolAdminView(newSchool);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
    /**
     * This method is responsible for showing the register school admin view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param School newSchool - the school that the school admin was registered
     *               to.
     */
    public static void showAdminRegisterSchoolAdminView(School newSchool) {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Registering a new School Admin</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        String input_labels[] = {};
        // panel view content
        if (newSchool == null) {
            // this is null if it was called from the admin register school admin view and
            // not from the admin register school view
            input_labels = new String[] { "Username", "Password", "Fullname", "Email",
                    "Phone Number", "Staff ID", "Position", "School Name" };
        } else {
            // this is not null if it was called from the admin register school view
            input_labels = new String[] { "Username", "Password", "Fullname", "Email",
                    "Phone Number", "Staff ID", "Position" };
        }

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
            try {
                // check if all fields are filled
                if (admin_register_school_admin_view_panel.checkFields() == false) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields");
                    return;
                }
                // get the saved fields and saved to Hashmap
                HashMap<String, String> saved_fields = admin_register_school_admin_view_panel.getSavedFields();

                // early return if newSchool is passed as null and the school name doesnt exist
                if (!SchoolHELPGUI.checkIfSchoolExists(saved_fields.get("School Name")) && newSchool == null) {
                    JOptionPane.showMessageDialog(null, "School doesn't exist!");
                    return;
                } else {
                    // if the school isnt null
                    // create a new school object by calling from SchoolHELPGUI
                    SchoolAdmin newSchoolAdmin = SchoolHELPGUI.createNewAdminUser(saved_fields, newSchool);

                    // show the next panel
                    showSchoolAdminAndSchoolCompletionView(newSchool, newSchoolAdmin);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // this is shown when both a school and school admin is registered
    /**
     * This method is responsible for showing the school admin and school completion
     * view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param School      the school object that was created
     * @param SchoolAdmin the school admin object that was created
     */
    public static void showSchoolAdminAndSchoolCompletionView(School newSchool, SchoolAdmin newSchoolAdmin) {
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
            if (newSchool == null) {
                showSchoolInfoView(newSchool, newSchoolAdmin, false);
            } else {
                // show the school info view
                showSchoolInfoView(newSchool, newSchoolAdmin, true);
            }
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
                new String[] { "School Admin and School Registration Complete",
                        "The school admin and school has been registered successfully." },
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
    /**
     * This method is responsible for showing the school info view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param School
     * @param SchoolAdmin
     * @param boolean
     */
    public static void showSchoolInfoView(School newSchool, SchoolAdmin newAdmin, boolean extraInfo) {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>School Info</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        LabelledListViewPanel admin_school_info_view_panel;
        JPanel button_panel = new JPanel();
        if (extraInfo) {
            // show all info about the school and the school admin
            String labels[] = { "School Name:", "School Address:", "School City:", "School Admin Username:",
                    "School Admin Password:",
                    "School Admin Fullname:", "School Admin Email:", "School Admin Phone:", "School Admin StaffID:",
                    "School Admin Position:", "School Admin Schoolname:" };
            String data[] = { newSchool.getSchoolName(), newSchool.getAddress(), newSchool.getCity(),
                    // to view the info of the newly created school admin for this school
                    newAdmin.getUsername(), newAdmin.getPassword(),
                    newAdmin.getFullname(),
                    newAdmin.getEmail(),
                    Long.toString(newAdmin.getPhone()),
                    Integer.toString(newAdmin.getStaffID()),
                    newAdmin.getPosition(),
                    newAdmin.getSchool().getSchoolName() };

            button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));
            JButton back_button = new JButton("Back");
            back_button.addActionListener(e -> showAdminSchoolsMenuView());
            button_panel.add(back_button);
            admin_school_info_view_panel = new LabelledListViewPanel(labels, data);

        } else {
            // just show the newly created school admin, without the school info
            String labels[] = { "School Admin Username:",
                    "School Admin Password:",
                    "School Admin Fullname:", "School Admin Email:", "School Admin Phone:", "School Admin StaffID:",
                    "School Admin Position:", "School Admin Schoolname:" };
            String data[] = {
                    // to view the info of the newly created school admin for this school
                    newAdmin.getUsername(), newAdmin.getPassword(),
                    newAdmin.getFullname(),
                    newAdmin.getEmail(),
                    Long.toString(newAdmin.getPhone()),
                    Integer.toString(newAdmin.getStaffID()),
                    newAdmin.getPosition(),
                    newAdmin.getSchool().getSchoolName() };
            button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));
            JButton back_button = new JButton("Back");
            back_button.addActionListener(e -> showAdminSchoolsMenuView());
            button_panel.add(back_button);
            admin_school_info_view_panel = new LabelledListViewPanel(labels, data);
        }
        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_school_info_view_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(button_panel, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);

    }

    // * */ show METHODS FOR ADMIN REQUESTS MENU]
    // this method is to show the request submission view
    /**
     * This method is responsible for showing the request submission view panel.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showRequestSubmissionChoiceView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JPanel title_panel = new JPanel();
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Request Submission</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // add the title and subtitle to the title panel
        title_panel.setLayout(new BoxLayout(title_panel, BoxLayout.Y_AXIS));
        title_panel.add(title_label);
        title_panel.add(subtitle_label);

        // panel view content
        // the buttons and their event handlers
        JButton tutorial_request_button = new JButton("Tutorial Request");
        tutorial_request_button.addActionListener(e -> showTutorialRequestSubmissionView());
        JButton resource_request_button = new JButton("Resource Request");
        resource_request_button.addActionListener(e -> showResourceRequestSubmissionView());
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> showAdminRequestsMenuView());
        JButton buttons[] = { tutorial_request_button, resource_request_button, back_button };

        // the panel view
        MultibuttonInputPanel admin_request_submission_view_panel = new MultibuttonInputPanel(buttons);

        // add these elements to the right menu panel
        right_menu_view_panel.add(title_panel, BorderLayout.NORTH);
        right_menu_view_panel.add(admin_request_submission_view_panel, BorderLayout.CENTER);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);

    }

    // this method is to show the tutorial request submission view
    /**
     * This method is responsible for showing the tutorial request submission view
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
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
        String input_labels[] = { "Request Description", "Proposed Date", "Proposed Time", "Student Level",
                "Student Amount" };

        // create the panel view object
        MultifieldInputPanel tutorial_request_submission_view_panel = new MultifieldInputPanel(input_labels);

        // buttons panel
        JPanel button_panel = new JPanel();

        // buttons for this panel
        JButton done_button = new JButton("Done");
        // done button saves the fields
        done_button.addActionListener(e -> {
            try {
                if (!tutorial_request_submission_view_panel.checkFields()) {
                    JOptionPane.showMessageDialog(main_frame, "Please fill in all the fields!");
                    return;
                } else {
                    // get the input fields
                    HashMap<String, String> saved_fields = tutorial_request_submission_view_panel.getSavedFields();
                    SchoolAdmin current_user = (SchoolAdmin) SchoolHELPGUI.getCurrentUser();
                    School thisSchool = current_user.getSchool();
                    // call the method to save the request
                    SchoolHELPGUI.createNewRequest(saved_fields, thisSchool);

                    // then show a message dialog
                    JOptionPane.showMessageDialog(main_frame, "Request Submitted Successfully!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // show the admin requests menu view
            showAdminRequestsMenuView();
        });

        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> showRequestSubmissionChoiceView());

        // add the buttons to the button panel
        button_panel.add(done_button);
        button_panel.add(back_button);

        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(tutorial_request_submission_view_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(button_panel, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);

    }

    // this method is to show the resource request submission view
    /**
     * This method is responsible for showing the resource request submission view
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
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
        String input_labels[] = { "Request Description", "Resource Type", "Amount Expected" };

        // create the panel view object
        MultifieldInputPanel resource_request_submission_view_panel = new MultifieldInputPanel(input_labels);

        // buttons panel
        JPanel button_panel = new JPanel();

        // buttons for this panel
        JButton done_button = new JButton("Done");
        // done button saves the fields
        done_button.addActionListener(e -> {
            try {
                if (!resource_request_submission_view_panel.checkFields()) {
                    JOptionPane.showMessageDialog(main_frame, "Please fill in all the fields!");
                    return;
                } else {
                    // get the input fields
                    HashMap<String, String> saved_fields = resource_request_submission_view_panel.getSavedFields();
                    SchoolAdmin current_user = (SchoolAdmin) SchoolHELPGUI.getCurrentUser();
                    School thisSchool = current_user.getSchool();
                    // call the method to save the request
                    SchoolHELPGUI.createNewRequest(saved_fields, thisSchool);

                    // then show a message dialog
                    JOptionPane.showMessageDialog(main_frame, "Request Submitted Successfully!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // show the admin requests menu view
            showAdminRequestsMenuView();
        });

        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> showRequestSubmissionChoiceView());

        // add the buttons to the button panel
        button_panel.add(done_button);
        button_panel.add(back_button);

        // add these elements to the right menu panel
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(resource_request_submission_view_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(button_panel, BorderLayout.SOUTH);

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
    /**
     * This method is responsible for showing the admin offers menu view.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
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
            // get the requests for offers
            thisSchoolRequestsForOffers = ((SchoolAdmin) currentUser).getSchool().getRequests();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create the column names
        String column_names[] = { "ID", "Status", "Request Date", "School", "City", "Description" };

        // create the table model
        RequestTableModelViewPanel reviews_for_offers_view_panel = new RequestTableModelViewPanel(column_names,
                thisSchoolRequestsForOffers, buttons);

        // new label, jtextfield, and button to search for the request
        JPanel search_panel = new JPanel();
        JLabel search_label = new JLabel("Search for Request by ID:");
        search_panel.setLayout(new FlowLayout());
        JTextField search_textfield = new JTextField(10);
        JButton search_button = new JButton("Search");
        search_button.addActionListener(e -> {
            // make sure the textfield is not empty
            if (search_textfield.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a request ID.");
                return;
            }
            // get the text from the search textfield
            String search_text = search_textfield.getText();
            Request search_result = null;
            try {
                // make sure that the search text is an integer
                int search_id = Integer.parseInt(search_text);
                // search for the request
                search_result = SchoolHELPGUI.searchForRequest(search_id);
            } catch (Exception ex) {
                // if it is not, then display a message
                JOptionPane.showMessageDialog(null, "Please enter a valid request ID.");
                return;
            }
            // if the search result is null, then display a message
            if (search_result == null) {
                JOptionPane.showMessageDialog(null, "No request found with that ID.");
            } else {
                // show the offers of that request
                showOffersOfRequest(search_result);
            }
        });

        // add the search components to the search panel
        search_panel.add(search_label);
        search_panel.add(search_textfield);
        search_panel.add(search_button);

        // add these elements to the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(reviews_for_offers_view_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(search_panel, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    /**
     * This method is responsible for showing the offers of a request.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param selected_request the request that the user wants to see the offers of.
     */
    public static void showOffersOfRequest(Request selected_request) {
        // ask SchoolHELPGUI to search for the request and return the offers of that
        // request object
        if (!SchoolHELPGUI.getOffersOfRequest(selected_request).isEmpty()) {
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
            ArrayList<Offer> thisRequestOffers = new ArrayList<>();
            thisRequestOffers = SchoolHELPGUI.getOffersOfRequest(selected_request);

            // create the column names
            String column_names[] = { "ID", "Status", "Offer Date", "Volunteer Name", "Remarks" };

            // create the table model
            OfferTableModelViewPanel reviews_for_offers_view_panel = new OfferTableModelViewPanel(column_names,
                    thisRequestOffers, buttons);

            // new label, jtextfield, and button to search for the offers
            JPanel search_panel = new JPanel();
            JLabel search_label = new JLabel("Search for Offer by ID:");
            JTextField search_textfield = new JTextField(10);
            JButton search_button = new JButton("Search");
            search_button.addActionListener(e -> {
                // make sure the textfield is not empty
                if (search_textfield.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter an offer ID.");
                    return;
                }
                // get the text from the search textfield
                String search_text = search_textfield.getText();
                Offer search_result = null;
                try {
                    // make sure that the search text is an integer
                    int search_id = Integer.parseInt(search_text);
                    // search for the request
                    search_result = SchoolHELPGUI.searchForOffer(search_id);
                } catch (Exception ex) {
                    // if it is not, then display a message
                    JOptionPane.showMessageDialog(null, "Please enter a valid offer ID.");
                    return;
                }
                // if the search result is null, then display a message
                if (search_result == null) {
                    JOptionPane.showMessageDialog(null, "No offer found with that ID.");
                } else {
                    // show the offers of that request
                    showOfferDetails(search_result, selected_request);
                }
            });

            // add the search components to the search panel
            search_panel.add(search_label);
            search_panel.add(search_textfield);
            search_panel.add(search_button);

            // add these elements to the right menu panel
            right_menu_view_panel.setLayout(new BorderLayout());
            right_menu_view_panel.add(title_label, BorderLayout.NORTH);
            right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
            right_menu_view_panel.add(reviews_for_offers_view_panel, BorderLayout.CENTER);
            right_menu_view_panel.add(search_panel, BorderLayout.SOUTH);

            // then add the right menu panel to the main frame
            main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
            main_frame.setVisible(true);
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
            back_button.addActionListener(e -> MainView.showReviewsForOffersView());

            // add the buttons to the JButton array
            JButton buttons[] = { back_button };

            // create the panel view object
            StandardInfoPanel info_view_panel = new StandardInfoPanel(
                    new String[] { "No offers for this request yet." },
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

    /**
     * This method is responsible for showing the details of an offer.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param Offer
     * @param Request
     */
    private static void showOfferDetails(Offer selected_offer, Request selected_request_of_this) {
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
        reject_button.addActionListener(e -> {
            boolean wasSuccess = SchoolHELPGUI.rejectOffer(selected_offer);
            if (!wasSuccess) {
                JOptionPane.showMessageDialog(null, "The offer could not be rejected.");
            } else {
                JOptionPane.showMessageDialog(null, "The offer was rejected.");
                showOffersOfRequest(selected_request_of_this);
            }
        });

        JButton accept_button = new JButton("Accept");
        accept_button.addActionListener(e -> {
            boolean wasSuccess = SchoolHELPGUI.acceptOffer(selected_offer);
            if (!wasSuccess) {
                JOptionPane.showMessageDialog(null, "The offer could not be accepted.");
            } else {
                JOptionPane.showMessageDialog(null, "The offer was accepted.");
                showOffersOfRequest(selected_request_of_this);
            }

        });

        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> MainView.showAdminOffersMenuView());

        // add the buttons to the JButton array
        JButton buttons[] = { reject_button, accept_button, back_button };

        // turn the selected_offer details to a String Array first by means of stream
        // iteration, by dividng by date submitted, offer status, offer id, submitted by
        // and offer details
        // parse offerid to string
        String offer_details[] = new String[] { "Date: " + selected_offer.getOfferDate().format(
                DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString() + "Status: " + selected_offer.getOfferStatus()
                + "ID: " +
                Integer.toString(selected_offer.getOfferID()) + "Volunteer Name: "
                + selected_offer.getIsOwnedBy().getFullname() + "Remarks: " +
                selected_offer.getOfferRemarks() };
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

    /**
     * This method is responsible for showing the volunteer register view.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showVolunteerRegisterView() {
        // this method is called when a user wants to register as a volunteer, this
        // meethod shows the volunteer register view panel
        // clear the right menu panel
        right_menu_view_panel.removeAll();
        main_frame.getContentPane().removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Volunteer Registration</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // panel view content
        // new multifieldinput panel object
        String input_labels[] = { "Your Username", "Your Password", "Fullname",
                "Phone Number", "Email", "Occupation", "Date of Birth" };
        MultifieldInputPanel volunteer_register_field_panel = new MultifieldInputPanel(input_labels);

        // the button panel
        JPanel button_panel = new JPanel();

        // for the next button, saves the input fields locally in this function first
        JButton next_button = new JButton("Next");
        // next button will show the next panel and call the getSavedFields() method of
        // the panel and save the fields to a Hashmap
        next_button.addActionListener(e -> {
            try {
                if (volunteer_register_field_panel.checkFields() == false) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                    return;
                } else {
                    // get the saved fields
                    HashMap<String, String> saved_fields = volunteer_register_field_panel.getSavedFields();
                    // create a VOLUNTEER USER object from the saved fields
                    Volunteer new_volunteer = SchoolHELPGUI.createNewVolunteerUser(saved_fields);
                    // go to volunteer successfull register view
                    showVolunteerRegisterSuccessView(new_volunteer);
                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
                return;
            }
        });

        // back button
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> MainView.showVolunteerLoginView());

        // add the buttons to panel
        button_panel.add(back_button);
        button_panel.add(next_button);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(volunteer_register_field_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(button_panel, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    /**
     * This method is responsible for showing the volunteer register success view.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param Volunteer
     */
    private static void showVolunteerRegisterSuccessView(Volunteer new_volunteer) {
        // this method is called when a volunteer user is successfully registered, this
        // method shows the volunteer register success view panel

        // clear the frame
        right_menu_view_panel.removeAll();
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
        StandardInfoPanel list = new StandardInfoPanel(
                new String[] { "You have successfully registered as a volunteer user!" }, buttons);

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
    /**
     * This method is responsible for showing the volunteer main menu view.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showVolunteerMenuView() {
        // clear the frame
        right_menu_view_panel.removeAll();
        main_frame.getContentPane().removeAll();

        // init the sidemenuview
        showSideMenuView();
        JPanel title_panel = new JPanel();
        title_panel.setLayout(new BoxLayout(title_panel, BoxLayout.Y_AXIS));
        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Volunteer Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel(
                "<html><h3>Viewing Requests</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // add the title and subtitle to the title panel
        title_panel.add(title_label);
        title_panel.add(subtitle_label);

        // panel view content
        // table view of available requests of all schools
        String[] column_names = { "ID", "Status", "Request Date", "School Name", "City",
                "Description" };
        ArrayList<Request> requests = SchoolHELPGUI.getAllRequests();
        // the buttons and their event handlers
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> MainView.showAdminOffersMenuView());
        // add the buttons to the JButton array
        JButton buttons[] = { back_button };

        // create the table view object
        RequestTableModelViewPanel table = new RequestTableModelViewPanel(column_names, requests, buttons);

        // new label, jtextfield, and jbutton to seacrh for a request
        JPanel search_panel = new JPanel();
        JLabel search_label = new JLabel("Search request by ID: ");
        JTextField search_field = new JTextField(10);
        JButton search_button = new JButton("Search");
        search_button.addActionListener(e -> {
            // make sure the text field is not empty
            if (search_field.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a search term.");
                return;
            }
            String search_text = search_field.getText();
            Request search_result = null;
            try {
                // make sure that the search text is an integer
                int search_id = Integer.parseInt(search_text);
                // search for the request
                search_result = SchoolHELPGUI.searchForRequest(search_id);
            } catch (Exception ex) {
                // if it is not, then display a message
                JOptionPane.showMessageDialog(null, "Please enter a valid request ID.");
                return;
            }
            // if the search result is null, then display a message
            if (search_result == null) {
                JOptionPane.showMessageDialog(null, "No request found with that ID.");
            } else {
                // show the request details view
                showRequestDetails(search_result);

                // show submit offer
                // showSubmitOfferView(search_result);

            }
        });

        // add the search elements to the title panel
        search_panel.add(search_label);
        search_panel.add(search_field);
        search_panel.add(search_button);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_panel, BorderLayout.NORTH);
        right_menu_view_panel.add(table, BorderLayout.CENTER);
        right_menu_view_panel.add(search_panel, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    /**
     * This method is responsible for showing the request details view.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param Request
     */
    private static void showRequestDetails(Request selected_request) {
        // this method shows teh request details from the selected request
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JPanel title_panel = new JPanel();
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Volunteer Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Request Details</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // add the title and subtitle to the title panel
        title_panel.setLayout(new GridLayout(2, 1));
        title_panel.add(title_label);
        title_panel.add(subtitle_label);

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
        StandardListViewPanel request_details_panel = new StandardListViewPanel(request_details, buttons);

        // add these elements to the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_panel, BorderLayout.NORTH);
        right_menu_view_panel.add(request_details_panel, BorderLayout.CENTER);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    /**
     * This method is responsible for showing the submit offer view.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param Request
     */
    public static void showSubmitOfferView(Request selected_request) {
        // this method shows the submit offer view panel to allow the user to input
        // remarks into a JTextArea and have it submitted to the system
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JPanel title_panel = new JPanel();
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Volunteer Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Enter your remarks:</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // add the title label to the title panel
        title_panel.setLayout(new GridLayout(2, 1));
        title_panel.add(title_label);
        title_panel.add(subtitle_label);

        // new text area
        JTextArea text_area = new JTextArea();

        // panel view content
        JPanel button_panel = new JPanel();
        // the buttons and their event handlers
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> showRequestDetails(selected_request));

        // new submit button
        JButton submit_button = new JButton("Submit");
        submit_button.addActionListener(e -> {
            // get the text from the text area
            String remarks = text_area.getText();
            System.out.println(remarks);

            SchoolHELPGUI.createNewOffer(selected_request, remarks);

            // show a dialog box to show that the offer has been submitted
            JOptionPane.showMessageDialog(null, "Offer Submitted Successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // show the volunteer menu view
            showVolunteerMenuView();
        });

        // add the buttons to the panel
        button_panel.setLayout(new FlowLayout());
        button_panel.add(back_button);
        button_panel.add(submit_button);

        // create the panel view object
        JPanel text_area_panel = new JPanel();
        text_area_panel.setLayout(new BorderLayout());
        text_area_panel.add(text_area, BorderLayout.CENTER);

        // add these elements to the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_panel, BorderLayout.NORTH);
        right_menu_view_panel.add(text_area_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(button_panel, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    /**
     * This method is responsible for showing the volunteer offers menu view.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showVolunteerOffersMenuView() {
        // this goes to a view where it shows all the offers this volunteer has
        // submitted
        // this method shows teh request details from the selected request
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JPanel title_panel = new JPanel();
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Volunteer Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new label as title using html h1
        JLabel subtitle_label = new JLabel("<html><h3>Your Submitted Offers</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // add the title and subtitle to the title panel
        title_panel.setLayout(new GridLayout(2, 1));
        title_panel.add(title_label);
        title_panel.add(subtitle_label);

        // panel view content
        // the buttons and their event handlers
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> showVolunteerMenuView());

        // add the buttons to the JButton array
        JButton buttons[] = { back_button };

        // create the panel view object
        Volunteer volunteer;
        String[] volunteersOffers;
        try {
            volunteer = (Volunteer) SchoolHELPGUI.getCurrentUser();
            // to get the offers of the volunteer and convert them to a string array
            volunteersOffers = volunteer.getOffers().stream().map(offer -> offer.toString()).toArray(String[]::new);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return;
        }
        StandardListViewPanel request_details_panel = new StandardListViewPanel(volunteersOffers, buttons);

        // add these elements to the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());
        right_menu_view_panel.add(title_panel, BorderLayout.NORTH);
        right_menu_view_panel.add(request_details_panel, BorderLayout.CENTER);
        right_menu_view_panel.add(back_button, BorderLayout.SOUTH);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    // * METHODS ONLY FOR SCHOOLHELP ADMINS, SCHOOLADMINS OF HELP */
    /**
     * This method is responsible for showing the school HELP admin menu view.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void showSchoolHELPAdminView() {
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new subtitle label
        JLabel subtitle_label = new JLabel("<html><h3>Choose an option below</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // buttons for the admin school menu view
        JButton show_all_users_button = new JButton("Show all Users");
        JButton back_button = new JButton("Back");

        // action listeners for the buttons
        show_all_users_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showAllUsersView();
        });
        back_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showUserChoiceView();
        });

        // create the array of buttons
        JButton buttons[] = { show_all_users_button, back_button };

        // create the panel with the buttons
        MultibuttonInputPanel schoolhelp_admin_view_panel = new MultibuttonInputPanel(buttons);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());

        // add the elements to their respective panels
        main_frame.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(schoolhelp_admin_view_panel, BorderLayout.CENTER);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);
    }

    /**
     * This method is responsible for showing the all users table view.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    private static void showAllUsersView() {
        // this method takes all the users and shows them in a list view
        // clear the right menu panel
        right_menu_view_panel.removeAll();

        // new label as title using html h1
        JLabel title_label = new JLabel("<html><h1>SchoolHELP Admin Menu</h1></html>");
        title_label.setVerticalAlignment(JLabel.TOP);

        // new subtitle label
        JLabel subtitle_label = new JLabel("<html><h3>Showing all Users</h3></html>");
        subtitle_label.setVerticalAlignment(JLabel.TOP);

        // content view panel
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> {
            // clear the frame before adding new elements
            showUserChoiceView();
        });
        JButton[] buttons = { back_button };

        // the list
        String[] labels = { "Username: ", "Password: ", "Fullname: ", "Email: ", "Phone number: " };
        ArrayList<User> data = SchoolHELPGUI.getAllUsers();

        // just iterate over the two String arrays
        // and add the labels and data to the table
        JPanel table_panel = new JPanel();
        UserTableModelViewPanel table_model = new UserTableModelViewPanel(labels, data, buttons);
        table_panel.add(table_model);

        // set the layout of the right menu panel
        right_menu_view_panel.setLayout(new BorderLayout());

        // add the elements to their respective panels
        main_frame.add(title_label, BorderLayout.NORTH);
        right_menu_view_panel.add(subtitle_label, BorderLayout.NORTH);
        right_menu_view_panel.add(table_panel, BorderLayout.CENTER);

        // then add the right menu panel to the main frame
        main_frame.add(right_menu_view_panel, BorderLayout.CENTER);
        main_frame.setVisible(true);

    }
}