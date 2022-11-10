public class SchoolHELPConsole {
    // creating a single instance of the SchoolHELP class
    
    private static SchoolHELP schoolHELP = new SchoolHELP();

    public static void main(String[] args) {
        System.out.println("Welcome to the School Help System");
        System.out.println("Please enter your name");
        String name = System.console().readLine();
        System.out.println("Please enter your age");
        int age = Integer.parseInt(System.console().readLine());
        System.out.println("Please enter your grade");
        int grade = Integer.parseInt(System.console().readLine());
        System.out.println("");
    }
}
