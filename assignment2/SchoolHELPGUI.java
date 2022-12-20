
// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Stream;

// this whole class' purpose is to be a user interface
/**
 * {@summary} This class is the top level main class of the
 * SchoolHELPConsole.java file. This is what should be run first as it contains
 * the main method.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class SchoolHELPGUI {
    // init a single instance of the SchoolHELP class
    private static SchoolHELP SchoolHELP = new SchoolHELP();
    // global var to keep track of who's currently logged in in this instance
    private static User currentUser = null;
    // private static SchoolAdmin currentUserAdmin = null;

    // to check if its the first time login or not
    private static boolean isFirstTimeLogin = true;

    // console class methods
    /**
     * {@summary} This method is responsible for displayed the school creation menu
     * and returning the school object that was created.
     * 
     * @return School object
     */
    public static School displaySchoolCreationMenu() {
        while (true) {
            try {
                School newSchool = new School();
                // school name
                Stream.of("\n--Registering a new School (1/2)--", "Please enter the school name: ")
                        .forEach(System.out::println);
                String schoolInput = System.console().readLine();
                if (SchoolHELP.getSchools().stream().anyMatch(s -> s.getSchoolName().equals(schoolInput))) {
                    Stream.of("\nSchool already exists, skipping to Registering a new SchoolAdmin.")
                            .forEach(System.out::println);
                    return newSchool;
                } else {
                    newSchool.setSchoolName(schoolInput);
                }
                // school address
                Stream.of("Please enter the school address: ").forEach(System.out::println);
                newSchool.setAddress(System.console().readLine());
                // get school email
                Stream.of("Please enter the school city: ").forEach(System.out::println);
                newSchool.setCity(System.console().readLine());

                // schoolID is randomly generated, even though this can be done in the class
                // constructor itself, i prefer to have it here
                newSchool.setSchoolID((int) (Math.random() * 1000));

                // giving user feedback that this was successful
                Stream.of("\nSchool added successfully!").forEach(System.out::println);
                return newSchool;

            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }
    }

    /**
     * {@summary} This method displays the menu for registering a new SchoolAdmin
     * and returning the SchoolAdmin object that was created.
     * 
     * @param school
     * @return SchoolAdmin object
     */
    private static SchoolAdmin displaySchoolAdminCreationMenu(School school) {
        // Registering a new school admin
        // school admin username
        while (true) {
            try {
                Stream.of("\n--Creating a new School Admin (2/2)--",
                        "Please enter the school admin username (1/7): ")
                        .forEach(System.out::println);
                String adminUsername = (System.console().readLine());
                // school admin password
                Stream.of("Please enter the school admin password (2/7): ").forEach(System.out::println);
                String adminPassword = (System.console().readLine());
                // school admin fullname
                Stream.of("Please enter the school admin fullname (3/7): ").forEach(System.out::println);
                String adminFullname = (System.console().readLine());
                // school admin email
                Stream.of("Please enter the school admin email (4/7): ").forEach(System.out::println);
                String adminEmail = (System.console().readLine());
                // school admin phone
                Stream.of("Please enter the school admin phone (e.g 628112912, without +) (5/7): ")
                        .forEach(System.out::println);
                Long adminPhone = Long.parseLong(System.console().readLine());
                // school admin ID
                Stream.of("Please enter the school staff ID (6/7): ").forEach(System.out::println);
                int adminID = Integer.parseInt(System.console().readLine());
                // school admin position
                Stream.of("Please enter the school admin position (7/7): ").forEach(System.out::println);
                String adminPosition = (System.console().readLine());

                // passing all the values above to the SchoolAdmin constructor
                SchoolAdmin schoolAdmin = new SchoolAdmin(adminUsername, adminPassword, adminFullname,
                        adminEmail,
                        adminPhone,
                        adminID, adminPosition, school);

                // giving user feedback that this was successful
                Stream.of("\nSchool admin successfully registered!").forEach(System.out::println);
                // returning the newly freshly created SchoolAdmin object
                return schoolAdmin;

            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }
    }

    // for the admin-specific cli menu
    /**
     * {@summary} This method displays the menu and receives user input for the
     * SchoolAdmin user type.
     */
    public static void displayAdminMenu() {
        // this will be a loop that will keep running until the user decides to exit
        while (true) {
            // adding breadcrumbs so the users don't get lost in the menus because i sure
            // did
            System.out.println("\n\n~/SchoolHELP Console > Admin Menu");
            System.out.println("---Welcome to the SchoolHELP Admin Menu--");
            System.out.println("---INFO---");

            // to show the current user's name and position
            System.out.println("Logged in as: " + currentUser.getFullname() + ", position: ("
                    + ((SchoolAdmin) currentUser).getPosition() + ")");
            // to show the current school's name
            System.out.println("School ID: " + ((SchoolAdmin) currentUser).getSchool().getSchoolID() + ", School Name: "
                    + ((SchoolAdmin) currentUser).getSchool().getSchoolName());

            Stream.of("-----------", "1. Register a new school", "2. Edit profile",
                    "3. Submit a request for assistance",
                    "4. Review offers for requests", "5. Back").forEach(System.out::println);
            Stream.of("Please enter your choice: ").forEach(System.out::println);
            // await user input, wrapped in try/catch to 'sanitize' user input
            // in this function scope, currentUser will be of type SchoolAdmin
            SchoolAdmin currentUserAdmin = (SchoolAdmin) currentUser;
            try {
                int choice = Integer.parseInt(System.console().readLine());
                // switch statement to handle user input
                switch (choice) {
                    case 1:
                        try {
                            // wrap this in a try/catch block to catch any exceptions when adding new
                            // SchoolAdmin and School instances
                            try {
                                // call the method responsible for the school creation menu
                                School school = displaySchoolCreationMenu();

                                // add the school to the school list in the SchoolHELP class,
                                // since SchoolHELP is the only class that is a static instance
                                try {
                                    SchoolHELP.addSchool(school);
                                } catch (Exception e) {
                                    System.out.println("\nError: " + e.getMessage());
                                }

                                // then, call the method responsible for the school admin creation menu
                                // school object because its necessary
                                SchoolAdmin schoolAdmin = displaySchoolAdminCreationMenu(school);

                                // adding the school admin to the users list in the SchoolHELP class
                                // as it counts as being a user, and all user instances are held in the
                                // SchoolHELP class
                                SchoolHELP.addUser(schoolAdmin);

                                // adding the newly created schoolAdmin to this the newly created school
                                // instance
                                school.addSchoolAdmin(schoolAdmin);

                            } catch (Exception e) {
                                System.out.println("\nError: " + e.getMessage());
                            }

                            // display the main menu again
                            main(null);
                        } catch (Exception e) {
                            System.out.println("\nError: " + e.getMessage());
                        }
                        break;

                    case 2:
                        // breadcrumbs
                        System.out.println("\n\n~/SchoolHELP Console > Admin Menu > Edit Profile");
                        // Editing the profile of the current user admin profile
                        // display their current profile details of the current user in this instance
                        Stream.of("--Current profile details-- ").forEach(System.out::println);
                        Stream.of(currentUserAdmin.toString()).forEach(System.out::println);
                        System.out.println("\n---------------");
                        // asking the user which detail they want to change
                        Stream.of("Which detail would you like to change? ").forEach(System.out::println);
                        Stream.of("1. Username", "2. Password", "3. Fullname", "4. Email", "5. Phone", "6. Cancel")
                                .forEach(System.out::println);
                        Stream.of("Please enter your choice: ").forEach(System.out::println);
                        int detailChangeChoice = Integer.parseInt(System.console().readLine());
                        // switch statement to handle user input
                        switch (detailChangeChoice) {
                            case 1:
                                // this will be a loop so it can be repeated if the user wants to change
                                // multiple things
                                while (true) {
                                    try {
                                        // username change
                                        Stream.of("Please enter the new username: ").forEach(System.out::println);
                                        String newUsername = (System.console().readLine());

                                        // see if the username exists already in the users list
                                        if (SchoolHELP.isUsernameTaken(newUsername)) {
                                            // if it does, give the user feedback that it already exists
                                            Stream.of("Username already exists!\n").forEach(System.out::println);
                                            continue;

                                        } else {
                                            // if it doesn't, change the username
                                            currentUserAdmin.setUsername(newUsername);
                                            // give the user feedback that the username was changed
                                            Stream.of("Username successfully changed!\n").forEach(System.out::println);
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("\nError: " + e.getMessage());
                                        // stay in the loop even if there was an error
                                        continue;
                                    }
                                }
                                break;

                            case 2:
                                // password change
                                Stream.of("Please enter the new password: ").forEach(System.out::println);
                                String newPassword = (System.console().readLine());
                                // set the new password
                                currentUserAdmin.setPassword(newPassword);
                                // give the user feedback that the password was changed
                                Stream.of("\nPassword successfully changed!").forEach(System.out::println);
                                break;

                            case 3:
                                // fullname change
                                Stream.of("Please enter the new fullname: ").forEach(System.out::println);
                                String newFullname = (System.console().readLine());
                                // set the new fullname
                                currentUserAdmin.setFullname(newFullname);
                                // give the user feedback that the fullname was changed
                                Stream.of("\nFullname successfully changed!").forEach(System.out::println);
                                break;

                            case 4:
                                // email change
                                Stream.of("Please enter the new email: ").forEach(System.out::println);
                                String newEmail = (System.console().readLine());
                                // set the new email
                                currentUserAdmin.setEmail(newEmail);
                                // give the user feedback that the email was changed
                                Stream.of("\nEmail successfully changed!").forEach(System.out::println);
                                break;

                            case 5:
                                // phone change
                                while (true) {
                                    // wrapping this in a try/catch block because we are parsing an int and it could
                                    // throw an exception and thats bad :(
                                    try {
                                        // phone change
                                        Stream.of("Please enter the new phone number (e.g 628112912, without +): ")
                                                .forEach(System.out::println);
                                        Long newPhoneNumber = Long.parseLong(System.console().readLine());
                                        // set the new phone
                                        currentUserAdmin.setPhone(newPhoneNumber);
                                        Stream.of("\nPhone number successfully changed!\n")
                                                .forEach(System.out::println);
                                        break;

                                    } catch (Exception e) {
                                        Stream.of("\nError : " + e.getMessage() + "\n").forEach(System.out::println);
                                        continue;
                                    }
                                }

                            case 6:
                                // cancel
                                break;

                            default:
                                // if the user enters an invalid choice
                                Stream.of("\nDetail change error; Invalid choice").forEach(System.out::println);
                                break;
                        }
                        break;

                    case 3:
                        // Submitting a request for assistance
                        // try/catch while loop since these are parseInts and its scary typing for these
                        // when testing
                        while (true) {
                            try {
                                // breadcrumbs
                                Stream.of("\n\n~/SchoolHELP Console > Admin Menu > Adding a new Request")
                                        .forEach(System.out::println);
                                // ask if the user wants to do a Tutorial Request or a Resource Request
                                Stream.of("--Adding a new request--",
                                        "Is this a tutorial request or a resource request?")
                                        .forEach(System.out::println);
                                Stream.of("1. Tutorial Request", "2. Resource Request", "3. Back")
                                        .forEach(System.out::println);
                                Stream.of("Please enter your choice: ").forEach(System.out::println);
                                int requestTypeChoice = Integer.parseInt(System.console().readLine());

                                while (true) {
                                    switch (requestTypeChoice) {
                                        case 1:
                                            try {
                                                // breadcrumbs
                                                Stream.of(
                                                        "\n\n~/SchoolHELP Console > Admin Menu > Adding a new Request > Tutorial Request")
                                                        .forEach(System.out::println);
                                                // description of the tutorial request
                                                Stream.of(
                                                        "\n--New Tutorial Request--",
                                                        "Please enter the description of the tutorial request (1/5): ")
                                                        .forEach(System.out::println);
                                                String tutorialDescription = (System.console().readLine());
                                                // proposed date of the tutorial request
                                                Stream.of(
                                                        "\nPlease enter the proposed date of the tutorial request (YYYYMMDD) (2/5): ")
                                                        .forEach(System.out::println);
                                                int proposedDate = Integer.parseInt(System.console().readLine());
                                                // validatee the proposed date
                                                boolean isDateValid = SchoolHELP.isValidDate(proposedDate);
                                                if (!isDateValid) {
                                                    Stream.of("\nDate not valid.", "Going back...")
                                                            .forEach(System.out::println);
                                                    break;
                                                }
                                                // proposed time of the tutorial request
                                                Stream.of(
                                                        "\nPlease enter the proposed time of the tutorial request (hhmm) (3/5): ")
                                                        .forEach(System.out::println);
                                                String proposedTimeString = System.console().readLine();
                                                // validate the proposed time
                                                boolean isTimeValid = SchoolHELP.isValidTime(proposedTimeString);
                                                if (!isTimeValid) {
                                                    Stream.of("\nTime not valid.", "Going back...")
                                                            .forEach(System.out::println);
                                                    break;
                                                }
                                                int proposedTime = Integer.parseInt(proposedTimeString);
                                                // student level
                                                Stream.of("\nPlease enter the student level (1-10) (4/5): ")
                                                        .forEach(System.out::println);
                                                int studentLevel = Integer.parseInt(System.console().readLine());
                                                if (studentLevel > 10) {
                                                    throw new Exception("Student level cannot be greater than 10");
                                                } else if (studentLevel < 1) {
                                                    throw new Exception("Student level cannot be less than 1");
                                                }
                                                // number of students expected
                                                Stream.of("\nPlease enter the number of students expected (5/5): ")
                                                        .forEach(System.out::println);
                                                int numberOfStudents = Integer.parseInt(System.console().readLine());

                                                // current time for the request
                                                LocalDateTime requestDate = LocalDateTime.now();

                                                // random requestID 6 digit number
                                                int requestID = (int) (Math.random() * 1000000);

                                                // request status is set to "NEW"
                                                String requestStatus = "NEW";

                                                // the school of this admin
                                                School school = currentUserAdmin.getSchool();
                                                try {
                                                    // passing the values above to the TutorialRequest constructor
                                                    TutorialRequest tutorialRequest = new TutorialRequest(requestID,
                                                            requestDate,
                                                            requestStatus,
                                                            tutorialDescription, proposedDate, proposedTime,
                                                            studentLevel,
                                                            numberOfStudents,
                                                            school);

                                                    currentUserAdmin.getSchool().addRequest(tutorialRequest);

                                                } catch (Exception e) {
                                                    System.out.println("\nError: " + e.getMessage());
                                                }

                                                // giving the user feedback that the request was successfully submitted
                                                Stream.of("\nRequest successfully submitted!\n")
                                                        .forEach(System.out::println);
                                            } catch (Exception e) {
                                                System.out.println("\nError: " + e.getMessage());
                                                continue;
                                            }
                                            break;

                                        case 2:
                                            try {
                                                // breadcrumbs
                                                Stream.of(
                                                        "\n\n~/SchoolHELP Console > Admin Menu > Adding a new Request > Resource Request")
                                                        .forEach(System.out::println);
                                                // description of the resource request
                                                Stream.of(
                                                        "--New Resource Request--",
                                                        "Please enter the description of the resource request (1/3): ")
                                                        .forEach(System.out::println);
                                                String resourceDescription = (System.console().readLine());
                                                // resource type
                                                Stream.of(
                                                        "\nPlease enter the resource type (mobile device/personal computer/networking equipment) (2/3):")
                                                        .forEach(System.out::println);
                                                String resourceType = (System.console().readLine());
                                                // number of resources expected
                                                Stream.of("\nPlease enter the number of resources expected (3/3): ")
                                                        .forEach(System.out::println);
                                                int numberOfResources = Integer.parseInt(System.console().readLine());

                                                // current time for the request
                                                LocalDateTime requestDateResource = LocalDateTime.now();

                                                // random requestID 6 digit number using math random
                                                int requestIDResource = (int) (Math.random() * 1000000);

                                                // request status is set to "NEW"
                                                String requestStatusResource = "NEW";

                                                // this school of this admin
                                                School school = currentUserAdmin.getSchool();

                                                try {
                                                    // passing the values above to the ResourceRequest constructor
                                                    ResourceRequest resourceRequest = new ResourceRequest(
                                                            requestIDResource,
                                                            requestDateResource,
                                                            requestStatusResource,
                                                            resourceDescription, resourceType, numberOfResources,
                                                            school);

                                                    currentUserAdmin.getSchool().addRequest(resourceRequest);

                                                } catch (Exception e) {
                                                    System.out.println("\nError: " + e.getMessage());
                                                }

                                                // giving the user feedback that the request was successfully submitted
                                                Stream.of("\nRequest successfully submitted!\n")
                                                        .forEach(System.out::println);
                                            } catch (Exception e) {
                                                System.out.println("\nError: " + e.getMessage());
                                                continue;
                                            }
                                            break;

                                        case 3:
                                            // cancel
                                            displayAdminMenu();
                                            break;

                                        default:
                                            // if the user enters an invalid choice
                                            Stream.of("Request error; Invalid choice").forEach(System.out::println);
                                            break;

                                    }

                                    break;
                                }

                            } catch (Exception e) {
                                System.out.println("\nTypeError: " + e.getMessage());
                                continue;
                            }
                        }

                    case 4:
                        // reviewing the offers for the school admins to change the statuses of them
                        while (true) {
                            try {
                                // TEST start //
                                // adding dummy data into the requests system
                                // this is for testing purposes only
                                // LocalDateTime NOW = LocalDateTime.now();
                                // // this school
                                // School school = currentUserAdmin.getSchool();
                                // Request request1 = new Request(12, NOW, "NEW", "testdesc", school);
                                // currentUserAdmin.getSchool().addRequest(request1);

                                // Volunteer volunteer1 = new Volunteer("guy", "guy", "guy", "guyemail", 123L,
                                // 312,
                                // "null");
                                // Offer offer1 = new Offer(221122, "yes", "PENDING", volunteer1);
                                // // add this offer to the request1
                                // request1.addOffer(offer1);
                                // TEST end //

                                // this prints out the list of requests for this current user's school
                                // and sorts it by status and date
                                System.out.println(
                                        "\n~/SchoolHELP Console > Admin Menu > Review Offers for submitted requests");
                                Stream.of("--Request Reviews--",
                                        "Now viewing the list of requests for school name: ("
                                                + currentUserAdmin.getSchool().getSchoolName() + ")")
                                        .forEach(System.out::println);
                                // show the list of offers for all the requests of this schoolname
                                // and sort them by status and date
                                if (currentUserAdmin.getSchool().getRequests().size() == 0) {
                                    Stream.of("\nThere are no requests for this school.").forEach(System.out::println);
                                    // press any key to go back to the admin menu
                                    Stream.of("\nPress any key to go back to the admin menu.")
                                            .forEach(System.out::println);
                                    System.console().readLine();
                                    displayAdminMenu();
                                } else {
                                    // divider
                                    System.out.println("-------");
                                    // sort the requests by status and date
                                    currentUserAdmin.getSchool().getRequests().sort(Comparator
                                            .comparing(Request::getRequestStatus)
                                            .thenComparing(Request::getRequestDate));
                                    // print out the list of request
                                    Stream.of(
                                            "ID     | Status | Request Date | Request Type | City        | Description ")
                                            .forEach(System.out::println);
                                    for (Request request : currentUserAdmin.getSchool().getRequests()) {
                                        System.out.println(request.getRequestID() + " | "
                                                + request.getRequestStatus() + "    | "
                                                + request.getRequestDate()
                                                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                                                + "   | "
                                                + request.getRequestType() + "     | "
                                                + request.getSchool().getCity() + "   | "
                                                + request.getRequestDescription());
                                    }
                                    System.out.println("-------");
                                }
                                // the user selects one of the shown requests by requestID
                                Stream.of(
                                        "\nPlease enter the request ID of the request you would like to review (1/5) (Type 0 to go back.): ")
                                        .forEach(System.out::println);
                                int requestID = Integer.parseInt(System.console().readLine());
                                // if the user enters 0, go back to the admin menu
                                if (requestID == 0) {
                                    System.out.println("Going back to the admin menu...");
                                    displayAdminMenu();
                                }
                                // find out if it exists first before proceeding
                                if (currentUserAdmin.getSchool().getRequests().stream()
                                        .anyMatch(request -> request.getRequestID() == requestID)) {

                                    // if the request has no offers
                                    if (currentUserAdmin.getSchool().getRequestByID(requestID).getOffers()
                                            .size() == 0) {
                                        Stream.of("\nThere are no offers for this request.")
                                                .forEach(System.out::println);
                                        // press any key to go back to the admin menu
                                        Stream.of("\nPress any key to go back.")
                                                .forEach(System.out::println);
                                        System.console().readLine();
                                        continue;
                                    } else {
                                        // THIS IS ONLY VALID IF THE REQUEST HAS OFFERS
                                        // going inside a loop because there might be multiple offers the user wants to
                                        // change the statuses of for this request
                                        while (true) {
                                            // print out the request details
                                            Stream.of(
                                                    "\n\n--Request Details of RequestID: "
                                                            + currentUserAdmin.getSchool().getRequestByID(requestID)
                                                                    .getRequestID()
                                                            + "--")
                                                    .forEach(System.out::println);
                                            System.out.println("'" + currentUserAdmin.getSchool()
                                                    .getRequestByID(requestID).getRequestDescription() + "''");

                                            // print out the offers for this request
                                            Stream.of("\n--Offers for Request ID of " + currentUserAdmin.getSchool()
                                                    .getRequestByID(requestID).getRequestID() + "--")
                                                    .forEach(System.out::println);
                                            // if there are no offers, this prints out a message to let the user know
                                            if (currentUserAdmin.getSchool().getRequestByID(requestID)
                                                    .getOffers() != null) {
                                                Stream.of(
                                                        "ID     | Status      | Offer Date   | Volunteer Name  | Remarks ")
                                                        .forEach(System.out::println);
                                                currentUserAdmin.getSchool().getRequestByID(requestID).getOffers()
                                                        .stream()
                                                        .sorted(Comparator.comparing(Offer::getOfferStatus))
                                                        .forEach(offer -> {
                                                            System.out.println(offer.getOfferID() + "   | "
                                                                    + offer.getOfferStatus() + "    | "
                                                                    + offer.getOfferDate()
                                                                            .format(DateTimeFormatter
                                                                                    .ofPattern("yyyy/MM/dd"))
                                                                    + "     | "
                                                                    + offer.getIsOwnedBy().getFullname() + "        | "
                                                                    + offer.getOfferRemarks());
                                                        });
                                            } else {
                                                Stream.of("There are no offers for this request.")
                                                        .forEach(System.out::println);
                                                // press any key to go back to the admin menu
                                                Stream.of("Press any key to go back to the admin menu.")
                                                        .forEach(System.out::println);
                                                System.console().readLine();
                                                displayAdminMenu();
                                            }

                                            // the user selects one of the shown offers by offerID
                                            Stream.of(
                                                    "\nPlease enter the offer ID of the offer you would like to review (2/5): ")
                                                    .forEach(System.out::println);
                                            int offerID = Integer.parseInt(System.console().readLine());

                                            // find out if it exists first before proceeding
                                            if (currentUserAdmin.getSchool().getRequestByID(requestID).getOffers()
                                                    .stream()
                                                    .anyMatch(offer -> offer.getOfferID() == offerID)) {

                                                // if it exists, find the offer object
                                                Offer offer = currentUserAdmin.getSchool().getRequestByID(requestID)
                                                        .getOffers().stream()
                                                        .filter(offer2 -> offer2.getOfferID() == offerID).findFirst()
                                                        .get();

                                                // print out the offer details
                                                Stream.of("\n--Offer Details--").forEach(System.out::println);
                                                System.out.println(offer);

                                                // the user selects the status of the offer
                                                Stream.of("Please select the status of the offer (3/5): ",
                                                        "1. ACCEPT", "2. REJECT")
                                                        .forEach(System.out::println);
                                                int offerStatusChoice = Integer.parseInt(System.console().readLine());
                                                String offerStatus = "";
                                                // just to make sure it will always be either of these two
                                                switch (offerStatusChoice) {
                                                    case 1:
                                                        offerStatus = "ACCEPTED";
                                                        // send an email to the school administrator and the volunteer
                                                        // owner
                                                        // of the offer
                                                        // to let them know that the offer has been accepted
                                                        // here ..
                                                        // update the offer status
                                                        offer.setOfferStatus(offerStatus);
                                                        // giving the user feedback that the status was successfully
                                                        // changed
                                                        Stream.of("\nStatus successfully changed!\n")
                                                                .forEach(System.out::println);
                                                        break;
                                                    case 2:
                                                        offerStatus = "REJECTED";
                                                        // update the offer status
                                                        offer.setOfferStatus(offerStatus);
                                                        // giving the user feedback that the status was successfully
                                                        // changed
                                                        Stream.of("\nStatus successfully changed!\n")
                                                                .forEach(System.out::println);
                                                        break;

                                                    default:
                                                        // offer status does not change here in this case
                                                        Stream.of("Request error; Invalid choice")
                                                                .forEach(System.out::println);
                                                        break;
                                                }
                                                // would you like to review another offer for this Request?
                                                Stream.of(
                                                        "Would you like to review another offer for this Request? (Y/N) (4/5)")
                                                        .forEach(System.out::println);
                                                String reviewAnotherOffer = System.console().readLine();
                                                if (reviewAnotherOffer.equalsIgnoreCase("Y")) {
                                                    // go back to the beginning of the loop
                                                    continue;
                                                } else {
                                                    // would you like to close the request?
                                                    Stream.of("Would you like to close the request? (Y/N) (5/5) ")
                                                            .forEach(System.out::println);
                                                    String closeRequest = System.console().readLine();
                                                    if (closeRequest.equalsIgnoreCase("Y")) {
                                                        // update the request status
                                                        currentUserAdmin.getSchool().getRequestByID(requestID)
                                                                .setRequestStatus("CLOSED");
                                                        // giving the user feedback that the status was successfully
                                                        // changed
                                                        Stream.of("\nRequest successfully closed!\n")
                                                                .forEach(System.out::println);
                                                        // go back to the admin menu
                                                        System.out.println("\nGoing back to the admin menu...");
                                                        displayAdminMenu();
                                                    } else {
                                                        // go back to the admin menu
                                                        System.out.println("\nGoing back to the admin menu...");
                                                        displayAdminMenu();
                                                    }
                                                }

                                            } else {
                                                // if the offerID does not exist
                                                Stream.of("Error: That Offer ID does not exist.")
                                                        .forEach(System.out::println);
                                                // press any key to go back to admin menu
                                                Stream.of("Press any key to go back to the admin menu.")
                                                        .forEach(System.out::println);
                                                System.console().readLine();
                                                displayAdminMenu();
                                            }
                                        }
                                    }

                                } else {
                                    // if the requestID does not exist
                                    Stream.of("Error: Request ID does not exist.").forEach(System.out::println);
                                    // press any key to go back to admin menu
                                    Stream.of("Press any key to go back to the admin menu.")
                                            .forEach(System.out::println);
                                    System.console().readLine();
                                    displayAdminMenu();
                                }

                                // // press any key to go back to admin menu
                                // Stream.of("Press any key to go back to the admin
                                // menu.").forEach(System.out::println);
                                // System.console().readLine();
                                // displayAdminMenu();

                            } catch (Exception e) {
                                System.out.println("\nTypeError: " + e.getMessage());
                                // press any key to go back to admin menu
                                Stream.of("Press any key to go back to the admin menu.")
                                        .forEach(System.out::println);
                                System.console().readLine();
                                displayAdminMenu();
                                break;
                            }
                        }

                    case 5:
                        // logout
                        Stream.of("\nLogging out...").forEach(System.out::println);
                        main(null);
                }

            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
                continue;
            }
        }

    }

    /**
     * {@summary} This method displays and handles all functionality for submitting
     * offers, takes no params, does not return anything.
     */
    private static void SubmitOffer() {
        // volunteer user now can select the request by its ID to view MORE details
        Stream.of("\nPlease enter the ID of the request you would like to view (Type 0 to go back.): ")
                .forEach(System.out::println);
        int requestID = Integer.parseInt(System.console().readLine());
        if (requestID == 0) {
            // go back to the volunteer menu
            System.out.println("\nGoing back to the volunteer menu...");
            displayVolunteerMenu();
        }

        // check if request exists
        if (SchoolHELP.isRequest(requestID)) {
            while (true) {
                try {
                    // breadcrumbs
                    Stream.of(
                            "\n\n~/SchoolHELP Console > Volunteer Login > Volunteer Menu > View Requests > View Request Details",
                            "-------");
                    // if request exists, display all the details of the request
                    Stream.of("Currently looking at request: ",
                            "ID     | Status | Request Date | School Name | City        | Description ")
                            .forEach(System.out::println);
                    Request request = SchoolHELP.getRequest(requestID);
                    System.out.println(request.getRequestID() + " | "
                            + request.getRequestStatus() + "    | "
                            + request.getRequestDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + "   | "
                            + request.getSchool().getSchoolName() + "        | "
                            + request.getSchool().getCity() + "| "
                            + request.getRequestDescription());
                    // divider
                    Stream.of("-------").forEach(System.out::println);
                    // choice to choose to submit offers for the request, or go back to the
                    // previous
                    // menu
                    Stream.of(
                            "--VOLUNTEER--",
                            "Please choose an option: \n1. Submit Offer for this request \n2. Back")
                            .forEach(System.out::println);
                    int choice2 = Integer.parseInt(System.console().readLine());
                    switch (choice2) {
                        case 1:
                            // breadcrumbs
                            System.out.println(
                                    "\n\n~/SchoolHELP Console > Volunteer Login > Volunteer Menu > View Requests > View Request Details > Submit Offer");
                            // submit offer for the request
                            Stream.of("--Submitting Offer for Request--",
                                    "Please enter some remaks for this offer: ")
                                    .forEach(System.out::println);
                            String offerRemarks = System.console().readLine();
                            // create the offer
                            LocalDateTime offerDate = LocalDateTime.now();
                            Offer offer = new Offer(offerDate, offerRemarks, "PENDING",
                                    (Volunteer) currentUser);

                            // add the offer to the request
                            SchoolHELP.getRequest(requestID).addOffer(offer);

                            // add the offer to the volunteer
                            ((Volunteer) currentUser).addOffer(offer);

                            // give the user feedback that the offer was successfully
                            // submitted
                            Stream.of("\nOffer successfully submitted!\n")
                                    .forEach(System.out::println);
                            // go back to the previous menu
                            System.out.println("\nGoing back to the previous menu...");
                            ViewRequests();
                        case 2:
                            // go back to the previous menu
                            System.out.println("\nGoing back to the previous menu...");
                            ViewRequests();
                    }
                } catch (Exception e) {
                    System.out.println("\nError: " + e.getMessage());
                    continue;
                }
            }

        } else {
            // if request does not exist
            Stream.of("\nError: Request ID does not exist.").forEach(System.out::println);
            // press any key to go back to the volunteer menu
            Stream.of("Press any key to go back to the volunteer menu.")
                    .forEach(System.out::println);
            System.console().readLine();
            displayVolunteerMenu();
        }
    }

    /**
     * {@summary} Shows all the requests that are currently in the system
     */
    private static void ViewRequests() {
        while (true) {
            try {
                // breadcrumbs
                System.out.println("\n\n~/SchoolHELP Console > Volunteer Login > Volunteer Menu > View Requests");
                System.out.println("---REQUESTS---");
                // View requests that have been submitted, ever.
                // basically, display all requests that exists in the system
                // if there are none, show the EMPTY message
                if (SchoolHELP.getAllRequests().isEmpty()) {
                    Stream.of("\nThere are no requests in the system.", "Press any key to go back.")
                            .forEach(System.out::println);
                    System.console().readLine();
                    displayVolunteerMenu();
                } else {
                    // if there are requests, displaying only the status, request date, description,
                    // school name and city.
                    // the user can select the request by its ID to view MORE details
                    // and also, only requests with status of NEW are displayed
                    Stream.of("ID     | Status | Request Date | School Name | City        | Description ")
                            .forEach(System.out::println);
                    for (Request request : SchoolHELP.getAllRequests()) {
                        if (request.getRequestStatus().equals("NEW")) {
                            System.out.println(request.getRequestID() + " | "
                                    + request.getRequestStatus() + "    | "
                                    + request.getRequestDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                                    + "   | "
                                    + request.getSchool().getSchoolName() + "        | "
                                    + request.getSchool().getCity() + "    | "
                                    + request.getRequestDescription());
                        }
                    }
                }
                System.out.println("--------------"); // these are just dividers to make the CLI look better

                // choice to choose to view requests by school, by city, or by request date
                Stream.of(
                        "--VOLUNTEER--",
                        "1. View requests by school", "2. View requests by city",
                        "3. View requests by request date", "4. Back", "Please enter your choice:")
                        .forEach(System.out::println);
                int choice = Integer.parseInt(System.console().readLine());
                switch (choice) {
                    case 1:
                        while (true) {
                            try {
                                // breadcrumbs
                                System.out.println(
                                        "\n\n~/SchoolHELP Console > Volunteer Login > Volunteer Menu > View Requests > View Requests by School");
                                // view requests by school
                                Stream.of("--Viewing Requests by School--",
                                        "Please enter the name of the school (case-sensitive): ")
                                        .forEach(System.out::println);
                                String schoolName = System.console().readLine();
                                // check if school exists
                                if (SchoolHELP.isSchool(schoolName)) { // to confirm wether its ACTUALLY a school or not
                                                                       // because
                                                                       // you can never trust the user
                                    // if school exists, check if it has any requests that are NEW
                                    if (SchoolHELP.getSchool(schoolName).anyNewRequests() == true) {
                                        System.out.println("\n-------");
                                        // if school has requests, display all the requests with status NEW
                                        Stream.of(
                                                "ID     | Status | Request Date | School Name | City        | Description ")
                                                .forEach(System.out::println);
                                        SchoolHELP.getSchool(schoolName).getRequests().forEach(request -> {
                                            if (request.getRequestStatus().equals("NEW")) {
                                                System.out.println(request.getRequestID() + " | "
                                                        + request.getRequestStatus() + "    | "
                                                        + request.getRequestDate().format(
                                                                DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                                                        + "   | "
                                                        + request.getSchool().getSchoolName() + "        | "
                                                        + request.getSchool().getCity() + "| "
                                                        + request.getRequestDescription());
                                            }
                                        });
                                        System.out.println("-------");
                                        // call the method to submit offers
                                        SubmitOffer();
                                    } else {
                                        // if school has no requests
                                        Stream.of("\nThere are no requests for this school.",
                                                "Press any key to go back.").forEach(System.out::println);
                                        System.console().readLine();
                                        // go back to ViewRequests() method
                                        ViewRequests();
                                        break;
                                    }
                                } else {
                                    // if school does not exist, display error message
                                    Stream.of("\nSchool does not exist", "Press any key to go back.")
                                            .forEach(System.out::println);
                                    System.console().readLine();
                                    // go back to ViewRequests() method
                                    ViewRequests();
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("\nError: " + e.getMessage());
                                continue;
                            }
                        }

                    case 2:
                        while (true) {
                            try {
                                // breadcrumbs
                                System.out.println(
                                        "\n\n~/SchoolHELP Console > Volunteer Login > Volunteer Menu > View Requests > View Requests by City");
                                // view requests by city
                                Stream.of("--Viewing Requests by City--",
                                        "Please enter the name of the city (case-sensitive): ")
                                        .forEach(System.out::println);
                                String cityName = System.console().readLine();
                                // check if city exists
                                if (SchoolHELP.isCity(cityName)) { // to confirm wether its ACTUALLY a city or not
                                                                   // because you
                                                                   // can
                                                                   // never trust the user
                                    // if city exists, check if there are any requests in the city
                                    if (SchoolHELP.getRequestsByCity(cityName).isEmpty() == false) {
                                        // then check if there are any requests in the city that are NEW
                                        System.out.println("\n-------");
                                        // printing out only the requests with city of cityName
                                        Stream.of(
                                                "ID     | Status | Request Date | School Name | City        | Description ")
                                                .forEach(System.out::println);
                                        SchoolHELP.getRequestsByCity(cityName).forEach(request -> {
                                            if (request.getRequestStatus().equals("NEW")) {
                                                System.out.println(request.getRequestID() + " | "
                                                        + request.getRequestStatus() + "    | "
                                                        + request.getRequestDate().format(
                                                                DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                                                        + "   | "
                                                        + request.getSchool().getSchoolName() + "        | "
                                                        + request.getSchool().getCity() + "     | "
                                                        + request.getRequestDescription());

                                            }
                                        });
                                        System.out.println("-------");
                                        // call the method to submit offers
                                        SubmitOffer();
                                    } else {
                                        // if there are no requests in the city
                                        Stream.of("\nThere are no requests for this city.",
                                                "Press any key to go back.").forEach(System.out::println);
                                        System.console().readLine();
                                        // go back to ViewRequests() method
                                        ViewRequests();
                                    }
                                } else {
                                    // if city does not exist, display error message
                                    Stream.of("\nCity does not exist.", "Press any key to go back.")
                                            .forEach(System.out::println);
                                    System.console().readLine();
                                    // go back to ViewRequests() method
                                    ViewRequests();
                                }
                            } catch (Exception e) {

                                System.out.println("\nError: " + e.getMessage());
                                continue;
                            }
                        }
                    case 3:
                        while (true) {
                            try {
                                // breadcrumbs
                                System.out.println(
                                        "\n\n~/SchoolHELP Console > Volunteer Login > Volunteer Menu > View Requests > View Requests by Request Date");
                                // view requests by request date
                                Stream.of("--Viewing Requests by Date--",
                                        "Please enter the request date (YYYYMMDD): ")
                                        .forEach(System.out::println);
                                int requestDate = Integer.parseInt(System.console().readLine());
                                // check if date valid date
                                if (SchoolHELP.isValidDate(requestDate)) { // to confirm wether its ACTUALLY a date or
                                                                           // not
                                                                           // because
                                                                           // you can
                                                                           // never trust the user
                                    // if date valid, check if there are any requests for that date
                                    if (SchoolHELP.getRequestsByDate(requestDate) != null) {

                                        System.out.println("\n-------");
                                        // if there are requests for that date, display only the RequestStatus of NEW,
                                        // description, school name and city
                                        Stream.of(
                                                "ID     | Status | Request Date | School Name | City        | Description ")
                                                .forEach(System.out::println);
                                        SchoolHELP.getRequestsByDate(requestDate).forEach(request -> {
                                            if (request.getRequestStatus().equals("NEW")) {
                                                System.out.println(request.getRequestID() + " | "
                                                        + request.getRequestStatus() + "    | "
                                                        + request.getRequestDate().format(
                                                                DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                                                        + "   | "
                                                        + request.getSchool().getSchoolName() + "        | "
                                                        + request.getSchool().getCity() + "    | "
                                                        + request.getRequestDescription());
                                            }
                                        });
                                        System.out.println("-------");
                                        // call the method to submit offers
                                        SubmitOffer();
                                    } else {
                                        // if there are no requests for that date, display error message
                                        Stream.of("\nThere are no requests for this date.")
                                                .forEach(System.out::println);
                                        // go back
                                        Stream.of("\nGoing back to Volunteer Menu...").forEach(System.out::println);
                                        ViewRequests();

                                    }
                                } else {
                                    // if date not valid, display error message
                                    Stream.of("\nDate not valid. Please use the YYYYMMDD format.")
                                            .forEach(System.out::println);
                                    // go back
                                    Stream.of("\nGoing back to Volunteer Menu...").forEach(System.out::println);
                                    ViewRequests();
                                }
                            } catch (Exception e) {
                                System.out.println("\nError: " + e.getMessage());
                                continue;
                            }
                        }

                    case 4:
                        // go back
                        Stream.of("\nGoing back to Volunteer Menu...").forEach(System.out::println);
                        displayVolunteerMenu();

                    default:
                        // if user enters invalid choice
                        Stream.of("\nInvalid choice. Please try again.").forEach(System.out::println);
                        continue;
                }

            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
                System.out.println("\nPress any key to go back.");
                System.console().readLine();
                ViewRequests();
                continue;
            }
        }

    }

    /**
     * {@summary} Displays the volunteer menu
     */
    private static void displayVolunteerMenu() {
        // at this point, the user is a volunteer, and is logged in.
        while (true) {
            try {
                // breadcrumbs
                System.out.println("\n\n~/SchoolHELP Console > Volunteer Login > Volunteer Menu");
                // to show the current user's name and occupation
                System.out.println("----INFO----");
                System.out.println("Logged in as: " + currentUser.getFullname() + ", position: ("
                        + ((Volunteer) currentUser).getOccupation() + ")");
                // show the volunteer only menu
                Stream.of("--VOLUNTEER--", "1. View Requests", "2. Back", "Please enter your choice:")
                        .forEach(System.out::println);
                int volunteerMenuChoice = Integer.parseInt(System.console().readLine());
                switch (volunteerMenuChoice) {
                    case 1:
                        ViewRequests();
                        break;
                    case 2:
                        // back
                        displayVolunteerAccountHandler();
                        break;
                    default:
                        // if the user enters an invalid choice
                        Stream.of("Request error; Invalid choice").forEach(System.out::println);
                        break;
                }
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
                continue;
            }
        }
    }

    /**
     * {@summary} Displays the volunteer login menu. Calls displayVolunteerMenu on
     * login success and goes back to displayVolunteerAccountHandler on login
     * failure.
     * 
     */
    public static void displayVolunteerLogin() {
        while (true) {
            try {
                // volunteer login
                Stream.of("\n--VOLUNTEER LOGIN (1/2)--", "Enter your username: ").forEach(System.out::println);
                String username = System.console().readLine();
                Stream.of("\n--VOLUNTEER LOGIN (2/2)--", "Enter your password: ").forEach(System.out::println);
                String password = System.console().readLine();
                // check if user is volunteer
                if (SchoolHELP.isUserVolunteer(username, password)) {
                    // set current user to the volunteer
                    currentUser = SchoolHELP.getUser(username, password);
                    // if user is volunteer, display volunteer menu
                    displayVolunteerMenu();
                } else {
                    // if user is not volunteer, display error message
                    System.out.println("\nInvalid username or password");
                    displayVolunteerAccountHandler();
                }
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
                continue;
            }
        }
    }

    // for the volunteer-specific cli menu
    /**
     * {@summary} Displays the volunteer account handler menu. Handles volunteer
     * account creation and login. Calls other methods. Can call
     * displayVolunteerAccountHandler and displayVolunteerLogin.
     */
    public static void displayVolunteerAccountHandler() {
        // Volunteer Menu
        while (true) {
            try {
                // breadcrumbs
                System.out.println("\n\n~/SchoolHELP Console > Volunteer Login");
                Stream.of("--VOLUNTEER MENU--", "1. Register As Volunteer", "2. Login", "3. Back",
                        "Please enter your choice:")
                        .forEach(System.out::println);
                int volunteerMenuChoice = Integer.parseInt(System.console().readLine());
                switch (volunteerMenuChoice) {
                    case 1:
                        // breadcrumbs
                        System.out.println("\n\n~/SchoolHELP Console > Volunteer Login > Register As Volunteer");
                        // Register As Volunteer
                        Stream.of("--Volunteer Account Creation--", "Please enter your username (1/7): ")
                                .forEach(System.out::println);
                        String username = System.console().readLine();
                        Stream.of("Please enter your password (2/7): ").forEach(System.out::println);
                        String password = System.console().readLine();
                        Stream.of("Please enter your fullname (3/7): ").forEach(System.out::println);
                        String fullname = System.console().readLine();
                        Stream.of("Please enter your email (4/7): ").forEach(System.out::println);
                        String email = System.console().readLine();
                        Stream.of("Please enter your phone number (e.g 628112912, without +) (5/7): ")
                                .forEach(System.out::println);
                        Long phoneNumber = Long.parseLong(System.console().readLine());
                        Stream.of("Please enter your occupation (6/7): ").forEach(System.out::println);
                        String occupation = System.console().readLine();
                        Stream.of("Please enter your date of birth (e.g YYYYMMDD) (7/7): ")
                                .forEach(System.out::println);
                        int dateOfBirth = Integer.parseInt(System.console().readLine());

                        // create volunteer object
                        Volunteer newVolunteer = new Volunteer(username, password, fullname, email, phoneNumber,
                                dateOfBirth,
                                occupation);

                        // add volunteer to the user list
                        SchoolHELP.addUser(newVolunteer);

                        // display success message
                        Stream.of("\nSuccessfully registered!").forEach(System.out::println);

                        // display volunteer menu
                        displayVolunteerAccountHandler();
                        break;

                    case 2:
                        // Login
                        displayVolunteerLogin();
                        break;
                    case 3:
                        // Logout
                        main(null);
                        break;
                }
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
                continue;
            }
        }

    }

    /**
     * {@summary} The main method of the program, this runs first before anything
     * else. This shows the first CLI menu.
     * 
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            // breadcrumbs
            System.out.println("\n\n~/SchoolHELP Console");
            // using lambda expressions and stream() to get user input
            Stream.of("--Welcome to the School Help System--", "What are you logging in as?", "1. Admin",
                    "2. Volunteer", "3. Exit",
                    "Please enter your choice: ")
                    .forEach(System.out::println);
            // await user input
            try {
                int choice = Integer.parseInt(System.console().readLine());
                // switch statement to handle user input
                switch (choice) {
                    case 1:
                        if (isFirstTimeLogin) {
                            Stream.of(
                                    "\n\nDetected first time login, default login is (admin, admin), it is advisable to change the default login to prevent unauthorized access.",
                                    "\n")
                                    .forEach(System.out::print);
                        }
                        // admin login
                        Stream.of("\n--ADMIN LOGIN (1/2)--", "Please enter your username: ")
                                .forEach(System.out::println);
                        String adminUsername = System.console().readLine();
                        Stream.of("\n--ADMIN LOGIN (2/2)--", "Please enter your password: ")
                                .forEach(System.out::println);
                        String adminPassword = System.console().readLine();
                        // check if user is admin
                        if (SchoolHELP.isUserAdmin(adminUsername, adminPassword)) {
                            // setting the current logged in user of this instance
                            currentUser = SchoolHELP.getUser(adminUsername, adminPassword);
                            // at this point, first time login is no more!
                            isFirstTimeLogin = false;
                            // if user is admin, display admin menu
                            displayAdminMenu();

                        } else {
                            // if user is not admin, display error message
                            System.out.println("\nInvalid username or password");
                        }

                        break;

                    case 2:
                        // volunteer login
                        displayVolunteerAccountHandler();
                        break;

                    case 3:
                        // exit program
                        System.exit(0);
                        break;

                    default:
                        // display error message
                        System.out.println("Invalid choice");
                        break;
                }
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
                main(null);
            }
        }
    }
}
