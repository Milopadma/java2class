
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
public class SchoolHELPConsole {
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

// I GUSTI BAGUS MILO PADMA WIJAYA //E2000426
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * {@summary} This class manages instances of User and School classes, both in
 * ArrayLists.
 *
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class SchoolHELP {
    // class variables
    private static ArrayList<User> users = new ArrayList<User>();
    private static ArrayList<School> schools = new ArrayList<School>();

    // class constructor
    /**
     * 
     * SchoolHELP class constructor, does not take in any arguments. Instead just
     * creates the initial default users and schools.
     */
    public SchoolHELP() {
        // default ArrayList constructor with a single default school
        schools.add(new School("HELP", 1, "Jalan Damansara", "Kuala Lumpur"));
        // default ArrayList constructor with a single default SchoolAdmin user
        users.add(
                new SchoolAdmin("admin", "admin", "MILO PADMA", "defaultadmin@milopadma.com", (long) 628113344512L, 001,
                        "SchoolHELPAdmin", schools.get(0)));
    }

    // class setter and getter methods
    /**
     * This method returns the ArrayList of users.
     * 
     * @return
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * This method sets the ArrayList of users.
     * 
     * @param users
     */
    public void setUsers(ArrayList<User> users) {
        SchoolHELP.users = users;
    }

    // singular user
    /**
     * This method returns a single user from the ArrayList of users.
     * 
     * @param index
     * @return
     */
    public User getUser(String adminUsername, String adminPassword) {
        for (User user : users) {
            if (user.getUsername().equals(adminUsername)) {
                return user;
            }
        }
        return null;
    }

    /**
     * This method returns the ArrayList of schools.
     * 
     * @return ArrayList
     */
    public ArrayList<School> getSchools() {
        return schools;
    }

    /**
     * This method sets the ArrayList of schools.
     * 
     * @param schools
     */
    public void setSchools(ArrayList<School> schools) {
        SchoolHELP.schools = schools;
    }

    // class methods
    /**
     * This method adds a user to the ArrayList of users.
     * 
     * @param user
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * This method adds a school to the ArrayList of schools.
     * 
     * @param school
     */
    public void addSchool(School school) {
        schools.add(school);
    }

    /**
     * {@summary} A method of SchoolHELP, gets the school by school name string, and
     * returns the school object if it exists.
     * 
     * @param schoolName
     * @return
     */
    public School getSchool(String schoolName) {
        for (School school : schools) {
            if (school.getSchoolName().equals(schoolName)) {
                return school;
            }
        }
        return null;
    }

    /**
     * 
     * Removes a singular user from the ArrayList of users.
     * 
     * @param user
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * 
     * Removes a singular school from the ArrayList of schools.
     * 
     * @param school
     */
    public void removeSchool(School school) {
        schools.remove(school);
    }

    /**
     * {@summary} A method of SchoolHELP, gets all the requests of all the schools
     * in the Array List.
     * 
     * @return ArrayList of type Request
     */
    public ArrayList<Request> getAllRequests() {
        ArrayList<Request> allRequests = new ArrayList<Request>();
        for (School school : schools) {
            allRequests.addAll(school.getRequests());
        }
        return allRequests;

    }

    // functional class methods
    // to check if the user is an admin in the user list array or not
    /**
     * 
     * To check if the user is an admin in the user list array or not.
     * 
     * @param adminUsername
     * @param adminPassword
     * @return boolean
     */
    public boolean isUserAdmin(String adminUsername, String adminPassword) {
        // find this user in the users arraylist
        User user = users.stream()
                .filter(u -> u.getUsername().equals(adminUsername) && u.getPassword().equals(adminPassword)).findAny()
                .orElse(null);
        if (user == null) {
            return false;
        }
        // check if this user instance is a SchoolAdmin or not
        if (user.isSchoolAdmin()) {
            return true;
        } else {
            return false;
        }
    }

    // to check if the user is a volunteer in the user list array or not
    /**
     * 
     * To check if the user is a volunteer in the user list array or not.
     * 
     * @param volunteerUsername
     * @param volunteerPassword
     * @return boolean
     */
    public boolean isUserVolunteer(String volunteerUsername, String volunteerPassword) {
        // find this user in the users arraylist
        User user = users.stream()
                .filter(u -> u.getUsername().equals(volunteerUsername) && u.getPassword().equals(volunteerPassword))
                .findAny().orElse(null);
        if (user == null) {
            return false;
        } // check if this user instance is a Volunteer or not
        if (user.isVolunteer() == true) {
            return true;
        } else {
            return false;
        }
    }

    // to check if that username exists in the ArrayList already or not
    /**
     * 
     * To check if that username exists in the ArrayList already or not.
     * 
     * @param username
     * @return boolean
     */
    public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * To check if that school name exists in the ArrayList or not.
     * 
     * @param string
     * 
     * @return boolean
     */
    public boolean isSchool(String schoolName) {
        for (School school : schools) {
            if (school.getSchoolName().equals(schoolName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * To check if that city name exists in the ArrayList or not.
     * 
     * @param schoolName
     * @return boolean
     */
    public boolean isCity(String cityName) {
        for (School school : schools) {
            if (school.getCity().equals(cityName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@summary} A method of SchoolHELP, get the school by receiving cityName as a
     * param, then returns the schools in that city, if they exist.
     * 
     * @param cityName
     * @return ArrayList of type School
     */
    public ArrayList<Request> getRequestsByCity(String cityName) {
        for (School school : schools) {
            if (school.getCity().equals(cityName)) {
                return school.getRequests();
            }
        }
        return null;
    }

    /**
     * 
     * Takes in an int requestDate and checks if it is a valid date or not according
     * to the YYYYMMDD format. Returns true if it is valid, false if it is not.
     * 
     * @param requestDate
     * @return boolean
     */
    public boolean isValidDate(int requestDate) {
        // using the convention of YYYYMMDD
        // to validate whatever is requestDate
        // if it is a valid date, return true
        // if it is not a valid date, return false
        try {
            LocalDateTime.of(requestDate / 10000, (requestDate % 10000) / 100, requestDate % 100,
                    0,
                    0);
        } catch (DateTimeException e) {
            return false;
        }
        return true;

    }

    /**
     * {@summary} to return all the requests in the ArrayList of type Request,
     * that has the same date.
     * 
     * @param requestDate
     * @return ArrayList
     */
    public ArrayList<Request> getRequestsByDate(int requestDate) {
        // parsing the requestDate integer to a string
        String requestDateString = Integer.toString(requestDate);
        // then, loop through the schools arraylist, and get the requests of each school
        // and loop through the newly created requests arraylist, and check if the date
        ArrayList<Request> requestsByDate = new ArrayList<Request>();
        for (School school : schools) {
            for (Request request : school.getRequests()) {
                if (request.getRequestDateAsString().equals(requestDateString)) {
                    requestsByDate.add(request);
                }
            }
        }
        return requestsByDate;
    }

    /**
     * {@summary} Method of class SchoolHELP, to validate if the request is valid by
     * passing a type int requestID param.
     * 
     * @param requestID
     * @return
     */
    public boolean isRequest(int requestID) {
        for (Request request : getAllRequests()) {
            if (request.getRequestID() == requestID) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@summary} Method of class SchoolHELP, to return a singular request from the
     * ArrayList in School arrays. Traverses through the ArrayList of type School,
     * and then the ArrayList of type Request, and returns the request if it exists.
     * 
     * @param requestID
     * @return Request
     */
    public Request getRequest(int requestID) {
        for (School school : schools) {
            for (Request request : school.getRequests()) {
                if (request.getRequestID() == requestID) {
                    return request;
                }
            }
        }
        return null;
    }

    /**
     * {@summary} Method of class SchoolHELP, to validate the time passed in as an
     * int. Uses HHMM time format.
     * 
     * @param proposedTime
     * @return
     */
    public boolean isValidTime(String proposedTime) {
        // the basic time format HHMM,
        // where HH is the hour, and MM is the minute
        // HH must not be above 24, MM must not be above 59
        // if it is a valid time, return true
        // if it is not a valid time, return false
        try {
            int hour = Integer.parseInt(proposedTime.substring(0, 2));
            int minute = Integer.parseInt(proposedTime.substring(2, 4));
            if (hour > 24 || minute > 59) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }
}

// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.util.ArrayList;

// School class
/**
 * 
 * School class manages the data of School instances. Contains arraylists of
 * schoolAdmins instances and requests instances.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class School {
    // this class manages School data and also SchoolAdmin classes

    // class variables
    private String schoolName;
    private int schoolID;
    private String address;
    private String city;

    // each school has multiple SchoolAdmins
    private ArrayList<SchoolAdmin> schoolAdmins = new ArrayList<SchoolAdmin>();

    // each school has multiple Requests
    private ArrayList<Request> requests = new ArrayList<Request>();

    // class constructor
    /**
     * 
     * School class constructor. With arguments.
     * 
     * @param schoolName
     * @param schoolID
     * @param address
     * @param city
     */
    public School(String schoolName, int schoolID, String address, String city) {
        this.schoolName = schoolName;
        this.schoolID = schoolID;
        this.address = address;
        this.city = city;
    }

    // class constructor without args
    /**
     * 
     * School class constructor. Without arguments.
     */
    public School() {
        this.schoolName = "default school";
        this.schoolID = 000;
        this.address = "default address";
        this.city = "default city";
    }

    // class setter and getter methods
    /**
     * 
     * Method that returns the schoolName of this School class.
     * 
     * @return String
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 
     * Method that sets the schoolName of this School class.
     * 
     * @param schoolName
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * 
     * Method that returns the schoolID of this School class.
     * 
     * @return int
     */
    public int getSchoolID() {
        return schoolID;
    }

    /**
     * 
     * Method that sets the schoolID of this School class.
     * 
     * @param schoolID
     */
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    /**
     * 
     * Method that returns the address of this School class.
     * 
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * Method that sets the address of this School class.
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * Method that returns the city of this School class.
     * 
     * @return String
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * Method that sets the city of this School class.
     * 
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    // setter and getters for the ArrayLists
    /**
     * 
     * Method that returns the schoolAdmins of this School class.
     * 
     * @return ArrayList
     */
    public ArrayList<SchoolAdmin> getSchoolAdmins() {
        return schoolAdmins;
    }

    /**
     * 
     * Method that sets the schoolAdmins of this School class.
     * 
     * @param schoolAdmins
     */
    public void setSchoolAdmins(ArrayList<SchoolAdmin> schoolAdmins) {
        this.schoolAdmins = schoolAdmins;
    }

    /**
     * 
     * Method that returns the requests of this School class.
     * 
     * @return ArrayList
     */
    public ArrayList<Request> getRequests() {
        return requests;
    }

    /**
     * 
     * Method that sets the requests of this School class.
     * 
     * @param requests
     */
    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    // singular getRequestByID
    /**
     * 
     * Method that returns a singular Request instance from the requests arraylist,
     * using its requestID as a search parameter.
     * 
     * @param requestID
     * @return
     */
    public Request getRequestByID(int requestID) {
        for (Request request : requests) {
            if (request.getRequestID() == requestID) {
                return request;
            }
        }
        return null;
    }

    // add a SchoolAdmin to the school (singular)
    /**
     * 
     * Method that adds a SchoolAdmin instance to the schoolAdmins arraylist.
     * 
     * @param schoolAdmin
     */
    public void addSchoolAdmin(SchoolAdmin schoolAdmin) {
        schoolAdmins.add(schoolAdmin);
    }

    // add a Request to the school (singular)
    /**
     * 
     * Method that adds a Request to the request arraylist of this School class.
     * 
     * @param request
     */
    public void addRequest(Request request) {
        requests.add(request);
    }

    // custom class methods
    /**
     * {@summary} This method returns a boolean value depending on wether there are
     * any NEW requests in the school request array list or not.
     * 
     * @return boolean
     */
    public boolean anyNewRequests() {
        for (Request request : requests) {
            if (request.getRequestStatus().equals("NEW")) {
                return true;
            }
        }
        return false;
    }

    // toString method
    /**
     * 
     * Method that returns a String representation of this School class.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "School | [School address: " + address + ", city: " + city + ", school ID: " + schoolID
                + ", school name: "
                + schoolName + "]";
    }

}

// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Offer class that manages data of Offers, sends for the request class, and is
 * created by the Volunteer class.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class Offer {
    // class variables
    private LocalDateTime offerDate;
    private String remarks;
    private String offerStatus;

    // not included in the assignment but needed for offer-specific selection later
    // down the line
    private int offerID;

    // each offer instance is owned by a volunteer instance
    private Volunteer isOwnedBy;

    // class constructor
    /**
     * {@summary} Offer class constructor, takes in all the required fields.
     * 
     * @param offerDate   LocalDateTime
     * @param remarks     String
     * @param offerStatus String
     * @param isOwnedBy   Volunteer
     */
    public Offer(LocalDateTime offerDate, String remarks, String offerStatus, Volunteer isOwnedBy) {
        this.offerDate = offerDate;
        this.remarks = remarks;
        this.offerStatus = offerStatus;
        this.isOwnedBy = isOwnedBy;
        this.offerID = (int) (Math.random() * 9000) + 1000;
    }

    // class setter and getter methods
    /**
     * {@summary} Getter method for offerDate. Returns the offerDate.
     * 
     * @return int
     */
    public LocalDateTime getOfferDate() {
        return offerDate;
    }

    /**
     * {@summary} Setter method for offerDate. Takes in an int.
     * 
     * @param offerDate
     */
    public void setOfferDate(LocalDateTime offerDate) {
        this.offerDate = offerDate;
    }

    /**
     * {@summary} Getter method for remarks. Returns a String.
     * 
     * @return String
     */
    public String getOfferRemarks() {
        return remarks;
    }

    /**
     * {@summary} Setter method for remarks. Takes in a String.
     * 
     * @param remarks
     */
    public void setOfferRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * {@summary} Getter method for offerStatus. Returns a String.
     * 
     * @return String
     */
    public String getOfferStatus() {
        return offerStatus;
    }

    /**
     * {@summary} Setter method for offerStatus. Takes in a String.
     * 
     * @param offerStatus
     */
    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }

    /**
     * {@summary} Getter method for offerID. Returns a Volunteer object instance.
     * 
     * @return Volunteer
     */
    public Volunteer getIsOwnedBy() {
        return isOwnedBy;
    }

    /**
     * {@summary} Setter method for isOwnedBy. Takes in a Volunteer object instance.
     * 
     * @param isOwnedBy
     */
    public void setIsOwnedBy(Volunteer isOwnedBy) {
        this.isOwnedBy = isOwnedBy;
    }

    /**
     * {@summary} Getter method for offerID. Returns an int.
     * 
     * @return int
     */
    public int getOfferID() {
        return offerID;
    }

    /**
     * {@summary} Setter method for offerID. Takes in an int.
     * 
     * @param offerID
     */
    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    // toString method
    /**
     * {@summary} toString method for Offer class. Returns a String.
     */
    @Override
    public String toString() {
        return "Offer | [Offer date submitted: " + offerDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + ", offer status: " + offerStatus
                + ", offer ID: " + offerID
                + ", submitted by " + isOwnedBy.getFullname()
                + ", remarks: '" + remarks
                + "']";
    }
}

// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class manages the data of request instances, and its children classes.
 * Contains an array list of offers as it has a 1-many relationship with the
 * Offer class.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class Request {
    // this class receives from the Offer class and each School class has multiple
    // Requests
    // class variables
    private int requestID;
    private LocalDateTime requestDate;
    private String requestStatus;
    private String description;

    // class Request has a 1-many relationship with the Offer class
    private ArrayList<Offer> offers = new ArrayList<Offer>();

    // class Request also only has a 1-1 relationship with School class
    private School school;

    // class constructor
    /**
     * This is the class constructor for the Request class.
     * 
     * @param requestID
     * @param requestDate
     * @param requestStatus
     * @param description
     * @param school
     */
    public Request(int requestID, LocalDateTime requestDate, String requestStatus, String description, School school) {
        this.requestID = requestIDvalidator(requestID);
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
        this.description = description;
        this.school = school;
    }

    /**
     * 
     * Method to validate the request ID.
     * 
     * @param requestIDtoValidate
     * @return int
     */
    private int requestIDvalidator(int requestIDtoValidate) {
        // to make sure that the requestID is a 6 digit number
        // if the requestID is not a 6 digit number, this will regenerate a new
        // number
        if (requestIDtoValidate < 100000 || requestIDtoValidate > 999999) {
            requestIDtoValidate = (int) (Math.random() * 900000) + 100000;
        }
        return requestIDtoValidate;
    }

    // class setter and getter methods
    /**
     * This method returns the request ID.
     * 
     * @return
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * This method sets the request ID.
     * 
     * @param requestID
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    /**
     * This method returns the request date.
     * 
     * @return
     */
    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    /**
     * This method returns the request date as a string, necessary in some cases
     * such as comparisons.
     * 
     * @return String
     * @param requestDate
     */
    public String getRequestDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return requestDate.format(formatter);
    }

    /**
     * This method sets the request date. Takes in a LocalDateTime param.
     * 
     * @param requestDate
     */
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * This method returns the request status.
     * 
     * @return String
     */
    public String getRequestStatus() {
        return requestStatus;
    }

    /**
     * This method sets the request status.
     * 
     * @param requestStatus
     */
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    /**
     * This method returns the request description.
     * 
     * @return String
     */
    public String getRequestDescription() {
        return description;
    }

    /**
     * This method sets the request description.
     * 
     * @param description
     */
    public void setRequestDescription(String description) {
        this.description = description;
    }

    /**
     * This method returns the offers of this request..
     * 
     * @return ArrayList
     */
    public ArrayList<Offer> getOffers() {
        return offers;
    }

    /**
     * This method sets the offers of this request.
     * 
     * @param offers
     */
    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    /**
     * This method adds an offer to the offers array list.
     * 
     * @param offer
     */
    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    /**
     * This method returns the school of this request.
     * 
     * @return School
     */
    public School getSchool() {
        return school;
    }

    /**
     * This method sets the school of this request.
     * 
     * @param school
     */
    public void setSchool(School school) {
        this.school = school;
    }

    // toString method
    /**
     * This method returns the request ID, request date, request status, and
     * description as a string.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Request | [Request description: " + description + ", request date: "
                + requestDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ", request ID: "
                + requestID
                + ", request status:  " + requestStatus + ", of school: " + school + "]";
    }

    /**
     * {@summary} Method of Request class, returns either "Resource" or "Tutorial"
     * request depending on the instance of the class.
     * 
     * @return String "Resource" or "Tutorial"
     */
    public String getRequestType() {

        if (this instanceof ResourceRequest) {
            return "Resource";
        } else if (this instanceof TutorialRequest) {
            return "Tutorial";
        } else {
            return "Unknown";
        }

    }
}
// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
/**
 * 
 * SchoolAdmin class manages the data of SchoolAdmin instances. Always bind to
 * an instance of school through a many-to-one relationship. Child class of
 * User.
 * 
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class SchoolAdmin extends User {
    // this class manages SchoolAdmin data
    // class variables
    private int staffID;
    private String position;

    // a many-to-one relationship with Schoo, since each school has many
    // schooladmins
    private School thisSchool;

    // class constructor
    /**
     * 
     * SchoolAdmin class constructor. With arguments.
     * 
     * @param username
     * @param password
     * @param fullname
     * @param email
     * @param phone
     * @param staffID
     * @param position
     * @param school
     */
    public SchoolAdmin(String username, String password, String fullname, String email, Long phone, int staffID,
            String position, School school) {
        super(username, password, fullname, email, phone);
        this.staffID = staffID;
        this.position = position;
        this.thisSchool = school;
    }

    // class setter and getter methods
    /**
     * 
     * Method that returns the staffID of this SchoolAdmin class.
     * 
     * @return int
     */
    public int getStaffID() {
        return staffID;
    }

    /**
     * 
     * Method that sets the staffID of this SchoolAdmin class.
     * 
     * @param staffID
     */
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    /**
     * 
     * Method that returns the position of this SchoolAdmin class.
     * 
     * @return String
     */
    public String getPosition() {
        return position;
    }

    /**
     * 
     * Method that sets the position of this SchoolAdmin class.
     * 
     * @param position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 
     * Method that returns the thisSchool of this SchoolAdmin class.
     * 
     * @return School
     */
    public School getSchool() {
        return thisSchool;
    }

    /**
     * 
     * Method that sets the thisSchool of this SchoolAdmin class.
     * 
     * @param school
     */
    public void setSchool(School thisSchool) {
        this.thisSchool = thisSchool;
    }

    // toString method
    /**
     * 
     * Method that returns the string representation of this SchoolAdmin class.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "School Admin | [Staff ID: " + staffID + ", position: " + position + "], " + super.toString();
    }
}
// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426

/**
 * 
 * User class is the parent class of Volunteer and SchoolAdmin classes. Manages
 * the data of User instances.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class User {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private Long phone;

    // User class constructor
    /**
     * 
     * User class constructor
     * 
     * @param username
     * @param password
     * @param fullname
     * @param email
     * @param phone
     */
    public User(String username, String password, String fullname, String email, Long phone) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    // class setter and getter methods
    /**
     * 
     * Method that returns the username of this User class.
     * 
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * Method that sets the username of this User class.
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * Method that returns the password of this User class.
     * 
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * Method that sets the password of this User class.
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * Method that returns the fullname of this User class.
     * 
     * @return String
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * 
     * Method that sets the fullname of this User class.
     * 
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * 
     * Method that returns the email of this User class.
     * 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * Method that sets the email of this User class.
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * Method that returns the phone number of this User class.
     * 
     * @return Long
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * 
     * Method that sets the phone number of this User class.
     * 
     * @param phone
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    // toString method
    /**
     * Method that returns the String representation of this User class.
     */
    public String toString() {
        return "User | [Username: " + username + ", password: " + password + ", fullname: " + fullname + ", email: "
                + email
                + ", phone number: " + phone + "]";
    }

    // each user can be a SchoolAdmin or a Volunteer
    // to check if this user is a school admin or not
    /**
     * 
     * Method that checks if the instance of User this is getting called on is a
     * SchoolAdmin instance or not.
     * 
     * @return boolean
     */
    public boolean isSchoolAdmin() {
        if (this instanceof SchoolAdmin) { // basically checks if 'this' is an instance of SchoolAdmin
            return true;
        }
        return false;
    }

    // to check if this user is a volunteer or not
    /**
     * 
     * Method that checks if the instance of User this is getting called on is a
     * Volunteer instance or not.
     * 
     * @return boolean
     */
    public boolean isVolunteer() {
        if (this instanceof Volunteer) { // basically checks if 'this' is an instance of Volunteer
            return true;
        }
        return false;
    }
}

// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.util.ArrayList;

/**
 * 
 * Volunteer class manages the data of Volunteer instances. Contains arraylists
 * of offers that this Volunteer instance has made.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class Volunteer extends User {
    // this class manages Volunteer data
    // class variables
    private int dateOfBirth;
    private String occupation;

    // each volunteer can have multiple offers
    private ArrayList<Offer> offers = new ArrayList<Offer>();

    // class constructor
    /**
     * 
     * Volunteer class constructor. With arguments.
     * 
     * @param username
     * @param password
     * @param fullname
     * @param email
     * @param phone
     * @param dateOfBirth
     * @param occupation
     */
    public Volunteer(String username, String password, String fullname, String email, Long phone, int dateOfBirth,
            String occupation) {
        super(username, password, fullname, email, phone);
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
    }

    // class setter and getter methods
    /**
     * 
     * Method that returns the dateOfBirth of this Volunteer class.
     * 
     * @return int
     */
    public int getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * 
     * Method that sets the dateOfBirth of this Volunteer class.
     * 
     * @param dateOfBirth
     */
    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * 
     * Method that returns the occupation of this Volunteer class.
     * 
     * @return String
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * 
     * Method that sets the occupation of this Volunteer class.
     * 
     * @param occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * 
     * Method that returns the offers of this Volunteer class.
     * 
     * @return ArrayList<Offer>
     */
    public ArrayList<Offer> getOffers() {
        return offers;
    }

    /**
     * 
     * Method that sets the offers of this Volunteer class.
     * 
     * @param offers
     */
    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    /**
     * 
     * Method that adds an offer to the offers arraylist of this Volunteer class.
     * 
     * @param offer
     */
    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    // toString method
    /**
     * 
     * Method that returns the string representation of this Volunteer class.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Volunteer | [Date of birth: " + dateOfBirth + ", occupation: " + occupation + "]";
    }
}

// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class manages the data of tutorialRequest instances. Child of Request
 * class.
 * 
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class TutorialRequest extends Request {
    // this class handles all things about the tutorial request and its data

    // class variables
    private int proposedDate;
    private int proposedTime;
    private int studentLevel;
    private int numStudents;

    // each tutorial request has a 1-1 relationship with a School class
    private School school;

    // to show the request status
    private String requestStatus;

    // to show the request date
    private LocalDateTime requestDate;

    // to show this request ID
    private int requestID;

    // class constructor
    /**
     * 
     * The class contructor for the TutorialRequest class.
     * 
     * @param requestID
     * @param requestDate
     * @param requestStatus
     * @param description
     * @param proposedDate
     * @param proposedTime
     * @param studentLevel
     * @param numStudents
     * @param school
     */
    public TutorialRequest(int requestID, LocalDateTime requestDate, String requestStatus, String description,
            int proposedDate,
            int proposedTime, int studentLevel, int numStudents, School school) {
        super(requestID, requestDate, requestStatus, description, school);
        this.proposedDate = proposedDate;
        this.proposedTime = proposedTime;
        this.studentLevel = studentLevel;
        this.numStudents = numStudents;
        this.requestStatus = requestStatus;
        this.school = school;
        this.requestDate = requestDate;
        this.requestID = requestIDvalidator(requestID);
    }

    /**
     * 
     * Method to validate the request ID.
     * 
     * @param requestIDtoValidate
     * @return int
     */
    private int requestIDvalidator(int requestIDtoValidate) {
        // to make sure that the requestID is a 6 digit number
        // if the requestID is not a 6 digit number, this will regenerate a new
        // number
        if (requestIDtoValidate < 100000 || requestIDtoValidate > 999999) {
            requestIDtoValidate = (int) (Math.random() * 900000) + 100000;
        }
        return requestIDtoValidate;
    }

    // class setter and getter methods
    /**
     * This method returns the proposed date.
     * 
     * @return int
     */
    public int getProposedDate() {
        return proposedDate;
    }

    /**
     * This method sets the proposed date.
     * 
     * @param proposedDate
     */
    public void setProposedDate(int proposedDate) {
        this.proposedDate = proposedDate;
    }

    /**
     * This method returns the proposed time.
     * 
     * @return int
     */
    public int getProposedTime() {
        return proposedTime;
    }

    /**
     * This method sets the proposed time.
     * 
     * @param proposedTime
     */
    public void setProposedTime(int proposedTime) {
        this.proposedTime = proposedTime;
    }

    /**
     * This method returns the student level.
     * 
     * @return int
     */
    public int getStudentLevel() {
        return studentLevel;
    }

    /**
     * This method sets the student level.
     * 
     * @param studentLevel
     */
    public void setStudentLevel(int studentLevel) {
        this.studentLevel = studentLevel;
    }

    /**
     * This method returns the number of students.
     * 
     * @return int
     */
    public int getNumStudents() {
        return numStudents;
    }

    /**
     * This method sets the number of students.
     * 
     * @param numStudents
     */
    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    /**
     * This method returns the school.
     * 
     * @return School
     */
    public School getSchool() {
        return school;
    }

    /**
     * This method sets the school.
     * 
     * @param school
     */
    public void setSchool(School school) {
        this.school = school;
    }

    /**
     * This method sets the request status.
     * 
     * @param String
     */
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    /**
     * This method returns the request status.
     * 
     * @return String
     */
    public String getRequestStatus() {
        return requestStatus;
    }

    /**
     * This method returns the request date.
     * 
     * @return LocalDateTime
     */
    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    /**
     * This method returns the request date as a string.
     * 
     * @param String
     */
    public String getRequestDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return requestDate.format(formatter);
    }

    /**
     * This method sets the request date.
     * 
     * @param LocalDateTime
     */
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * This method returns the request ID.
     * 
     * @return int
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * This method sets the request ID.
     * 
     * @param int
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    // toString method
    /**
     * This method returns the string representation of the TutorialRequest class.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Tutorial Request | [Request Status: " + requestStatus
                + ", request date: " + requestDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + ", of school: " + school.getSchoolName()
                + ", request ID: " + requestID
                + ", number of students: " + numStudents
                + ", student level: " + studentLevel
                + ", proposed date: " + proposedDate
                + ", proposed time: " + proposedTime
                + "]";
    }
}

// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class manages the data of resourceRequest instances. Child of Request
 * class.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class ResourceRequest extends Request {
    // this class manages ResourceRequest data,
    // really does
    // class variables
    private String resourceType;
    private int numRequired;

    // each tutorial request has a 1-1 relationship with a School class
    private School school;

    // to show the request status
    private String requestStatus;

    // to show the request date
    private LocalDateTime requestDate;

    // to show this request ID
    private int requestID;

    // class constructor
    /**
     * 
     * The class contructor for the ResourceRequest class.
     * 
     * @param requestID
     * @param requestDate
     * @param requestStatus
     * @param description
     * @param resourceType
     * @param numRequired
     * @param school
     */
    public ResourceRequest(int requestID, LocalDateTime requestDate, String requestStatus, String description,
            String resourceType, int numRequired, School school) {
        super(requestID, requestDate, requestStatus, description, school);
        this.resourceType = resourceType;
        this.numRequired = numRequired;
        this.school = school;
        this.requestStatus = requestStatus;
        this.requestDate = requestDate;
        this.requestID = requestIDvalidator(requestID);
    }

    /**
     * 
     * Method to validate the request ID.
     * 
     * @param requestIDtoValidate
     * @return int
     */
    private int requestIDvalidator(int requestIDtoValidate) {
        // to make sure that the requestID is a 6 digit number
        // if the requestID is not a 6 digit number, this will regenerate a new
        // number
        if (requestIDtoValidate < 100000 || requestIDtoValidate > 999999) {
            requestIDtoValidate = (int) (Math.random() * 900000) + 100000;
        }
        return requestIDtoValidate;
    }

    // class setter and getter methods
    /**
     * This method returns the resource type.
     * 
     * @return String
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * This method sets the resource type.
     * 
     * @param resourceType
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * This method returns the number of resources required.
     * 
     * @return int
     */
    public int getNumRequired() {
        return numRequired;
    }

    /**
     * This method sets the number of resources required.
     * 
     * @param numRequired
     */
    public void setNumRequired(int numRequired) {
        this.numRequired = numRequired;
    }

    /**
     * This method returns the school.
     * 
     * @return School
     */
    public School getSchool() {
        return school;
    }

    /**
     * This method sets the school.
     * 
     * @param school
     */
    public void setSchool(School school) {
        this.school = school;
    }

    /**
     * This method sets the request status.
     * 
     * @param String
     */
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    /**
     * This method returns the request status.
     * 
     * @return String
     */
    public String getRequestStatus() {
        return requestStatus;
    }

    /**
     * This method sets the request date.
     * 
     * @param LocalDateTime
     */
    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    /**
     * This method returns the request date as a string.
     * 
     * @return String
     */
    public String getRequestDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return requestDate.format(formatter);
    }

    /**
     * This method sets the request date.
     * 
     * @param LocalDateTime
     */
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * This method returns the request ID.
     * 
     * @return int
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * This method sets the request ID.
     * 
     * @param int
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    // toString method
    /**
     * This method returns the string representation of the ResourceRequest class.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Resource Request | [Request Status: " + requestStatus
                + ", request date: " + requestDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + ", of school: " + school.getSchoolName()
                + ", request ID: " + requestID
                + ", number of resources required: " + numRequired
                + ", resource type: " + resourceType
                + "]";
    }
}
