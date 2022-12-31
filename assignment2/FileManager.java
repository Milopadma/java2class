import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class is used to manage the files for saving and loading the application
 * data. This class uses serialisation for the saving and loading of the app
 * data.
 * 
 * 
 * @author I Gusti Bagus Milo Padma Wijaya - E2000426
 *         Date: 2022-24-12
 */
public class FileManager {
    // class fields
    private static final String FILE_PATH = "appdata/";

    // class methods
    /**
     * This method is used to save the data to the file.
     * 
     * @param data     - the data to be saved
     * @param fileName - the name of the file
     * @return boolean - true if the data is saved successfully, false otherwise.
     */
    public static boolean saveData(SchoolHELP data, String fileName) {
        // save the data by means of serialization
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH + fileName);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            // out.writeObject(testClass);
            out.writeObject(data);
            // System.out.println("Data saved to file:" + data);
            // for (User user : data.getUsers()) {
            // System.out.println("User: " + user);
            // }
            out.close();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method is used to load the data from the file.
     * 
     * @param fileName - the name of the file
     * @return SchoolHELP - the data loaded from the file
     */
    public static SchoolHELP loadData(String fileName) {
        // check if the file exists
        File file = new File(FILE_PATH + fileName);
        if (file.exists()) {
            // load the data
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fis);
                SchoolHELP data = (SchoolHELP) in.readObject();
                // System.out.println("Data loaded from file: " + data);
                // for (User user : data.getUsers()) {
                // System.out.println(user);
                // }
                in.close();
                fis.close();
                return data;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
