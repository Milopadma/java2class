import java.io.*;

public class characterstreams {
    // this code snippet shows how to use character streams
    // character streams are used to read and write characters

    // class fields
    private int x;
    private int y;

    // class constructor
    public characterstreams(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // class methods
    public void saveData() {
        // this method saves data to a file
        try {
            FileWriter fileWriter = new FileWriter("data.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(x);
            bufferedWriter.write(y);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void loadData() {
        // this method loads data from a file
        try {
            FileReader fileReader = new FileReader("data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            x = bufferedReader.read();
            y = bufferedReader.read();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
