import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

// this class represents the table model view panel, 
// creates an object that takes in an arraylist of data and displays 
// it onto a table view

public class UserTableModelViewPanel extends JPanel {
    private static JTable table = new JTable();

    // GUI element initializations
    private DefaultTableModel tableModel;
    JPanel table_panel = new JPanel();
    JPanel button_panel = new JPanel();

    public UserTableModelViewPanel(String[] columnNames, ArrayList<User> data, JButton[] buttons) {
        // table layout are vertically stacked with same width using BoxLayout
        table_panel.setLayout(new BoxLayout(table_panel, BoxLayout.Y_AXIS));

        // create the table model and add the data to it
        tableModel = new DefaultTableModel(columnNames, 0);
        for (User row : data) {
            tableModel.addRow(new Object[] { row.getUsername(), row.getPassword(), row.getFullname(),
                    row.getEmail(), Long.toString(row.getPhone()) });
        }

        // create the table and add the table model to it
        table = new JTable(tableModel);

        // put table in a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        table_panel.add(scrollPane);

        // add the buttons
        for (JButton button : buttons) {
            button_panel.add(button);
        }

        // parent panel layout, items are stacked horizontally
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // to add the table panel and button panel to the parent panel
        add(table_panel, new GridBagConstraints());
        add(button_panel, new GridBagConstraints());
    }
}
