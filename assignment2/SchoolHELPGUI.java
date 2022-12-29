import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

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
        try {
            // first off, we need to serialize the data from the appdata folder
            SchoolHELP = getSchoolHELPFromAppdata();
        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    private static SchoolHELP getSchoolHELPFromAppdata() {
        SchoolHELP SchoolHELPInstance = null;
        // call Filemanager to get the data from the appdata folder
        try {
            SchoolHELPInstance = (SchoolHELP) FileManager.loadData("SchoolHELP.ser");
            System.out.println("SchoolHELP data loaded from appdata folder: " + SchoolHELPInstance.toString());
            System.out
                    .println("SchoolHELP data loaded from appdata folder: " + SchoolHELPInstance.getUsers().toString());
            return SchoolHELPInstance;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
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
    public static void userLogin(String username, char[] password) {
        String password_stringified = new String(password);
        try {
            // find out if this user is a volunteer or an admin
            if (SchoolHELP.isUserAdmin(username, password_stringified)) {
                // set the current user to the user that just logged in
                setCurrentUser(SchoolHELP.getUser(username, password_stringified));

                // if the user is an admin, show the admin menu
                MainView.showAdminSchoolsMenuView();

                // at this point, the user has logged in, so it's not the first time login
                // anymore
                setFirstTimeLogin(false);
            } else if (SchoolHELP.isUserVolunteer(username, password_stringified)) {
                // set the current user to the user that just logged in
                setCurrentUser(SchoolHELP.getUser(username, password_stringified));

                // if the user is a volunteer, show the volunteer menu
                MainView.showVolunteerMenuView();
            } else {
                // if the user is not found, show an error message
                JOptionPane.showMessageDialog(null, "Incorrect username or password", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static boolean isFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    // method that takes the fieldmap from the admin screen and creates a new school
    // based on its values
    public static School createSchool(HashMap<String, String> fieldMap) {
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

    public static ArrayList<Request> getAllRequests() {
        // get all the requests frrom all the schools
        return SchoolHELP.getAllRequests();
    }

    public static void createNewOffer(Request selected_request, String remarks) {
        // this method takes the Request and Remarks from the volunteer screen and
        // creates a new offer based on them

        // get the current user
        Volunteer currentUser = (Volunteer) getCurrentUser();

        LocalDateTime offerDate = LocalDateTime.now();

        // create a new offer object
        Offer newOffer = new Offer(offerDate, remarks, "PENDING", currentUser);

        // add the new offer to the request
        selected_request.addOffer(newOffer);
    }

    public static Volunteer createNewVolunteerUser(HashMap<String, String> saved_fields) {
        // this method takes in a Hashmap of the fields from the volunteer registration
        // screen and creates a new volunteer user based on them

        // get the values from the fieldmap
        String username = (String) saved_fields.get("Username");
        String password = (String) saved_fields.get("Password");
        String fullname = (String) saved_fields.get("Fullname");
        Long phone = Long.parseLong((String) saved_fields.get("Phone Number"));
        String email = (String) saved_fields.get("Email");
        String occupation = (String) saved_fields.get("Occupation");
        int dateofbirth = Integer.parseInt((String) saved_fields.get("Date of Birth"));

        // create a new volunteer object
        Volunteer newVolunteer = new Volunteer(username, password, fullname, email, phone, dateofbirth, occupation);

        // add the new volunteer to the volunteer list
        SchoolHELP.addUser(newVolunteer);

        // return the new volunteer
        return newVolunteer;
    }

    public static void createNewAdminUser(HashMap<String, String> saved_fields, School school) {
        // this method takes in a Hashmap of the fields from the volunteer registration
        // screen and creates a new volunteer user based on them

        // printout the hashmap
        System.out.println(saved_fields);

        // get the values from the fieldmap
        String username = (String) saved_fields.get("Username");
        String password = (String) saved_fields.get("Password");
        String fullname = (String) saved_fields.get("Fullname");
        String email = (String) saved_fields.get("Email");
        Long phone = Long.parseLong((String) saved_fields.get("Phone Number"));
        int staffID = Integer.parseInt((String) saved_fields.get("Staff ID"));
        String position = (String) saved_fields.get("Position");

        // create a new admin object
        SchoolAdmin newAdmin = new SchoolAdmin(username, password, fullname, email, phone, staffID, position, school);

        // add the new admin to the admin list
        SchoolHELP.addUser(newAdmin);
    }

    public static void createNewRequest(HashMap<String, String> saved_fields, School school) {
        // this method takes in a Hashmap of the fields from the volunteer registration
        // screen and creates a new volunteer user based on them

        // get the values from the fieldmap
        int requestID = ((int) (Math.random() * 1000));
        LocalDateTime requestDate = LocalDateTime.now();
        String requestStatus = "PENDING";
        String requestDescription = (String) saved_fields.get("Request Description");

        // create a new request object
        Request newRequest = new Request(requestID, requestDate, requestStatus, requestDescription, school);

        // add the new request to the request list
        SchoolHELP.getSchool(school.getSchoolName()).addRequest(newRequest);

    }

    // this method is called when the User clicks on the Exit button, this method
    // attempts to save the SchoolHELP object to a file
    public static void saveSchoolHELPInstance() {
        try {
            // using FileManager class
            FileManager.saveData(SchoolHELP, "SchoolHELP.ser");

            // printout a message to the console
            System.out.println("Serialized data is saved in SchoolHELP.ser");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
