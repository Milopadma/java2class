import java.awt.*;
import java.util.stream.Stream;

import javax.swing.*;

// this class represents the generic panel with multiple buttons in it
public class MultifieldInputPanel extends JPanel {
    // class fields
    // todo

    // GUI element initializations
    JPanel field_panel = new JPanel();

    // class constructor
    MultifieldInputPanel(String[] fieldNames, String[] fieldValues, Runnable[] button_functions) {
        // field layout are vertically stacked with same width using BoxLayout
        field_panel.setLayout(new BoxLayout(field_panel, BoxLayout.Y_AXIS));

        // loop to create each field in fieldNames array and attach the functions
        // to the buttons ActionListener, using Stream() to iterate through the array
        // and attach the functions, since using a traditional for (i) loop doesnt work
        // here
        Stream.iterate(0, i -> i + 1).limit(fieldNames.length).forEach(i -> {
            JPanel text_field = new JPanel();
            text_field.setLayout(new BoxLayout(text_field, BoxLayout.Y_AXIS));
            text_field.add(new JLabel(fieldNames[i]));
            text_field.add(new JTextField(fieldValues[i], 10));
            field_panel.add(text_field);
        });

        // for the Next button
        JButton next_button = new JButton("Next");
        // also save the text_field values to the MainView class' schoolRegistration
        // string array
        next_button.addActionListener(e -> MainView.saveMultifieldTextFields(fieldNames, fieldValues));
        next_button.addActionListener(e -> button_functions[0].run());
        field_panel.add(next_button);

        // for the Back button
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> button_functions[1].run());
        field_panel.add(back_button);

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to center the field panel
        setLayout(new GridBagLayout());

        // to add the field panel to the parent panel
        add(field_panel, new GridBagConstraints());
    }

}
