import java.time.LocalDateTime;
import java.util.stream.Stream;

// this whole class' purpose is to be a user interface
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
        School school = new School();
        while (true) {
            try {
                // school name
                Stream.of("\n--Registering a new School (1/2)--", "Please enter the school name: ")
                        .forEach(System.out::println);
                String schoolInput = System.console().readLine();
                if (SchoolHELP.getSchools().stream().anyMatch(s -> s.getSchoolName().equals(schoolInput))) {
                    Stream.of("\nSchool already exists, skipping to Registering a new SchoolAdmin.")
                            .forEach(System.out::println);
                    return school;
                } else {
                    school.setSchoolName(schoolInput);
                }
                // school address
                Stream.of("Please enter the school address: ").forEach(System.out::println);
                school.setAddress(System.console().readLine());
                // get school email
                Stream.of("Please enter the school city: ").forEach(System.out::println);
                school.setCity(System.console().readLine());

                // schoolID is randomly generated, even though this can be done in the class
                // constructor itself, i prefer to have it here
                school.setSchoolID((int) (Math.random() * 1000));

                // giving user feedback that this was successful
                Stream.of("\nSchool added successfully!").forEach(System.out::println);
                return school;

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
                        "Please enter the school admin username: ")
                        .forEach(System.out::println);
                String adminUsername = (System.console().readLine());
                // school admin password
                Stream.of("Please enter the school admin password: ").forEach(System.out::println);
                String adminPassword = (System.console().readLine());
                // school admin fullname
                Stream.of("Please enter the school admin fullname: ").forEach(System.out::println);
                String adminFullname = (System.console().readLine());
                // school admin email
                Stream.of("Please enter the school admin email: ").forEach(System.out::println);
                String adminEmail = (System.console().readLine());
                // school admin phone
                Stream.of("Please enter the school admin phone: ").forEach(System.out::println);
                int adminPhone = Integer.parseInt(System.console().readLine());
                // school admin ID
                Stream.of("Please enter the school admin ID: ").forEach(System.out::println);
                int adminID = Integer.parseInt(System.console().readLine());
                // school admin position
                Stream.of("Please enter the school admin position: ").forEach(System.out::println);
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
        // this shall be a looop!!!!
        while (true) {
            System.out.println("\n--Welcome to the SchoolHELP Admin Menu--");

            // to show the current user's name and position
            System.out.println("Logged in as: " + currentUser.getFullname() + ", position: ("
                    + ((SchoolAdmin) currentUser).getPosition() + ")");
            // to show the current school's name
            System.out.println("School ID: " + ((SchoolAdmin) currentUser).getSchool().getSchoolID() + ", School Name: "
                    + ((SchoolAdmin) currentUser).getSchool().getSchoolName());

            Stream.of("1. Register a new school", "2. Edit profile",
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
                                // call the method responsible for that wall of text
                                School school = displaySchoolCreationMenu();
                                // then, call the method responsible for the other wall of text that takes the
                                // school object because its necessary
                                SchoolAdmin schoolAdmin = displaySchoolAdminCreationMenu(school);

                                // adding the school admin to the users list in the SchoolHELP class
                                // as it counts as being a user, and all user instances are held in the
                                // SchoolHELP class
                                SchoolHELP.addUser(schoolAdmin);

                                // adding the newly created schoolAdmin to this school's instance
                                school.addSchoolAdmin(schoolAdmin);

                                // add the school to the school list in the SchoolHELP class,
                                // since SchoolHELP is the only class that is a static instance
                                SchoolHELP.addSchool(school);

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
                        // Editing the profile of the current user admin profile
                        // display their current profile details of the current user in this instance
                        Stream.of("Current profile details: ").forEach(System.out::println);
                        Stream.of(currentUserAdmin.toString()).forEach(System.out::println);
                        // asking the user which detail they want to change
                        Stream.of("Which detail would you like to change? ").forEach(System.out::println);
                        Stream.of("1. Username", "2. Password", "3. Fullname", "4. Email", "5. Phone", "6. Cancel")
                                .forEach(System.out::println);
                        Stream.of("Please enter your choice: ").forEach(System.out::println);
                        int detailChangeChoice = Integer.parseInt(System.console().readLine());
                        // switch statement to handle user input
                        switch (detailChangeChoice) {
                            case 1:
                                // this shall be a loooooop!!
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
                                        // we shall stay in the loop !!!!!!!!
                                        continue;
                                    }
                                }

                            case 2:
                                // password change
                                Stream.of("Please enter the new password: ").forEach(System.out::println);
                                String newPassword = (System.console().readLine());
                                // set the new password
                                currentUserAdmin.setPassword(newPassword);
                                break;

                            case 3:
                                // fullname change
                                Stream.of("Please enter the new fullname: ").forEach(System.out::println);
                                String newFullname = (System.console().readLine());
                                // set the new fullname
                                currentUserAdmin.setFullname(newFullname);
                                break;

                            case 4:
                                // email change
                                Stream.of("Please enter the new email: ").forEach(System.out::println);
                                String newEmail = (System.console().readLine());
                                // set the new email
                                currentUserAdmin.setEmail(newEmail);
                                break;

                            case 5:
                                // phone change
                                while (true) {
                                    // wrapping this in a try/catch block because we are parsing an int and it could
                                    // throw an exception and thats bad :(
                                    try {
                                        // phone change
                                        Stream.of("Please enter the new phone number: ").forEach(System.out::println);
                                        int newPhoneNumber = Integer.parseInt(System.console().readLine());
                                        // set the new phone
                                        currentUserAdmin.setPhone(newPhoneNumber);
                                        Stream.of("Phone number successfully changed!\n").forEach(System.out::println);
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
                                Stream.of("Detail change error; Invalid choice").forEach(System.out::println);
                                break;
                        }
                        break;

                    case 3:
                        // Submitting a request for assistance
                        // try/catch while loop since these are parseInts and its scary typing for these
                        // when testing
                        while (true) {
                            try {
                                // ask if the user wants to do a Tutorial Request or a Resource Request
                                Stream.of("\n--Adding a new request--",
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
                                                // description of the tutorial request
                                                Stream.of("Please enter the description of the tutorial request: ")
                                                        .forEach(System.out::println);
                                                String tutorialDescription = (System.console().readLine());
                                                // proposed date of the tutorial request
                                                Stream.of("Please enter the proposed date of the tutorial request: ")
                                                        .forEach(System.out::println);
                                                int proposedDate = Integer.parseInt(System.console().readLine());
                                                // proposed time of the tutorial request
                                                Stream.of("Please enter the proposed time of the tutorial request: ")
                                                        .forEach(System.out::println);
                                                int proposedTime = Integer.parseInt(System.console().readLine());
                                                // student level
                                                Stream.of("Please enter the student level: ")
                                                        .forEach(System.out::println);
                                                int studentLevel = Integer.parseInt(System.console().readLine());
                                                // number of students expected
                                                Stream.of("Please enter the number of students expected: ")
                                                        .forEach(System.out::println);
                                                int numberOfStudents = Integer.parseInt(System.console().readLine());

                                                // current time for the request
                                                LocalDateTime requestDate = LocalDateTime.now();

                                                // random requestID
                                                int requestID = (int) (Math.random() * 1000000);

                                                // request status is set to "NEW"
                                                String requestStatus = "NEW";
                                                try {
                                                    // passing the values above to the TutorialRequest constructor
                                                    TutorialRequest tutorialRequest = new TutorialRequest(requestID,
                                                            requestDate,
                                                            requestStatus,
                                                            tutorialDescription, proposedDate, proposedTime,
                                                            studentLevel,
                                                            numberOfStudents);

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
                                                // description of the resource request
                                                Stream.of("Please enter the description of the resource request: ")
                                                        .forEach(System.out::println);
                                                String resourceDescription = (System.console().readLine());
                                                // resource type
                                                Stream.of(
                                                        "Please enter the resource type: (mobile device/personal computer/networking equipment)")
                                                        .forEach(System.out::println);
                                                String resourceType = (System.console().readLine());
                                                // number of resources expected
                                                Stream.of("Please enter the number of resources expected: ")
                                                        .forEach(System.out::println);
                                                int numberOfResources = Integer.parseInt(System.console().readLine());

                                                // current time for the request
                                                LocalDateTime requestDateResource = LocalDateTime.now();

                                                // random requestID
                                                int requestIDResource = (int) (Math.random() * 1000000);

                                                // request status is set to "NEW"
                                                String requestStatusResource = "NEW";

                                                try {
                                                    // passing the values above to the ResourceRequest constructor
                                                    ResourceRequest resourceRequest = new ResourceRequest(
                                                            requestIDResource,
                                                            requestDateResource,
                                                            requestStatusResource,
                                                            resourceDescription, resourceType, numberOfResources);

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

    public static void displayVolunteerLogin() {
        while (true) {
            try {
                // volunteer login
                Stream.of("\n--VOLUNTEER--", "Enter your username: ").forEach(System.out::println);
                String username = System.console().readLine();
                Stream.of("\n--VOLUNTEER--", "Enter your password: ").forEach(System.out::println);
                String password = System.console().readLine();
                // check if user is volunteer
                if (SchoolHELP.isUserVolunteer(username, password)) {
                    // if user is volunteer, display volunteer menu
                    displayVolunteerMenu();
                } else {
                    // if user is not volunteer, display error message
                    System.out.println("Invalid username or password");
                }
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
                continue;
            }
        }
    }

    /**
     * {@summary} Shows all the requests that are currently in the system
     */
    private static void ViewRequests() {
        while (true) {
            try {
                // View requests that have been submitted, ever.
                // basically, display all requests that exists in the system
                SchoolHELP.getSchools().forEach(school -> {
                    school.getRequests().forEach(request -> {
                        System.out.println(request);
                    });
                });

                // choice to choose to view requests by school, by city, or by request date
                Stream.of(
                        "\n--VOLUNTEER--",
                        "Please choose an option: \n1. View requests by school \n2. View requests by city \n3. View requests by request date \n4. Cancel")
                        .forEach(System.out::println);
                int choice = Integer.parseInt(System.console().readLine());
                switch (choice) {
                    case 1:
                        // view requests by school
                        Stream.of("Please enter the name of the school (case-sensitive): ")
                                .forEach(System.out::println);
                        String schoolName = System.console().readLine();
                        // check if school exists
                        if (SchoolHELP.isSchool(schoolName)) { // to confirm wether its ACTUALLY a school or not because
                                                               // you can never trust the user
                            // if school exists, display all requests for that school
                            SchoolHELP.getSchool(schoolName).getRequests().forEach(request -> {
                                System.out.println(request);
                            });
                        } else {
                            // if school does not exist, display error message
                            Stream.of("School does not exist").forEach(System.out::println);
                        }
                        break;
                }

            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
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
                // show the volunteer only menu!!
                Stream.of("\n--VOLUNTEER--", "1. View Requests", "2. Back").forEach(System.out::println);
                int volunteerMenuChoice = Integer.parseInt(System.console().readLine());
                switch (volunteerMenuChoice) {
                    case 1:
                        ViewRequests();
                        break;
                    case 2:
                        // back
                        main(null);
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

    // for the volunteer-specific cli menu
    public static void displayVolunteerAccountHandler() {
        // Volunteer Menu
        while (true) {
            try {
                Stream.of("\n--VOLUNTEER MENU--", "1. Register As Volunteer", "2. Login", "3. Back")
                        .forEach(System.out::println);
                int volunteerMenuChoice = Integer.parseInt(System.console().readLine());
                switch (volunteerMenuChoice) {
                    case 1:
                        // Register As Volunteer
                        Stream.of("Please enter your username (1/7): ").forEach(System.out::println);
                        String username = System.console().readLine();
                        Stream.of("Please enter your password (2/7): ").forEach(System.out::println);
                        String password = System.console().readLine();
                        Stream.of("Please enter your fullname (3/7): ").forEach(System.out::println);
                        String fullname = System.console().readLine();
                        Stream.of("Please enter your email (4/7): ").forEach(System.out::println);
                        String email = System.console().readLine();
                        Stream.of("Please enter your phone number (5/7): ").forEach(System.out::println);
                        int phoneNumber = Integer.parseInt(System.console().readLine());
                        Stream.of("Please enter your occupation (6/7): ").forEach(System.out::println);
                        String occupation = System.console().readLine();
                        Stream.of("Please enter your date of birth (7/7): ").forEach(System.out::println);
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

    public static void main(String[] args) {
        while (true) {
            // using lambda expressions and stream() to get user input
            Stream.of("\n--Welcome to the School Help System--", "Please enter your choice: ", "1. Admin",
                    "2. Volunteer", "3. View All Users, in detail.", "4. View All Requests", "5. Exit")
                    .forEach(System.out::println);
            Stream.of("Enter your choice: ").forEach(System.out::print);
            // await user input
            try {
                int choice = Integer.parseInt(System.console().readLine());

                switch (choice) {
                    case 1:
                        if (isFirstTimeLogin) {
                            Stream.of(
                                    "Detected first time login, default login is (admin, admin), please change this after configuration.")
                                    .forEach(System.out::print);
                        }
                        // admin login
                        Stream.of("\n--ADMIN--", "Please enter your username: ").forEach(System.out::print);
                        String adminUsername = System.console().readLine();
                        Stream.of("\n--ADMIN--", "Please enter your password: ").forEach(System.out::print);
                        String adminPassword = System.console().readLine();
                        // check if user is admin
                        if (SchoolHELP.isUserAdmin(adminUsername, adminPassword)) {
                            // setting the current logged in user of this instance
                            currentUser = SchoolHELP.getUser(adminUsername, adminPassword);

                            // if user is admin, display admin menu
                            displayAdminMenu();

                            // at this point, first time login is no more!
                            isFirstTimeLogin = false;

                        } else {
                            // if user is not admin, display error message
                            System.out.println("Invalid username or password");
                        }

                        break;

                    case 2:

                        displayVolunteerAccountHandler();
                        break;

                    case 3:
                        // view all users
                        SchoolHELP.getUsers();
                        break;

                    case 4:
                        // view all requests
                        SchoolHELP.getAllRequests();
                        break;

                    case 5:
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

        // switch statement to handle user input
    }

}
