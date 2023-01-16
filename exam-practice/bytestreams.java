import java.io.*;

public class bytestreams {
    // this code snippet shows how to use byte streams
    // byte streams are used to read and write bytes

    // class fields
    private int x;
    private int y;

    // class constructor
    public bytestreams(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // class methods
    public void saveData() {
        // this method saves data to a file
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("data.txt");
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeInt(x);
            dataOutputStream.writeInt(y);
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        // this method loads data from a file
        try {
            FileInputStream fileInputStream = new FileInputStream("data.txt");
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            x = dataInputStream.readInt();
            y = dataInputStream.readInt();
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
