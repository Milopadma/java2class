import java.awt.*;
import java.util.stream.Stream;

import javax.swing.*;

// this class represents the standard info list panel
public class LabelledListViewPanel extends JPanel {
    // GUI element initializations
    JPanel list_panel = new JPanel();
    JPanel button_panel = new JPanel();

    public LabelledListViewPanel(String[] labels, String[] data) {
        // the list layout is vertically stacked with same width using BoxLayout
        list_panel.setLayout(new BoxLayout(list_panel, BoxLayout.Y_AXIS));

        // iterate through labels and add jlabel data beside it
        Stream.iterate(0, i -> i + 1).limit(labels.length).forEach(i -> {
            list_panel.add(new JLabel(labels[i]));
            list_panel.add(new JLabel(data[i]));
        });

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to center the list panel
        setLayout(new GridBagLayout());

        // to add the list panel to the parent panel
        add(list_panel, new GridBagConstraints());
    }

}
