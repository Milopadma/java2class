
class gui {
    public static void main(String[] args) {
        // initialize components
        javax.swing.JFrame frame = new javax.swing.JFrame("Hello World");
        javax.swing.JLabel label = new javax.swing.JLabel("Hello World");
        javax.swing.JButton button = new javax.swing.JButton("Click me");
        javax.swing.JPanel panel = new javax.swing.JPanel();
        // add components to panel
        panel.add(label);
        panel.add(button);
        // add panel to frame
        frame.add(panel);
        // set frame properties
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
