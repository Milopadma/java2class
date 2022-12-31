import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the table model view panel, creates an object that
 * takes in an arraylist of data and displays it onto a table view.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya - E2000426
 *         Date: 2022-24-12
 */
public class RequestTableModelViewPanel extends JPanel {
    private static JTable table = new JTable();

    // GUI element initializations
    private DefaultTableModel tableModel;
    JPanel table_panel = new JPanel();
    JPanel button_panel = new JPanel();

    /**
     * This constructor creates a table model view panel with a table and buttons.
     * 
     * @author I Gusti Bagus Milo Padma Wijaya - E2000426
     *         Date: 2022-24-12
     * 
     * @param columnNames - an array of column names to be added to the table.
     * @param data        - an arraylist of data to be added to the table.
     * @param buttons     - an array of buttons to be added to the panel.
     * 
     */
    public RequestTableModelViewPanel(String[] columnNames, ArrayList<Request> data, JButton[] buttons) {
        // table layout are vertically stacked with same width using BoxLayout
        table_panel.setLayout(new BoxLayout(table_panel, BoxLayout.Y_AXIS));

        // create the table model and add the data to it
        tableModel = new DefaultTableModel(columnNames, 0);
        for (Request row : data) {
            tableModel.addRow(new Object[] { row.getRequestID(), row.getRequestStatus(), row.getRequestDate(),
                    row.getSchool().getSchoolName(), row.getSchool().getCity(),
                    row.getRequestDescription() });
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
