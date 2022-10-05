
class gui {
    public static void main(String[] args) {
        // initialize gui components
        javax.swing.JFrame frame = new javax.swing.JFrame("Hello World"); // the window box
        javax.swing.JLabel label = new javax.swing.JLabel("Hello World"); // the text label
        javax.swing.JButton button = new javax.swing.JButton("Click me"); // the button
        javax.swing.JPanel panel = new javax.swing.JPanel(); // the panel to hold the components (inside frame)
        // add panel to frame
        frame.add(panel);
        // add components to panel
        panel.add(label);
        panel.add(button);
        // set frame properties //this apparently needs to be last
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
