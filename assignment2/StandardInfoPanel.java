import java.awt.*;

import javax.swing.*;

// this class represents the standard info panel
public class StandardInfoPanel extends JPanel {
    // GUI element initializations
    JPanel info_panel = new JPanel();
    JPanel button_panel = new JPanel();

    public StandardInfoPanel(String string, String string2, JButton[] buttons) {
        // info layout are vertically stacked with same width using BoxLayout
        info_panel.setLayout(new BoxLayout(info_panel, BoxLayout.Y_AXIS));

        // add the info text to the info panel
        info_panel.add(new JLabel(string));
        info_panel.add(new JLabel(string2));

        // add the buttons to the button panel
        for (JButton button : buttons) {
            button_panel.add(button);
        }

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to add the info panel and button panel to the parent panel
        add(info_panel, new GridBagConstraints());
        add(button_panel, new GridBagConstraints());
    }
}
