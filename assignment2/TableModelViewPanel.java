import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

// this class represents the table model view panel, 
// creates an object that takes in an arraylist of data and displays 
// it onto a table view

public class TableModelViewPanel extends JPanel {
    // class fields
    // todo

    // GUI element initializations
    private DefaultTableModel tableModel;
    JPanel table_panel = new JPanel();
    JPanel button_panel = new JPanel();

    public TableModelViewPanel(String[] columnNames, String[][] data, JButton[] buttons) {
        // table layout are vertically stacked with same width using BoxLayout
        table_panel.setLayout(new BoxLayout(table_panel, BoxLayout.Y_AXIS));

        // create the table model and add the data to it
        tableModel = new DefaultTableModel(data, columnNames);

        // create the table and add the table model to it
        JTable table = new JTable(tableModel);

        // add the table to the table panel
        table_panel.add(table);

        // add the buttons to the button panel
        for (JButton button : buttons) {
            button_panel.add(button);
        }

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to add the table panel and button panel to the parent panel
        add(table_panel, new GridBagConstraints());
        add(button_panel, new GridBagConstraints());
    }
}
