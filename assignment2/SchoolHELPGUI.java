import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class controls the data flow, and controls the communications between
 * the GUI classes and the data classes.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya - E2000426
 *         Date: 2022-24-12
 */
public class SchoolHELPGUI {
    /**
     * This is the single instance of the SchoolHELP class.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static SchoolHELP SchoolHELP;

    // global var to keep track of who's currently logged in in this instance
    private static User currentUser = null;

    // to check if its the first time login or not
    private static boolean isFirstTimeLogin = true;

    /**
     * This method is called when the program is first run, it will attempt to load
     * a SchoolHELP object from the appdata folder. It then calls the MainView
     * method to load the GUI.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
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

    // this method is called when the User clicks on the Exit button, this method
    // attempts to save the SchoolHELP object to a file
    /**
     * This method is called when the User clicks on the Exit button, this method
     * attempts to save the SchoolHELP object to a file.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     */
    public static void saveSchoolHELPInstance() {
        try {
            // using FileManager class
            FileManager.saveData(SchoolHELP, "SchoolHELP.ser");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method searches for requests based on the requestID, and returns the
     * first match.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param search_id - the requestID to search for.
     * @return Request - the first match of the requestID.
     */
    public static Request searchForRequest(int search_id) {
        // iterate over the stream arraylist of all requests of all schools
        return SchoolHELP.getAllRequests().stream().filter(request -> request.getRequestID() == search_id).findFirst()
                .orElse(null);
    }

    /**
     * This method searches for offers based on the offerID, and returns the first
     * match.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param search_id - the offerID to search for.
     * @return Offer - the first match of the offerID.
     */
    public static Offer searchForOffer(int search_id) {
        // iterate over the stream arraylist of all offers of all requests of all
        // schools
        return SchoolHELP.getAllRequests().stream().flatMap(request -> request.getOffers().stream())
                .filter(offer -> offer.getOfferID() == search_id).findFirst().orElse(null);
    }

    // * getters and setters
    /**
     * This method returns the current user that is logged in.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @return User - the current user that is logged in.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * This method sets the current user that is logged in.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param currentUser - the current user that is logged in.
     */
    public static void setCurrentUser(User currentUser) {
        SchoolHELPGUI.currentUser = currentUser;
    }

    /**
     * This method sets the first time login boolean.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param isFirstTimeLogin - the first time login boolean.
     * 
     */
    public static void setFirstTimeLogin(boolean isFirstTimeLogin) {
        SchoolHELPGUI.isFirstTimeLogin = isFirstTimeLogin;
    }

    /**
     * This method returns an ArrayList of schools, taken from the ArrayList of
     * School in SchoolHELP.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @return ArrayList<School> - the ArrayList of schools.
     */
    public static ArrayList<School> getAllSchools() {
        return SchoolHELP.getSchools();
    }

    // * CLASS HELPER METHODS */
    /**
     * This method handles the login process for both admin and volunteer, returns
     * true if the user is found and is valid.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param username - the username of the user.
     * @param password - the password of the user.
     * @param userType - the type of the user, either "ADMIN" or "VOLUNTEER".
     * @return boolean - true if the user is found and is valid.
     */
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

    /**
     * This method returns the first time login boolean.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @return boolean - the first time login boolean.
     */
    public static boolean isFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    /**
     * This method takes the fieldmap from the admin screen and creates a new school
     * based on its values.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param fieldMap - the fieldmap from the admin screen.
     * @return School - the new school that was created.
     */
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

    /**
     * This method takes the Request as value and returns the offers of that
     * request.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param value - the request instance.
     * @return ArrayList<Offer> - the offers of the request.
     */
    public static ArrayList<Offer> getOffersOfRequest(Request value) {
        // get the offers of the request by calling the appropriate method in the
        // SchoolHELP class
        return SchoolHELP.getRequest(value.getRequestID()).getOffers();
    }

    /**
     * This method takes the Offer as value and returns the boolean of whether the
     * offer is successfully rejected or not.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param value - the offer instance.
     * @return boolean - true if the offer is successfully rejected.
     */
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

    /**
     * This method takes the Offer as value and returns the boolean of whether the
     * offer is successfully accepted or not.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param value - the offer instance.
     * @return boolean - true if the offer is successfully accepted.
     */
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

    /**
     * This method returns an arraylist of all the requests from SchoolHELP class
     * instance.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @return ArrayList<Request> - the arraylist of all the requests.
     */
    public static ArrayList<Request> getAllRequests() {
        // get all the requests frrom all the schools
        return SchoolHELP.getAllRequests();
    }

    /**
     * This method takes the Request and Remarks from the volunteer screen and
     * creates a new offer based on them.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param selected_request - the request instance.
     * @param remarks          - the remarks from the volunteer screen.
     */
    public static void createNewOffer(Request selected_request, String remarks) {
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

    /**
     * This method takes the HashMap of the fields from the volunteer registration
     * and creates a new volunteer user based on them.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param saved_fields - the HashMap of the fields from the volunteer
     *                     registration
     *                     screen.
     * @return Volunteer - the new volunteer user.
     */
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

    /**
     * This method takes the HashMap of the fields from the schooladmin registration
     * and
     * creates a new schooladmin user based on them.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param saved_fields - the HashMap of the fields from the school registration
     *                     screen.
     * @param school       - the school instance.
     * @return School - the new school user.
     * 
     */
    public static SchoolAdmin createNewAdminUser(HashMap<String, String> saved_fields, School school) {
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

        // return the newAdmin
        return newAdmin;
    }

    /**
     * This method takes the HashMap of the fields from the request submission and
     * creates a new request based on them.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param saved_fields - the HashMap of the fields from the request submission
     *                     screen.
     * @param school       - the school instance.
     */
    public static void createNewRequest(HashMap<String, String> saved_fields, School school) {
        // this method takes in a Hashmap of the fields from the request submission
        // screen and creates a new request object based on them

        // get the values from the fieldmap
        int requestID = ((int) (Math.random() * 1000));
        LocalDateTime requestDate = LocalDateTime.now();
        String requestStatus = "NEW";
        String requestDescription = (String) saved_fields.get("Request Description");

        // create a new request object
        Request newRequest = new Request(requestID, requestDate, requestStatus, requestDescription, school);

        // add the new request to the request list
        SchoolHELP.getSchool(school.getSchoolName()).addRequest(newRequest);

    }

    /**
     * This method takes the HashMap of the fields from the admin profile screen and
     * updates the profile of the current user.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param inputs          - the HashMap of the fields from the admin profile
     *                        screen.
     * @param thisSchoolAdmin - the current school admin user.
     */
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

    /**
     * This method returns a list of all the Users in SchoolHELP.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @return ArrayList<User> - the list of all the users in SchoolHELP.
     */
    public static ArrayList<User> getAllUsers() {
        // method that returns a array of all the users in SchoolHELP
        return SchoolHELP.getUsers();
    }

    /**
     * This method checks if the school exists in the ArrayList in SchoolHELP class
     * or not.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param string - the name of the school.
     * @return boolean - true if the school exists, false if it doesn't.
     */
    public static boolean checkIfSchoolExists(String string) {
        // method that checks if the school exists in the ArrayList in SchoolHELP class
        // or not
        for (School school : SchoolHELP.getSchools()) {
            if (school.getSchoolName().equals(string)) {
                return true;
            }
        }
        return false;
    }

}
