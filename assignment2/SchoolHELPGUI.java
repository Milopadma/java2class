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
        MainView.showLoginView();
        // send an event listener to the login button
        // when the login button is clicked, the login() method is called
        MainView.AdminButton.addActionListener(e -> {
            System.out.println("Admin button clicked");
            MainView.showAdminLoginView();
        });
        MainView.VolunteerButton.addActionListener(e -> {
            System.out.println("Volunteer button clicked");
            MainView.showVolunteerLoginView();
        });
    }
}
