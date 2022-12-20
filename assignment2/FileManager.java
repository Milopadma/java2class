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
 * @author I GUSTI BAGUS MILO PADMA WIJAYA
 * 
 */
public class FileManager {
    // class fields
    private static final String FILE_PATH = "appdata/";

    // class methods
    /**
     * This method is used to save the data to the file.
     * 
     * @param data
     * @return
     */
    public static boolean saveData(String data, String fileName) {
        // check if the file exists
        File file = new File(FILE_PATH + fileName);
        if (!file.exists()) {
            // create the file
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fos);
                out.writeObject(data);
                out.close();
                fos.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * This method is used to load the data from the file.
     * 
     * @return
     */
    public static String loadData(String fileName) {
        // check if the file exists
        File file = new File(FILE_PATH + fileName);
        if (file.exists()) {
            // load the data
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fis);
                String data = (String) in.readObject();
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
