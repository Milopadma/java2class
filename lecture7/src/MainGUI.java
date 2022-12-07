package src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainGUI {
    // main GUI using java swing
    public static void main(String[] args) {
        JFrame frame = new JFrame("My First GUI");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        // new fields
        JTextField field1 = new JTextField(20);
        JTextField field2 = new JTextField("Enter text here");
        // adding the components to the frame
        frame.add(panel);
        panel.add(field1);
        panel.add(field2);
        frame.setVisible(true);
    }
}