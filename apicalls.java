import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.*;
import javax.swing.*;

public class apicalls {
    // class declarations
    private String res;

    // this method manages all things GUI
    private void guiManager() {
        // initialize gui components
        
        // label params
        JLabel label = new JLabel(); // the text label
        label.setVerticalAlignment(0); // 0 is top, 1 is center, 2 is bottom
        label.setText(res); // set the text

        // text field params
        JTextField textField = new JTextField(); // the input box
        textField.setBorder(BorderFactory.createEmptyBorder()); // remove the border

        // button params
        JButton button = new JButton("Click me"); // the button
        // add an event listener to the button
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // CallApi method that takes the text from the input box
                if (textField.getText().length() > 0) {
                    try {
                        res = callApi(textField.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                // set the label text to the response
                label.setText(res);
            }
        });

        // panel
        JPanel panel = new JPanel(); // the panel
        panel.setBackground(Color.gray);
        panel.setLayout(new GridLayout(3, 3, 3, 3)); // set the layout

        // set frame properties //this apparently needs to be last
        JFrame frame = new JFrame("API CALLS"); // the window box
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        panel.add(textField);
        panel.add(button);
        panel.add(label);
        frame.add(panel);

    }

    // this method calls the api
    /**
     * @param query
     * @throws IOException
     */
    private String callApi(String query) throws IOException {
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
            System.out.println(response.toString());
            return response.toString();
        } else {
            return "GET request not worked";
        }
    }

    // method main runs the program
    public static void main(String[] args) {
        apicalls app = new apicalls();
        app.guiManager();
    }
}