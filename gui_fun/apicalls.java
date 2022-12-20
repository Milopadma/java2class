package gui_fun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class apicalls {
    // class declarations
    private String imageurlstringRes;
    private URL imageurl;

    // this method manages all things GUI
    private void guiManager() {
        // initialize gui components

        // label params
        JLabel label = new JLabel(); // the text label
        label.setVerticalAlignment(0); // 0 is top, 1 is center, 2 is bottom
        label.setText("Search github profile pictures"); // set the text

        JLabel labelImage = new JLabel(); // the image label
        labelImage.setVerticalAlignment(0); // 0 is top, 1 is center, 2 is bottom

        // text field params
        JTextField textField = new JTextField(); // the input box
        textField.setBorder(BorderFactory.createEmptyBorder()); // remove the border
        textField.setPreferredSize(new Dimension(200, 30)); // set the size

        // button params
        JButton button = new JButton("Search"); // the button
        // set the size of the button
        button.setPreferredSize(new Dimension(100, 30));

        // add an event listener to the button
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // CallApi method that takes the text from the input box
                if (textField.getText().length() > 0) {
                    try {
                        imageurlstringRes = callApi(textField.getText());
                        // add https:// in front of the url
                        imageurlstringRes = "https:" + imageurlstringRes;
                        // turn imageurlstringREs to URL
                        imageurl = new URL(imageurlstringRes);
                        System.out.println(imageurlstringRes);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                // set the label text to the response
                try {
                    BufferedImage img = ImageIO.read(imageurl);
                    labelImage.setIcon(new ImageIcon(img)); // this is responsible for updating the image 
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // controls panel
        JPanel panel = new JPanel(); // the panel
        panel.setBackground(Color.gray);
        panel.setLayout(new GridLayout(
                3, // rows
                1, // columns
                16, // horizontal gap
                16 // vertical gap
        )); // set the layout

        // image panel
        JPanel panelImage = new JPanel(); // the panel
        panelImage.setBackground(Color.gray);

        // set frame properties //this apparently needs to be last
        JFrame frame = new JFrame("API CALLS"); // the window box
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2, 1, 3, 3)); // set the layout
        // add the components to the panel
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panelImage.add(labelImage);
        // add the panel to the frame
        frame.add(panel);
        frame.add(panelImage);
    }

    // this method calls the api
    /**
     * @param query
     * @throws IOException
     */
    private String callApi(String query) throws IOException {
        String url = "https://api.github.com/users/" + query;
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = conn.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            // receive the response as an object
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // get the image_url from the response
            String[] responseArray = response.toString().split(",");
            String imageurlstringRes = responseArray[3].split(":")[2].replace("\"", "");
            return imageurlstringRes;
        } else {
            return "User not found";
        }
    }

    // method main runs the program
    public static void main(String[] args) {
        apicalls app = new apicalls();
        app.guiManager();
    }
}