import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class TestEventFrame extends JFrame implements ActionListener {
    // class fields
    private JButton btn;
    private JLabel lbl;
    private int count = 0;

    public static void main(String[] args) {
        TestEventFrame test = new TestEventFrame();
        test.setVisible(true);
    }

    public TestEventFrame() {
        super("Test Event Frame");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        btn = new JButton("Click Me");
        btn.addActionListener(this);
        add(btn);

        lbl = new JLabel("Clicks: 0");
        add(lbl);
    }

    public void actionPerformed(ActionEvent e) {
        count++;
        lbl.setText("Clicks: " + count);
    }

}