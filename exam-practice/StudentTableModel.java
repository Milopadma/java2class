import java.util.*;
import javax.swing.table.*;

public class StudentTableModel extends AbstractTableModel {
    private static final String[] columnHeader = { "Name", "StudentID", "Score" };
    private ArrayList<Student> students;

    // constructor
    public StudentTableModel() {
        students = new ArrayList<Student>();
    };

    // abstract method implementation
    public int getRowCount() {
        return getStudents().size();
    }

    public int getColumnCount() {
        return columnHeader.length;
    }

    public Object getValueAt(int row, int column) {
        Student s = getStudents().get(row);
        switch (column) {
            case 0:
                return s.getName();
            case 1:
                return s.getStudentID();
            case 2:
                return new Double(s.getScore());
            default:
                return "";
        }
    }

    // method to get the column name from its index in the
    // string array
    public String getColumnName(int column) {
        return columnHeader[column];
    }

    // methods to edit the tableModel
    public void add(Student s) {
        getStudents().add(s);
        fireTableDataChanged();
    }

    public Student getStudent(int row) {
        Student s = getStudents().get(row);
        return s;
    }

    public Student remove(int row) {
        Student s = getStudents().remove(row);
        fireTableDataChanged();
        return s;
    }

    public void clear() {
        getStudents().clear();
        fireTableDataChanged();
    }

    // methods to edit the Collection of students
    // that is maintained by the tableModel
    private void setStudents() {
        students = new ArrayList<Student>();
    }

    private ArrayList<Student> getStudents() {
        return students;
    }

    public boolean contains(Student s) {
        return getStudents().contains(s);
    }

    public boolean isEmpty() {
        return getStudents().size() == 0;
    }
}