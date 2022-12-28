import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
// this class controls the data flow, and controls the communications between the GUI classes and the data classes
public class SchoolHELPGUI {
    // init a single instance of the SchoolHELP class
    private static SchoolHELP SchoolHELP = new SchoolHELP();
    // global var to keep track of who's currently logged in in this instance
    private static User currentUser = null;
    // private static SchoolAdmin currentUserAdmin = null;
    // to check if its the first time login or not
    private static boolean isFirstTimeLogin = true;

    public static void main(String[] args) {
        // init the main view
        try {
            MainView main_view = new MainView();
            // send an event listener to the login button
            // when the login button is clicked, the login() method is called
            // main_view.login_view_panel.AdminButton.addActionListener(event -> {
            // System.out.println("Admin button clicked");
            // MainView.showAdminLoginView();
            // });
            // main_view.VolunteerButton.addActionListener(e -> {
            // System.out.println("Volunteer button clicked");
            // MainView.showVolunteerLoginView();
            // });
            // main_view.admin_login_view_panel.UsernameTextField.addActionListener(e -> {
            // System.out.println("Username text field clicked");
            // MainView.showAdminLoginView();
            // });
        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    // * getters and setters
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SchoolHELPGUI.currentUser = currentUser;
    }

    public static void setFirstTimeLogin(boolean isFirstTimeLogin) {
        SchoolHELPGUI.isFirstTimeLogin = isFirstTimeLogin;
    }

    public static ArrayList<School> getAllSchools() {
        return SchoolHELP.getSchools();
    }

    // * CLASS HELPER METHODS */
    // this method handles the login process for admin user
    public static void adminLogin(String username, char[] password) {
        String password_stringified = new String(password);
        try {
            // check if the username and password are correct
            if (SchoolHELPGUI.checkLogin(username, password_stringified)) {
                // if the login is successful, show the admin menu
                MainView.showAdminSchoolsMenuView();
            } else {
                // if the login is unsuccessful, show an error message
                JOptionPane.showMessageDialog(null, "Incorrect username or password", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    // this method is called when the login button is clicked
    public static boolean checkLogin(String username, String password) {
        if (SchoolHELP.isUserAdmin(username, password)) {

            // call the AdminScreen class
            // AdminScreen admin_screen = new AdminScreen();
            // set the current user to the admin
            currentUser = SchoolHELP.getUser(username, password);
            // set the first time login to false
            isFirstTimeLogin = false;
            // return true
            return true;
        } else {
            // return false
            return false;
        }
    }

    public static boolean isFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    // method that takes the fieldmap from the admin screen and creates a new school
    // based on its values
    public static School createNewSchool(Map<String, String> fieldMap) {
        // get the values from the fieldmap
        String schoolName = (String) fieldMap.get("School Name");
        int schoolID = ((int) (Math.random() * 1000));
        String schoolAddress = (String) fieldMap.get("School Address");
        String schoolCity = (String) fieldMap.get("School City");

        // create a new school object
        School newSchool = new School(schoolName, schoolID, schoolAddress, schoolCity);

        // add the new school to the school list
        SchoolHELP.addSchool(newSchool);

        // return the new school
        return newSchool;
    }

    public static ArrayList<Offer> getOffersOfRequest(Request value) {
        // get the offers of the request by calling the appropriate method in the
        // SchoolHELP class
        return SchoolHELP.getRequest(value.getRequestID()).getOffers();
    }

    public static void rejectOffer(Offer selected_offer) {
        // ßet the status of the offer to rejected
        selected_offer.setOfferStatus("REJECTED");

    }

    public static void acceptOffer(Offer selected_offer) {
        // set the status of the offer to accepted
        selected_offer.setOfferStatus("ACCEPTED");
    }
}
