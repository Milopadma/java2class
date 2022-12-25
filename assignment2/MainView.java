// this class is responsible for the main view of the application GUI, all GUI-related classes are based on this class
// GUI elements import

import java.awt.*;
import javax.swing.*;

public class MainView {
    // class GUI elements
    static JFrame frame = new JFrame();

    public static JButton loginButton = new JButton("Login");

    // constructor
    public MainView() {
        // initialize the frame and GUI elements
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
    }

    // class methods
    // show LoginView
    public static void showLoginView() {
        LoginView loginView = new LoginView(frame, loginButton, "Login", "Username", "Password");
        frame.add(loginView);
        frame.setVisible(true);
    }

}
