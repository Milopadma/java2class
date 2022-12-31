import java.awt.*;
import java.util.HashMap;
import java.util.stream.Stream;

import javax.swing.*;

/**
 * This class represents the multifield input panel. It is used to display a
 * panel with multiple fields to input data.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya - E2000426
 *         Date: 2022-24-12
 */
public class MultifieldInputPanel extends JPanel {
    // GUI element initializations
    JPanel field_panel = new JPanel();
    JPanel text_field_panel = new JPanel();

    boolean allFieldsFilled = false;

    // class constructor
    /**
     * This constructor creates a panel with multiple fields in it.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param fieldNames
     */
    MultifieldInputPanel(String[] fieldNames) {
        // field layout are vertically stacked with same width using BoxLayout
        field_panel.setLayout(new BoxLayout(field_panel, BoxLayout.Y_AXIS));

        // loop to create each field in fieldNames array and attach the functions
        // to the buttons ActionListener, using Stream() to iterate through the array
        // and attach the functions, since using a traditional for (i) loop doesnt work
        // here
        Stream.iterate(0, i -> i + 1).limit(fieldNames.length).forEach(i -> {
            text_field_panel.setLayout(new BoxLayout(text_field_panel, BoxLayout.Y_AXIS));
            text_field_panel.add(new JLabel(fieldNames[i]));
            JTextField newTextField = new JTextField(10);
            // newTextField.addActionListener(e -> newTextField.getText());
            text_field_panel.add(newTextField);
            field_panel.add(text_field_panel);
        });

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to center the field panel
        setLayout(new GridBagLayout());

        // to add the field panel to the parent panel
        add(field_panel, new GridBagConstraints());
    }

    /**
     * This method returns a hashmap of the saved field names and values.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @return HashMap<String, String> savedFields
     */
    public HashMap<String, String> getSavedFields() {
        // create a new hashmap
        HashMap<String, String> savedFields = new HashMap<String, String>();
        // iterate over the text_field panel and get the text from each label and text
        // field
        // and save it to the hashmap
        Stream.iterate(0, i -> i + 1).limit(text_field_panel.getComponentCount()).forEach(i -> {
            // since every JLabel is acompanied by a JTextField, we can just iterate over
            // the text_field panel and get the text from each JLabel and JTextField
            // and save it to the hashmap
            try {
                if (text_field_panel.getComponent(i) instanceof JLabel) {
                    savedFields.put(((JLabel) text_field_panel.getComponent(i)).getText(),
                            ((JTextField) text_field_panel.getComponent(i + 1)).getText());
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }

            // if (text_field.getComponent(i) instanceof JLabel) {
            // savedFields.put(((JLabel) text_field.getComponent(i)).getText(), "");
            // }
            // // if its a JTextField, get the text from it and save it to the hashmap
            // if (text_field.getComponent(i) instanceof JTextField) {
            // savedFields.put(((JTextField) text_field.getComponent(i)).getText(),
            // ((JTextField) text_field.getComponent(i)).getText());
            // }
        });
        return savedFields;
    }

    /**
     * This method checks if all fields are filled.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @return boolean allFieldsFilled
     */
    public boolean checkFields() {
        // iterate over the text_field panel and get the text from each label and text
        Stream.iterate(0, i -> i + 1).limit(text_field_panel.getComponentCount()).forEach(i -> {
            try {
                if (text_field_panel.getComponent(i) instanceof JLabel) {
                    if (((JTextField) text_field_panel.getComponent(i + 1)).getText().equals("")) {
                        allFieldsFilled = false;
                        return;
                    } else {
                        allFieldsFilled = true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        });
        return allFieldsFilled;
    }
}