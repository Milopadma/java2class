import java.awt.*;
import java.util.stream.Stream;

import javax.swing.*;

// this class represents the standard info dialog panel with two buttons in it
public class StandardInfoPanel extends JPanel {

    // class fields
    // todo

    // GUI element initializations
    JPanel info_panel = new JPanel();
    JPanel button_panel = new JPanel();

    public StandardInfoPanel(String string, String string2, Runnable[] button_functions, School newSchool) {
        // info layout are vertically stacked with same width using BoxLayout
        info_panel.setLayout(new BoxLayout(info_panel, BoxLayout.Y_AXIS));

        // add the info text to the info panel
        info_panel.add(new JLabel(string));
        info_panel.add(new JLabel(string2));

        // button layout are vertically stacked with same width using BoxLayout
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));

        // loop to create each button in buttonNames array and attach the functions
        // to the buttons ActionListener, using Stream() to iterate through the array
        // and attach the functions, since using a traditional for (i) loop doesnt work
        // here
        Stream.iterate(0, i -> i + 1).limit(button_functions.length).forEach(i -> {
            JButton button = new JButton(button_functions[i].toString());

            button.addActionListener(e -> MainView.showSchoolInfoView(newSchool));
            button.addActionListener(e -> button_functions[i].run());

            button_panel.add(button);
        });

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to center the info panel
        setLayout(new GridBagLayout());

        // to add the info panel to the parent panel
        add(info_panel, new GridBagConstraints());

        // to center the button panel
        setLayout(new GridBagLayout());

        // to add the button panel to the parent panel
        add(button_panel, new GridBagConstraints());

    }

}
