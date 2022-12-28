import java.awt.*;
import java.util.HashMap;
import java.util.stream.Stream;

import javax.swing.*;

// this class represents the multifield input panel. 
public class MultifieldInputPanel extends JPanel {
    // GUI element initializations
    JPanel field_panel = new JPanel();
    JPanel text_field = new JPanel();

    // class constructor
    MultifieldInputPanel(String[] fieldNames) {
        // field layout are vertically stacked with same width using BoxLayout
        field_panel.setLayout(new BoxLayout(field_panel, BoxLayout.Y_AXIS));

        // loop to create each field in fieldNames array and attach the functions
        // to the buttons ActionListener, using Stream() to iterate through the array
        // and attach the functions, since using a traditional for (i) loop doesnt work
        // here
        Stream.iterate(0, i -> i + 1).limit(fieldNames.length).forEach(i -> {
            text_field.setLayout(new BoxLayout(text_field, BoxLayout.Y_AXIS));
            text_field.add(new JLabel(fieldNames[i]));
            JTextField newTextField = new JTextField(10);
            newTextField.addActionListener(e -> newTextField.getText());
            text_field.add(newTextField);
            field_panel.add(text_field);
        });

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to center the field panel
        setLayout(new GridBagLayout());

        // to add the field panel to the parent panel
        add(field_panel, new GridBagConstraints());
    }

    // method to return a hashmap of the saved field names and values
    public HashMap<String, String> getSavedFields() {
        // create a new hashmap
        HashMap<String, String> savedFields = new HashMap<String, String>();
        // iterate over the text_field panel and get the text from each label and text
        // field
        // and save it to the hashmap
        Stream.iterate(0, i -> i + 1).limit(text_field.getComponentCount()).forEach(i -> {
            // if its a JLabel, get the text from it and save it to the hashmap
            if (text_field.getComponent(i) instanceof JLabel) {
                savedFields.put(((JLabel) text_field.getComponent(i)).getText(), "");
            }
            // if its a JTextField, get the text from it and save it to the hashmap
            if (text_field.getComponent(i) instanceof JTextField) {
                savedFields.put(((JLabel) text_field.getComponent(i)).getText(),
                        ((JTextField) text_field.getComponent(i)).getText());
            }
        });
        return savedFields;
    }
}