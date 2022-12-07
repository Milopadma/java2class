package src_2;

import javax.swing.*;
import java.awt.*;

public class GUIManager {
    // public class fields
    public static JFrame main_frame = new JFrame("Give me your data");

    public static JPanel name_age_panel = new JPanel();
    public static JLabel name_label = new JLabel("Name: ");
    public static JLabel age_label = new JLabel("Age: ");
    public static JTextField name_text_field = new JTextField(20); // readable text field
    public static JTextField age_text_field = new JTextField(20); // readable text field

    public static JPanel gender_select_panel = new JPanel();
    public static JLabel gender_label = new JLabel("Gender: ");
    public static ButtonGroup gender_select_group = new ButtonGroup();
    public static JRadioButton gender_male_radio = new JRadioButton("Male"); // readable radio button
    public static JRadioButton gender_female_radio = new JRadioButton("Female"); // readable radio button
    public static String this_gender = "";

    public static JPanel text_area_panel = new JPanel();
    public static JTextArea text_area_text = new JTextArea(10, 20); // readable text area

    public static JPanel buttons_panel = new JPanel();
    public static JButton submit_button = new JButton("Submit"); // action button
    public static JButton clear_button = new JButton("Clear"); // action button
    public static JButton cancel_button = new JButton("Cancel"); // action button

    public static JFrame output_frame = new JFrame("Output");
    public static JTextArea output_text = new JTextArea(10, 20); // readable text area
    public static PopupFactory output_popup = new PopupFactory();

    // class methods
    public static void setGender(String gender) {
        this_gender = gender;
    }

    public static String getGender() {
        return this_gender;
    }

    public static void main(String[] args) {
        // init all the things
        // this one inits the frame settings
        main_frame.setSize(800, 600);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // mainframe grid layout
        main_frame.setLayout(new GridLayout(
                4, // rows
                1, // columns
                8, // horizontal gap
                8 // vertical gap
        ));

        /*
         * the name_age_panel
         */
        // setting the panel layout using gridlayout
        // NAME_AGE_PANEL LAYOUT
        name_age_panel.setBackground(Color.GRAY);
        name_age_panel.setLayout(new GridLayout(
                2, // rows
                2, // columns
                8, // horizontal gap
                8 // vertical gap
        ));
        // adding the components above to the panel
        name_age_panel.add(name_label);
        name_age_panel.add(name_text_field);
        name_age_panel.add(age_label);
        name_age_panel.add(age_text_field);

        /*
         * the gender_select_panel
         */
        // setting the panel layout using gridLayout
        // GENDER_SELECT_PANEL LAYOUT
        gender_select_panel.setBackground(Color.GRAY);
        gender_select_panel.setLayout(new GridLayout(
                1, // rows
                3, // columns
                8, // horizontal gap
                8 // vertical gap
        ));
        // adding the components to the gender_select_panel
        gender_select_panel.add(gender_label);
        // adding the radio buttons to the buttongroup
        gender_select_group.add(gender_male_radio);
        gender_select_group.add(gender_female_radio);
        gender_male_radio.setActionCommand("male");
        gender_female_radio.setActionCommand("female");

        // adding event listeners to the radio buttons
        gender_male_radio.addActionListener(e -> {
            setGender("male");
        });

        gender_female_radio.addActionListener(e -> {
            setGender("female");
        });

        // adding the gender group to the panel
        gender_select_panel.add(gender_male_radio);
        gender_select_panel.add(gender_female_radio);

        /*
         * the text_area_panel
         */
        // setting the panel layout using gridLayout
        // TEXT_AREA_PANEL LAYOUT
        text_area_panel.setBackground(Color.GRAY);
        text_area_panel.setLayout(new GridLayout(
                1, // rows
                1, // columns
                8, // horizontal gap
                8 // vertical gap
        ));
        // adding the components to the text_area_panel
        text_area_panel.add(text_area_text);

        /*
         * the buttons_panel
         */
        // setting th epanel layout using gridLayout
        // BUTTONS_PANEL LAYOUT
        buttons_panel.setBackground(Color.GRAY);
        buttons_panel.setLayout(new GridLayout(
                1, // rows
                3, // columns
                8, // horizontal gap
                8 // vertical gap
        ));
        // adding event listeners to the buttons
        submit_button.addActionListener(e -> {
            submit_button_functionality();
        });
        clear_button.addActionListener(e -> {
            clear_button_functionality();
        });
        cancel_button.addActionListener(e -> {
            cancel_button_functionality();
        });

        // adding the components to the buttons_panel
        buttons_panel.add(submit_button);
        buttons_panel.add(clear_button);
        buttons_panel.add(cancel_button);

        // adding the panels created before to the main frame
        main_frame.add(name_age_panel);
        main_frame.add(gender_select_panel);
        main_frame.add(text_area_panel);
        main_frame.add(buttons_panel);

        // setting the frame to visible
        main_frame.setVisible(true);
    }

    /*
     * pop up window
     */

    // button functionalities
    public static void submit_button_functionality() {
        // getting the text from the text fields
        String name = name_text_field.getText();
        String age = age_text_field.getText();

        // getting the selected radio button
        String selected_gender = getGender();

        // show a pop up dialog box
        JOptionPane.showMessageDialog(
                main_frame,
                "Name: " + name + "\n" +
                        "Age: " + age + "\n" +
                        "Gender: " + selected_gender);
    }

    public static void clear_button_functionality() {
        // clearing the text fields
        name_text_field.setText("");
        age_text_field.setText("");
        text_area_text.setText("");
    }

    public static void cancel_button_functionality() {
        // closing the program
        System.exit(0);
    }

}
