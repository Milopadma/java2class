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
    MultifieldInputPanel(String[] fieldNames, String[] fieldValues, Runnable button_function) {
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

        // for the button
        JButton button = new JButton("Done");
        button.addActionListener(e -> MainView.saveCurrentPanel(this));
        button.addActionListener(e -> button_function.run());

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to center the field panel
        setLayout(new GridBagLayout());

        // to add the field panel to the parent panel
        add(field_panel, new GridBagConstraints());
    }

}
