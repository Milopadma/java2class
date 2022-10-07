import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class apicalls {
    // class declarations
    private String res;

    // this method manages all things GUI
    private void GuiManager() {
        // initialize gui components
        javax.swing.JFrame frame = new javax.swing.JFrame("Hello World"); // the window box
        javax.swing.JLabel label = new javax.swing.JLabel("Hello World"); // the text label
        javax.swing.JButton button = new javax.swing.JButton("Click me"); // the button
        javax.swing.JTextField textField = new javax.swing.JTextField(); // the input box
        javax.swing.JPanel panel = new javax.swing.JPanel(); // the panel to hold the components (inside frame)
        // add panel to frame
        frame.add(panel);
        // add components to panel
        panel.add(label);
        panel.add(button);
        panel.add(textField);
        // set frame properties //this apparently needs to be last
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        // add an event listener to the button
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // CallApi method that takes the text from the input box
                if (textField.getText().length() > 0) {
                    try {
                        res = CallApi(textField.getText());
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                // set the label text to the response
                label.setText(res);
            }
        });
    }

    // this method calls the api
    /**
     * @param query
     * @throws IOException
     */
    private String CallApi(String query) throws IOException {
        String url = "https://api.github.com/users/" + query;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return "GET request not worked";
        }

    }

    // method main runs the program
    public static void main(String[] args) {
        apicalls app = new apicalls();
        app.GuiManager();
    }
}