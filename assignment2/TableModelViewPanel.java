import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

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

    public TableModelViewPanel(String[] columnNames, ArrayList data, JButton[] buttons) {
        // table layout are vertically stacked with same width using BoxLayout
        table_panel.setLayout(new BoxLayout(table_panel, BoxLayout.Y_AXIS));

        // create the table model and add the data to it
        tableModel = new DefaultTableModel(columnNames, 0);
        for (Object row : data) {
            tableModel.addRow((Object[]) row);
        }

        // add a click listener to the table
        tableModel.addTableModelListener(e -> {
            // get the row and column of the cell that was clicked
            int row = e.getFirstRow();
            int column = e.getColumn();

            // get the value of the cell that was clicked
            Object value = tableModel.getValueAt(row, column);

            // pass the value to the MainView class to show the offers of that request
            MainView.showOffersOfRequest((Request) value);
        });

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
