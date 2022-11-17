import java.time.LocalDateTime;
import java.util.stream.Stream;

// this whole class' purpose is to be a user interface
public class SchoolHELPConsole {
    // init a single instance of the SchoolHELP class
    private static SchoolHELP SchoolHELP = new SchoolHELP();
    // global var to keep track of who's currently logged in in this instance
    private static User currentUser = null;

    // console class methods
    // for the admin-specific cli menu
    public static void displayAdminMenu() {
        // this shall be a looop!!!!
        while (true) {
            System.out.println("\nWelcome to the SchoolHELP Admin Menu");
            Stream.of("1. Register a new school", "2. Edit profile",
                    "3. Submit a request for assistance",
                    "4. Review offers for requests", "5. Exit").forEach(System.out::println);
            Stream.of("Please enter your choice: ").forEach(System.out::println);
            // await user input, wrapped in try/catch to 'sanitize' user input
            try {
                int choice = Integer.parseInt(System.console().readLine());

                // switch statement to handle user input
                switch (choice) {
                    case 1:
                        // adding new school
                        // init a new instance of School class
                        School school = new School();
                        // school name
                        Stream.of("Please enter the school name: ").forEach(System.out::println);
                        school.setSchoolName(System.console().readLine());
                        // school address
                        Stream.of("Please enter the school address: ").forEach(System.out::println);
                        school.setAddress(System.console().readLine());
                        // get school email
                        Stream.of("Please enter the school city: ").forEach(System.out::println);
                        school.setCity(System.console().readLine());

                        // schoolID is randomly generated
                        school.setSchoolID((int) (Math.random() * 1000));

                        // Registering a new school admin
                        // school admin username
                        Stream.of("Please enter the school admin username: ").forEach(System.out::println);
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

                        // wrap this in a try/catch block to catch any exceptions when adding new
                        // SchoolAdmin and School instances
                        try {
                            // passing all the values above to the SchoolAdmin constructor
                            SchoolAdmin schoolAdmin = new SchoolAdmin(adminUsername, adminPassword, adminFullname,
                                    adminEmail,
                                    adminPhone,
                                    adminID, adminPosition);

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
                        // giving user feedback that this was successful
                        Stream.of("\nSchool added successfully!").forEach(System.out::println);
                        Stream.of("\nSchool admin successfully registered!").forEach(System.out::println);
                        break;

                    case 2:
                        // Editing the profile of the current user admin profile
                        // display their current profile details of the current user in this instance
                        Stream.of("Current profile details: ").forEach(System.out::println);
                        Stream.of(currentUser.toString()).forEach(System.out::println);
                        // asking the user which detail they want to change
                        Stream.of("Which detail would you like to change? ").forEach(System.out::println);
                        Stream.of("1. Username", "2. Password", "3. Fullname", "4. Email", "5. Phone")
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
                                            currentUser.setUsername(newUsername);
                                            // give the user feedback that the username was changed
                                            Stream.of("Username successfully changed!\n").forEach(System.out::println);
                                        }

                                        break;

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
                                currentUser.setPassword(newPassword);
                                break;

                            case 3:
                                // fullname change
                                Stream.of("Please enter the new fullname: ").forEach(System.out::println);
                                String newFullname = (System.console().readLine());
                                // set the new fullname
                                currentUser.setFullname(newFullname);
                                break;

                            case 4:
                                // email change
                                Stream.of("Please enter the new email: ").forEach(System.out::println);
                                String newEmail = (System.console().readLine());
                                // set the new email
                                currentUser.setEmail(newEmail);
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
                                        currentUser.setPhone(newPhoneNumber);
                                        Stream.of("Phone number successfully changed!\n").forEach(System.out::println);
                                        break;

                                    } catch (Exception e) {
                                        Stream.of("\nError : " + e.getMessage() + "\n").forEach(System.out::println);
                                        continue;
                                    }
                                }

                            default:
                                // if the user enters an invalid choice
                                Stream.of("Detail change error; Invalid choice").forEach(System.out::println);
                                break;
                        }
                        break;

                    case 3:
                        // Submitting a request for assistance
                        // description of the tutorial request
                        Stream.of("Please enter the description of the tutorial request: ")
                                .forEach(System.out::println);
                        String description = (System.console().readLine());
                        // proposed date of the tutorial request
                        Stream.of("Please enter the proposed date of the tutorial request: ")
                                .forEach(System.out::println);
                        int proposedDate = Integer.parseInt(System.console().readLine());
                        // proposed time of the tutorial request
                        Stream.of("Please enter the proposed time of the tutorial request: ")
                                .forEach(System.out::println);
                        int proposedTime = Integer.parseInt(System.console().readLine());
                        // student level
                        Stream.of("Please enter the student level: ").forEach(System.out::println);
                        int studentLevel = Integer.parseInt(System.console().readLine());
                        // number of students expected
                        Stream.of("Please enter the number of students expected: ").forEach(System.out::println);
                        int numberOfStudents = Integer.parseInt(System.console().readLine());

                        // current time for the request
                        LocalDateTime requestDate = LocalDateTime.now();

                        // random requestID
                        int requestID = (int) (Math.random() * 1000000);

                        // request status is set to "NEW"
                        String requestStatus = "NEW";

                        // passing the values above to the TutorialRequest constructor
                        TutorialRequest tutorialRequest = new TutorialRequest(requestID, requestDate, requestStatus,
                                description, proposedDate, proposedTime, studentLevel, numberOfStudents);
                }

            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
                continue;
            }
        }

    }

    // for the volunteer-specific cli menu
    public static void displayVolunteerMenu() {
    }

    public static void main(String[] args) {
        System.out.println("\nWelcome to the School Help System");
        // using lambda expressions and stream() to get user input
        Stream.of("1. Admin", "2. Volunteer", "3. Exit").forEach(System.out::println);
        Stream.of("Enter your choice: ").forEach(System.out::print);
        // await user input
        int choice = Integer.parseInt(System.console().readLine());

        // switch statement to handle user input
        switch (choice) {
            case 1:
                // admin login
                System.out.println("--ADMIN-- Enter your username: ");
                String adminUsername = System.console().readLine();
                System.out.println("--ADMIN-- Enter your password: ");
                String adminPassword = System.console().readLine();
                // check if user is admin
                if (SchoolHELP.isUserAdmin(adminUsername, adminPassword)) {
                    // setting the current logged in user of this instance
                    currentUser = SchoolHELP.getUser(adminUsername, adminPassword);

                    // if user is admin, display admin menu
                    displayAdminMenu();
                } else {
                    // if user is not admin, display error message
                    System.out.println("Invalid username or password");
                }
                break;

            case 2:
                // volunteer login
                System.out.println("--VOLUNTEER-- Enter your username: ");
                String username = System.console().readLine();
                System.out.println("--VOLUNTEER-- Enter your password: ");
                String password = System.console().readLine();
                // check if user is volunteer
                if (SchoolHELP.isUserVolunteer(username, password)) {
                    // if user is volunteer, display volunteer menu
                    displayVolunteerMenu();
                } else {
                    // if user is not volunteer, display error message
                    System.out.println("Invalid username or password");
                }
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
    }

}
