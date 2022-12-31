import javax.swing.*;
import java.awt.*;

/**
 * This class represents the standard list view panel.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya - E2000426
 *         Date: 2022-24-12
 */
public class StandardListViewPanel extends JPanel {

    // gui init
    JPanel list_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JList<String> list = new JList<String>();

    /**
     * This constructor creates a panel with a list and buttons in it.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param list_content - an array of strings to be added to the list.
     * @param buttons      - an array of buttons to be added to the panel.
     * 
     */
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
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.X_AXIS));

        // add the buttons
        for (JButton button : buttons) {
            button_panel.add(button);
        }

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // to add the list panel and button panel to the parent panel
        add(list_panel, new GridBagConstraints());
        add(button_panel, new GridBagConstraints());
    }
}
