import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
// this class controls the data flow, and controls the communications between the GUI classes and the data classes
public class SchoolHELPGUI {
    // init a single instance of the SchoolHELP class
    public static SchoolHELP SchoolHELP;
    // global var to keep track of who's currently logged in in this instance
    private static User currentUser = null;
    // private static SchoolAdmin currentUserAdmin = null;
    // to check if its the first time login or not
    private static boolean isFirstTimeLogin = true;

    public static void main(String[] args) {
        try {
            // first off, we need to serialize the data from the appdata folder
            SchoolHELP = getSchoolHELPFromAppdata();
            SchoolHELP.getUsers().forEach(user -> System.out.println(user));
            // then init the main_view class
            new MainView();

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    private static SchoolHELP getSchoolHELPFromAppdata() {
        // call Filemanager to get the data from the appdata folder
        try {
            SchoolHELP = (SchoolHELP) FileManager.loadData("SchoolHELP.ser");
            if (SchoolHELP == null) {
                // if it fails to load or the ile does not exist, create a new instance
                SchoolHELP = new SchoolHELP();
                // print
                System.out.println("SchoolHELPInstance was Null!");
                return SchoolHELP;
            } else {
                System.out
                        .println("SchoolHELP data loaded from appdata folder: " + SchoolHELP.getUsers().toString());
                return SchoolHELP;
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }

    // notes
    // the reading from file works if the schoolhelp instance is instantiated with
    // schoolhelpgui
    // but it doesnt work if it isnt instantiated with schoolhelpgui
    // ! thus something is wrong with the saving functions

    // this method is called when the User clicks on the Exit button, this method
    // attempts to save the SchoolHELP object to a file
    public static void saveSchoolHELPInstance() {
        try {
            // using FileManager class
            FileManager.saveData(SchoolHELP, "SchoolHELP.ser");

            // ! BUG ; new objects are not saved, loading is fine though

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Request searchForRequest(int search_id) {
        // iterate over the stream arraylist of all requests of all schools
        return SchoolHELP.getAllRequests().stream().filter(request -> request.getRequestID() == search_id).findFirst()
                .orElse(null);
    }

    public static Offer searchForOffer(int search_id) {
        // iterate over the stream arraylist of all offers of all requests of all
        // schools
        return SchoolHELP.getAllRequests().stream().flatMap(request -> request.getOffers().stream())
                .filter(offer -> offer.getOfferID() == search_id).findFirst().orElse(null);
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
    // this method handles the login process for both admin and volunteer, returns
    // true if the user is found and is valid
    public static boolean userLogin(String username, char[] password, String userType) {
        String password_stringified = new String(password);
        try {
            // find out if this user is a volunteer or an admin
            if (userType == "ADMIN" && SchoolHELP.isUserAdmin(username, password_stringified)) {
                // set the current user to the user that just logged in
                setCurrentUser(SchoolHELP.getUser(username, password_stringified));
                // at this point, the user has logged in, so it's not the first time login
                // anymore
                setFirstTimeLogin(false);
                return true;
            } else if (userType == "VOLUNTEER" && SchoolHELP.isUserVolunteer(username, password_stringified)) {
                // set the current user to the user that just logged in
                setCurrentUser(SchoolHELP.getUser(username, password_stringified));
                return true;
            } else {
                // if the user is not found, return false
                return false;
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
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

    public static boolean rejectOffer(Offer selected_offer) {
        // ßet the status of the offer to rejected
        try {
            selected_offer.setOfferStatus("REJECTED");
            // also change the status of it in the volunteer object
            // also change the status of it in the volunteer object, why ? since there are
            // essentially two copies of the same offer in two different arraylists, one in
            // the volunteer object and one in the request object
            selected_offer.getIsOwnedBy().getOffers().stream()
                    .filter(offer -> offer.getOfferID() == selected_offer.getOfferID()).findFirst().get()
                    .setOfferStatus("REJECTED");
            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }

    }

    public static boolean acceptOffer(Offer selected_offer) {
        // set the status of the offer to accepted
        try {
            selected_offer.setOfferStatus("ACCEPTED");
            // also change the status of it in the volunteer object, why ? since there are
            // essentially two copies of the same offer in two different arraylists, one in
            // the volunteer object and one in the request object
            selected_offer.getIsOwnedBy().getOffers().stream()
                    .filter(offer -> offer.getOfferID() == selected_offer.getOfferID()).findFirst().get()
                    .setOfferStatus("ACCEPTED");
            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
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

        // add the new offer to the volunteer
        currentUser.addOffer(newOffer);
    }

    public static Volunteer createNewVolunteerUser(HashMap<String, String> saved_fields) {
        // this method takes in a Hashmap of the fields from the volunteer registration
        // screen and creates a new volunteer user based on them
        try {
            // get the values from the fieldmap
            String username = (String) saved_fields.get("Your Username");
            String password = (String) saved_fields.get("Your Password");
            String fullname = (String) saved_fields.get("Fullname");
            Long phone = Long.parseLong((String) saved_fields.get("Phone Number"));
            String email = (String) saved_fields.get("Email");
            String occupation = (String) saved_fields.get("Occupation");
            int dateofbirth = Integer.parseInt((String) saved_fields.get("Date of Birth"));

            // create a new volunteer object
            Volunteer newVolunteer = new Volunteer(username, password, fullname, email, phone, dateofbirth, occupation);

            // add the new volunteer to the volunteer list
            SchoolHELP.addUser(newVolunteer);

            // iterate over the users and printout the users
            for (User user : SchoolHELP.getUsers()) {
                System.out.println(user);
            }

            // return the new volunteer
            return newVolunteer;
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }

    public static void createNewAdminUser(HashMap<String, String> saved_fields, School school) {
        // this method takes in a Hashmap of the fields from the admin registration
        // screen and creates a new schooladmin user based on them

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
        String schoolName = (String) saved_fields.get("School Name");

        SchoolAdmin newAdmin = null;
        // if schoolName isnt provided, use the school that was passed in
        if (schoolName == null || schoolName.equals("")) {
            newAdmin = new SchoolAdmin(username, password, fullname, email, phone, staffID, position,
                    school);
        } else {
            // if schoolName is provided, use the school that was found
            newAdmin = new SchoolAdmin(username, password, fullname, email, phone, staffID, position,
                    SchoolHELP.getSchool(schoolName));
        }

        // add the new admin to the admin list
        SchoolHELP.addUser(newAdmin);
    }

    public static void createNewRequest(HashMap<String, String> saved_fields, School school) {
        // this method takes in a Hashmap of the fields from the request submission
        // screen and creates a new request object based on them

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

    public static boolean updateAdminProfile(HashMap<String, String> inputs, SchoolAdmin thisSchoolAdmin) {
        // this method takes in a Hashmap of the fields from the admin profile screen
        // and updates the profile of the current user
        try {
            // get the values from the fieldmap
            String username = (String) inputs.get("Username");
            String password = (String) inputs.get("Password");
            String fullname = (String) inputs.get("Fullname");
            String email = (String) inputs.get("Email");
            Long phone = Long.parseLong((String) inputs.get("Phone Number"));
            int staffID = Integer.parseInt((String) inputs.get("Staff ID"));
            String position = (String) inputs.get("Position");

            // update the current user
            thisSchoolAdmin.setUsername(username);
            thisSchoolAdmin.setPassword(password);
            thisSchoolAdmin.setFullname(fullname);
            thisSchoolAdmin.setEmail(email);
            thisSchoolAdmin.setPhone(phone);
            thisSchoolAdmin.setStaffID(staffID);
            thisSchoolAdmin.setPosition(position);

            // return true if the update was successful
            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

}
