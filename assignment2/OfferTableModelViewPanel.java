import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

// this class represents the table model view panel, 
// creates an object that takes in an arraylist of data and displays 
// it onto a table view

public class OfferTableModelViewPanel extends JPanel {
    private static JTable table = new JTable();

    // GUI element initializations
    private DefaultTableModel tableModel;
    JPanel table_panel = new JPanel();
    JPanel button_panel = new JPanel();

    public OfferTableModelViewPanel(String[] columnNames, ArrayList<Offer> data, JButton[] buttons) {
        // table layout are vertically stacked with same width using BoxLayout
        table_panel.setLayout(new BoxLayout(table_panel, BoxLayout.Y_AXIS));

        // create the table model and add the data to it
        tableModel = new DefaultTableModel(columnNames, 0);
        for (Offer row : data) {
            tableModel.addRow(new Object[] { row.getOfferID(), row.getOfferStatus(), row.getOfferDate(),
                    row.getIsOwnedBy().getFullname(), row.getOfferRemarks() });
        }

        // // listen for click events in the table rows
        // tableModel.addTableModelListener(e -> {
        // // if the table is clicked
        // if (e.getType() == TableModelEvent.UPDATE) {
        // // get the selected row
        // int selectedRow = table.getSelectedRow();

        // // get the value of the first column of the selected row
        // Object value = table.getValueAt(selectedRow, 0);

        // // run the function
        // runnable_function.run();
        // // ! TODO VERY unsure that this works
        // }
        // });

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
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // to add the table panel and button panel to the parent panel
        add(table_panel, new GridBagConstraints());
        add(button_panel, new GridBagConstraints());
    }

    public static Object getSelectedRowValue() {
        // get the selected row
        int selectedRow = table.getSelectedRow();

        // get the value of the first column of the selected row
        Object value = table.getValueAt(selectedRow, 0);

        return value;
    }
}
