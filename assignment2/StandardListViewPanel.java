import javax.swing.*;
import java.awt.*;

public class StandardListViewPanel extends JPanel {

    // gui init
    JPanel list_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JList<String> list = new JList<String>();

    public StandardListViewPanel(String[] list_content, JButton[] buttons) {
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        // new list
        list = new JList<String>(list_content);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(500, 240));

        // add the list to the list panel
        list_panel.add(listScroller);

        // button layout are vertically stacked with same width using BoxLayout
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));

        // add the buttons
        for (JButton button : buttons) {
            button_panel.add(button);
        }

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to add the list panel and button panel to the parent panel
        add(list_panel, new GridBagConstraints());
        add(button_panel, new GridBagConstraints());
    }
}
