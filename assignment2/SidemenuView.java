import javax.swing.*;

// this class is responsible for the left side of the main view
// buttons and content varies if its for a School Admin or Volunteer user
public class SidemenuView extends JPanel {
    // class fields
    // todo

    // GUI element initializations
    // todo

    // class constructors
    public SidemenuView(User user) {
        // the school admin view
        if (user.isSchoolAdmin()) {
            // todo
        }
        // the volunteer view
        else if (user.isVolunteer()) {
            // todo
        }

        // show the logout button on the bottom of the sidemenu stack
        // todo
    }

    // event handlers
    // todo
}
