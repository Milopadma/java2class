import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SchoolHELPConsole {
    // creating a single instance of the SchoolHELP class
    
    private static SchoolHELP SchoolHELP = new SchoolHELP();
	private static Object isUserAdminOrVolunteer;

    // console class methods 
    // for the admin-specific cli menu
    public static void displayAdminMenu(){
        System.out.println("Welcome to the SchoolHELP Admin Menu");
        System.out.println("Please select an option from the menu below:");
        System.out.println("1. Add a new school");
        System.out.println("2. Add a new volunteer");
        System.out.println("3. Remove a school");
        System.out.println("4. Remove a volunteer");
        System.out.println("5. View all schools");
        System.out.println("6. View all volunteers");
        System.out.println("7. Exit");
    }

    // for the volunteer-specific cli menu
    public static void displayVolunteerMenu(){
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the School Help System");
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
