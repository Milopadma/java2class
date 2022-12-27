import java.awt.*;
import java.util.stream.Stream;

import javax.swing.*;

// this class represents the generic panel with multiple buttons in it
public class MultibuttonInputPanel extends JPanel {
    // class fields
    // todo

    // GUI element initializations
    JPanel button_panel = new JPanel();

    // class constructor that accepts a String array of button names and an Event
    // listener to add to the buttons
    public MultibuttonInputPanel(String[] buttonNames, Runnable[] button_functions) {
        // button layout are vertically stacked with same width using BoxLayout
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));

        // loop to create each button in buttonNames array and attach the functions
        // to the buttons ActionListener, using Stream() to iterate through the array
        // and attach the functions, since using a traditional for (i) loop doesnt work
        // here
        Stream.iterate(0, i -> i + 1).limit(buttonNames.length).forEach(i -> {
            JButton button = new JButton(buttonNames[i]);

            // and save this panel to the MainView class' currentPanel field
            button.addActionListener(e -> MainView.saveCurrentPanel(this));
            button.addActionListener(e -> button_functions[i].run());

            button_panel.add(button);
        });

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to center the button panel
        setLayout(new GridBagLayout());

        // to add the button panel to the parent panel
        add(button_panel, new GridBagConstraints());
    }
}
