package src;

import javax.swing.*;
import java.awt.*;

public class MainGUI {

    // public class fields for the GUI elements
    private static JTextField text_field_1;
    private static JTextField text_field_2;
    private static JTextPane output_text;
    private static JButton button_1;

    // class methods
    public static void updateText(String textparam) {
        output_text.setText(textparam);
    }

    // main GUI using java swing
    public static void main(String[] args) {
        // init framesa
        JFrame frame = new JFrame("My First GUI");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        // new fields
        text_field_1 = new JTextField(20);
        text_field_2 = new JTextField("Enter text here");
        output_text = new JTextPane();
        button_1 = new JButton("Click me");

        // adding the action listener to the button
        button_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateText(text_field_1.getText() + " " + text_field_2.getText());
            }
        });

        // adding the components to the frame
        frame.add(panel);
        frame.setLayout(new GridLayout(2, 1, 3, 3)); // set the layout
        panel.add(text_field_1);
        panel.add(text_field_2);
        panel.add(output_text);
        panel.add(button_1);
        panel.setLayout(new GridLayout(
                3, // rows
                1, // columns
                16, // horizontal gap
                16 // vertical gap
        )); // set the layout

        frame.setVisible(true);
    }
}