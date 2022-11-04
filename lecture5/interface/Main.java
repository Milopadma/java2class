import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    // new Phone object

    Phone xiaomiRedMi10 = new Xiaomi();
    // Phone iPhone = new iPhone();

    // users
    PhoneUser user1 = new PhoneUser("user1", xiaomiRedMi10);
    // PhoneUser user2 = new PhoneUser("user2", iPhone);

    user1.turnOnThePhone();
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();

    while (true) {
      System.out.println("Phone Menu");
      System.out.println("1. Turn on");
      System.out.println("2. Turn off");
      System.out.println("3. Volume up");
      System.out.println("4. Volume down");
      System.out.println("5. Exit");

      System.out.println("Pick an option: ");
      input = scanner.next();

      if (input.equalsIgnoreCase("1")) {
        user1.turnOnThePhone();
      } else if (input.equalsIgnoreCase("2")) {
        user1.turnOffThePhone();
      } else if (input.equalsIgnoreCase("3")) {
        user1.volumeUp();
      } else if (input.equalsIgnoreCase("4")) {
        user1.volumeDown();
      } else if (input.equalsIgnoreCase("5")) {
        break;
      } else {
        System.out.println("Invalid input");
      }

      scanner.close();
    }

  }
}
