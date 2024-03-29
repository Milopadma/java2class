import java.awt.*;
import java.util.stream.Stream;

import javax.swing.*;

/**
 * This class represents the generic panel with multiple buttons in it. Only
 * cares about the display of n amount of buttons.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya - E2000426
 *         Date: 2022-24-12
 */
public class MultibuttonInputPanel extends JPanel {
    // GUI element initializations
    JPanel button_panel = new JPanel();
    JPanel back_button_panel = new JPanel();

    // class constructor that accepts a String array of button names and an Event
    // listener to add to the buttons
    /**
     * This constructor creates a panel with multiple buttons in it.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param buttons - an array of buttons to be added to the panel.
     */
    public MultibuttonInputPanel(JButton[] buttons) {
        // button layout are vertically stacked with same width using BoxLayout
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));

        // back button will be placed on the bottom portion of the parent panel
        back_button_panel.setLayout(new BoxLayout(back_button_panel, BoxLayout.Y_AXIS));

        // iterate through the buttons array and add the buttons to the button panel
        Stream.iterate(0, i -> i + 1).limit(buttons.length).forEach(i -> {
            // if the button is the "Back" button, add it to the back_button_panel
            if (buttons[i].getText().equals("Back")) {
                back_button_panel.add(buttons[i]);
                return;
            } else {
                button_panel.add(buttons[i]);
            }
        });

        // to center the panel
        setLayout(new BorderLayout());

        // addding the panels to the parent
        add(button_panel, BorderLayout.CENTER);
        add(back_button_panel, BorderLayout.SOUTH);
    }
}
