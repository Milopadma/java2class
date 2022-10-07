import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;

public class apicalls {
    // class declarations
    private String res;

    // this method manages all things GUI
    private void GuiManager() {
        // initialize gui components
        JFrame frame = new JFrame("API CALLS"); // the window box
        JLabel label = new JLabel("Response here"); // the text label
        JButton button = new JButton("Click me"); // the button
        JTextField textField = new JTextField(); // the input box

        // label params
        label.setBounds(150, 20, 400, 30); // x axis, y axis, width, height

        // text field params
        textField.setBounds(150, 100, 100, 30); // x axis, y axis, width, height

        // button params
        button.setBounds(150, 150, 95, 30); // x axis, y axis, width, height

        // add components to panel
        frame.add(label);
        frame.add(button);
        frame.add(textField);

        // add panel to frame

        // set frame properties //this apparently needs to be last
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        // add an event listener to the button
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // CallApi method that takes the text from the input box
                if (textField.getText().length() > 0) {
                    try {
                        res = CallApi(textField.getText());
                    } catch (IOException e1) {
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