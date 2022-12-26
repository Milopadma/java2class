import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

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

    // this method handles the login process for admin user
    public static void adminLogin(String username, char[] password) {
        String password_stringified = new String(password);
        try {
            // check if the username and password are correct
            if (SchoolHELPGUI.checkLogin(username, password_stringified)) {
                // if the login is successful, show the admin menu
                MainView.showAdminMenuView();
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

    // getters and setters
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SchoolHELPGUI.currentUser = currentUser;
    }

    // public static SchoolAdmin getCurrentUserAdmin() {
    // return currentUserAdmin;
    // }

    // public static void setCurrentUserAdmin(SchoolAdmin currentUserAdmin) {
    // SchoolHELPGUI.currentUserAdmin = currentUserAdmin;
    // }

    public static boolean isFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    public static void setFirstTimeLogin(boolean isFirstTimeLogin) {
        SchoolHELPGUI.isFirstTimeLogin = isFirstTimeLogin;
    }
}
