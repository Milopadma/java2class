import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SchoolHELPConsole {
    // creating a single instance of the SchoolHELP class
    
    private static SchoolHELP schoolHELP = new SchoolHELP();
	private static Object isUserAdminOrVolunteer;

    // console class methods 
    public static void displayAdminMenu(){
        System.out.println("
        1. Add a new user
        2. Delete a user
        3. Update a user
        4. View all users
        5. View all volunteers
        ");
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
                String username = System.console().readLine();
                System.out.println("--ADMIN-- Enter your password: ");
                String password = System.console().readLine();
                // check if user is admin
                if (schoolHELP.isUserAdmin(username, password)) {
                    // if user is admin, display admin menu
                    displayAdminMenu();
                } else {
                    // if user is not admin, display error message
                    System.out.println("Invalid username or password");
                }
                break;
            case 2:
                // volunteer login
                System.out.println("Enter your username: ");
                String username = System.console().readLine();
                System.out.println("Enter your password: ");
                String password = System.console().readLine();
                // check if user is volunteer
                if (schoolHELP.isUserVolunteer(username, password)) {
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
