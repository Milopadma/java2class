
import java.awt.*;
import java.util.stream.Stream;

import javax.swing.*;

// a close relative of MultifieldInputPanel but sharded to its own class as it has its own slight differences
// todo .. this class can be combined with MultifieldInputPanel by deriving an abstract class from both of them and just using that
public class RequestSubmissionViewPanel extends JPanel {

    private JPanel text_field_panel = new JPanel();

    public RequestSubmissionViewPanel(String[] input_labels, Runnable[] button_functions) {
        // if the input_label is equals to DESCRIPTION, it will be the largest text area
        // else it will be a normal text field, all labels will be on the top left side
        // of the textfields

        // the list layout is vertically stacked with same width using BoxLayout
        JPanel field_panel = new JPanel();
        field_panel.setLayout(new BoxLayout(field_panel, BoxLayout.Y_AXIS));

        // loop to create each label and field value and add them to the list panel
        Stream.iterate(0, i -> i + 1).limit(input_labels.length).forEach(i -> {
            field_panel.add(new JLabel(input_labels[i]));
            if (input_labels[i].equals("DESCRIPTION")) {
                field_panel.add(new JTextArea());
            } else {
                field_panel.add(new JTextField());
            }
        });

        // a "done" button
        JButton done_button = new JButton("Done");
        done_button
                .addActionListener(e -> MainView.saveMultifieldTextFields(getSavedfieldNames(), getSavedfieldValues()));
        // ! this throws a null exception

        done_button.addActionListener(e -> button_functions[0].run());
        field_panel.add(done_button);

        // for the Back button
        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> button_functions[1].run());
        field_panel.add(back_button);

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to center the list panel
        setLayout(new GridBagLayout());

        // to add the list panel to the parent panel
        add(field_panel, new GridBagConstraints());
    }

    private String[] getSavedfieldNames() {
        // iterate overe the text_field panel and get the text from each label
        // and save it to the string array
        String[] savedfieldNames = new String[text_field_panel.getComponentCount()];
        Stream.iterate(0, i -> i + 1).limit(text_field_panel.getComponentCount()).forEach(i -> {
            // if its a JLabel, get the text from it and save it to the string array
            if (text_field_panel.getComponent(i) instanceof JLabel) {
                savedfieldNames[i] = ((JLabel) text_field_panel.getComponent(i)).getText();
            }
        });
        return savedfieldNames;
    }

    private String[] getSavedfieldValues() {
        // iterate over the text_field panel and get the text from each text field
        // and save it to the string array
        String[] savedfieldValues = new String[text_field_panel.getComponentCount()];
        Stream.iterate(0, i -> i + 1).limit(text_field_panel.getComponentCount()).forEach(i -> {
            // if its a JTextField, get the text from it and save it to the string array
            if (text_field_panel.getComponent(i) instanceof JTextField) {
                savedfieldValues[i] = ((JTextField) text_field_panel.getComponent(i)).getText();
            }
        });
        return savedfieldValues;
    }
}
