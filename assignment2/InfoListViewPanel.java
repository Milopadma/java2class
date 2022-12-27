import java.awt.*;
import java.util.stream.Stream;

import javax.swing.*;

// this class represents the standard info list panel with just one button in it
public class InfoListViewPanel extends JPanel {
    // class fields
    // todo

    // GUI element initializations
    JPanel list_panel = new JPanel();
    JPanel button_panel = new JPanel();

    public InfoListViewPanel(String[] input_labels, String[] input_field_values, Runnable button_function) {
        // the list layout is vertically stacked with same width using BoxLayout
        list_panel.setLayout(new BoxLayout(list_panel, BoxLayout.Y_AXIS));

        // loop to create each label and field value and add them to the list panel
        Stream.iterate(0, i -> i + 1).limit(input_labels.length).forEach(i -> {
            list_panel.add(new JLabel(input_labels[i]));
            list_panel.add(new JLabel(input_field_values[i]));
        });

        // button layout are vertically stacked with same width using BoxLayout
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));

        // create the button and attach the function to the buttons ActionListener
        JButton button = new JButton("Done");

        button.addActionListener(e -> button_function.run());

        button_panel.add(button);

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to center the list panel
        setLayout(new GridBagLayout());

        // to add the list panel to the parent panel
        add(list_panel, new GridBagConstraints());
        add(button_panel, new GridBagConstraints());
    }

}
