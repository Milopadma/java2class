import javax.swing.*;
import java.awt.*;

// this class is responsible for the left side of the main view
// buttons and content varies if its for a School Admin or Volunteer user
public class SidemenuView extends JPanel {
    // GUI element initializations
    JPanel profile_picture_panel = new JPanel();
    JPanel name_occupation_panel = new JPanel();
    JPanel button_panel = new JPanel();

    JButton edit_profile_button = new JButton("Edit Profile");
    JButton schools_button = new JButton("Schools");
    JButton requests_button = new JButton("Requests");
    JButton offers_button = new JButton("Offers");

    JButton logout_button = new JButton("Logout");

    // class constructors
    public SidemenuView(User user) {
        // the school admin view
        if (user.isSchoolAdmin()) {
            // school admin side menu view only has one button difference from the volunteer
            // view
            // setting the profile picture panel layout to be centered vertically and
            // horizontally
            profile_picture_panel.setLayout(new BoxLayout(profile_picture_panel, BoxLayout.Y_AXIS));
            profile_picture_panel.add(generateProfilePicture(user));

            // setting the name and occupation panel layout to be centered vertically and
            // horizontally
            name_occupation_panel.setLayout(new BoxLayout(name_occupation_panel, BoxLayout.Y_AXIS));
            name_occupation_panel.add(new JLabel(user.getUsername()));
            // since its a school admin, assume User is of SchoolAdmin instance and get the
            // position field
            JLabel position_label = new JLabel(((SchoolAdmin) user).getPosition());
            position_label.setAlignmentX(Component.CENTER_ALIGNMENT);
            position_label.setFont(position_label.getFont().deriveFont(Font.ITALIC));
            position_label.setFont(position_label.getFont().deriveFont(12f));
            name_occupation_panel.add(position_label);

            // setting the button panel layout to be stacked vertically
            button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));
            button_panel.add(edit_profile_button);
            button_panel.add(schools_button);
            button_panel.add(requests_button);
            button_panel.add(offers_button);
            button_panel.add(logout_button);

            // adding the event handlers for the buttons
            edit_profile_button.addActionListener(e -> {
                // call the MainView method to show the edit profile view
                MainView.showEditProfileView();
            });
            schools_button.addActionListener(e -> {
                // call the MainView method to show the School admin menu view
                MainView.showAdminSchoolsMenuView();
            });

            requests_button.addActionListener(e -> {
                // call the MainView method to show the Requests admin menu view
                MainView.showAdminRequestsMenuView();
            });

            offers_button.addActionListener(e -> {
                // call the MainView method to show the Offers admin menu view
                MainView.showAdminOffersMenuView();
            });

            logout_button.addActionListener(e -> {
                // call the MainView method to show the login view
                MainView.showUserChoiceView();
            });

            // now add all these elements to the panel
            profile_picture_panel.add(name_occupation_panel);
            setLayout(new BorderLayout());
            add(profile_picture_panel, BorderLayout.NORTH);
            add(button_panel, BorderLayout.SOUTH);

        }
        // the volunteer view
        else if (user.isVolunteer()) {
            // setting the profile picture panel layout to be centered vertically and
            // horizontally
            profile_picture_panel.setLayout(new BoxLayout(profile_picture_panel, BoxLayout.Y_AXIS));
            profile_picture_panel.add(generateProfilePicture(user));

            // setting the name and occupation panel layout to be centered vertically and
            // horizontally
            name_occupation_panel.setLayout(new BoxLayout(name_occupation_panel, BoxLayout.Y_AXIS));
            name_occupation_panel.add(new JLabel(user.getUsername()));
            // since its a volunteer, assume User is of Volunteer instance and get the
            // position field
            JLabel occupation_label = new JLabel(((Volunteer) user).getOccupation());
            occupation_label.setAlignmentX(Component.CENTER_ALIGNMENT);
            occupation_label.setFont(occupation_label.getFont().deriveFont(Font.ITALIC));
            occupation_label.setFont(occupation_label.getFont().deriveFont(12f));
            name_occupation_panel.add(occupation_label);

            // setting the button panel layout to be stacked vertically
            button_panel.setLayout(new GridLayout(4, 1, 10, 10));
            button_panel.add(requests_button);
            // offers button will change to "your offers" button
            offers_button.setText("Your Offers");
            button_panel.add(offers_button);
            button_panel.add(logout_button);

            // adding the event handlers for the buttons
            requests_button.addActionListener(e -> {
                // call the MainView method to show the Requests volunteer menu view
                MainView.showVolunteerMenuView();
            });

            offers_button.addActionListener(e -> {
                // call the MainView method to show the Offers volunteer menu view
                MainView.showVolunteerOffersMenuView();
            });

            // show the logout button on the bottom of the sidemenu stack, which is just a
            // Back button
            // that takes the user back to the login view
            logout_button.addActionListener(e -> {
                // call the MainView method to show the login view
                MainView.showUserChoiceView();
            });

            // now add all these elements to the parent panel
            profile_picture_panel.add(name_occupation_panel);
            setLayout(new BorderLayout());
            add(profile_picture_panel, BorderLayout.NORTH);
            add(button_panel, BorderLayout.SOUTH);
        }
        // catch this edge case
        else {
            System.out.println("Error: User is not a School Admin or Volunteer");
        }

    }

    // this class's helper methods
    // to generate the custom profile picture based on the User's name
    private JPanel generateProfilePicture(User user) {
        // get the first and last name of the user
        String profile_picture_text;
        try {
            profile_picture_text = user.getUsername().substring(0, 1) + user.getUsername().substring(1, 2);
        } catch (Exception e) {
            System.out.println("Error: User's name is not long enough to generate a profile picture");
            profile_picture_text = user.getUsername();
        }

        // create a circular profile picture based on the first and last letter of the
        // user's name
        JPanel profile_picture = new JPanel();
        profile_picture.setBackground(Color.decode("#F2F2F2"));
        profile_picture.setPreferredSize(new Dimension(100, 100));
        profile_picture.setLayout(new GridBagLayout());
        profile_picture.add(new JLabel(profile_picture_text));

        return profile_picture;
    }

}
