import java.util.stream.Stream;

// this whole class' purpose is to be a user interface
public class SchoolHELPConsole {
    // init a single instance of the SchoolHELP class
    private static SchoolHELP SchoolHELP = new SchoolHELP();

    // console class methods
    // for the admin-specific cli menu
    public static void displayAdminMenu() {
        System.out.println("\nWelcome to the SchoolHELP Admin Menu");
        Stream.of("1. Register a new school", "2. Register a new school admin", "3. Edit profile",
                "4. Submit a request for assistance",
                "5. Review offers for requests", "7. Exit").forEach(System.out::println);
        Stream.of("Please enter your choice: ").forEach(System.out::println);
        // await user input
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
                // school ID
                Stream.of("Please enter the school ID: ").forEach(System.out::println);
                school.setSchoolID(Integer.parseInt(System.console().readLine()));
                // school address
                Stream.of("Please enter the school address: ").forEach(System.out::println);
                school.setAddress(System.console().readLine());
                // get school email
                Stream.of("Please enter the school city: ").forEach(System.out::println);
                school.setCity(System.console().readLine());

                // add the school to the school list in the SchoolHELP class
                SchoolHELP.addSchool(school);
                break;
            case 2:
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

                // passing all the values above to the SchoolAdmin constructor
                SchoolAdmin schoolAdmin = new SchoolAdmin(adminUsername, adminPassword, adminFullname, adminEmail,
                        adminPhone,
                        adminID, "Admin");

                // adding the school admin to the users list in the SchoolHELP class
                SchoolHELP.addUser(schoolAdmin);
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
