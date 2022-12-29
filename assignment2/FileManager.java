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
    public static boolean saveData(Object data, String fileName) {
        // check if the file exists
        File file = new File(FILE_PATH + fileName);
        if (!file.exists()) {
            // create the file with the given name
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else
                    System.out.println("File already exists.");
                // create the file output stream
                // and save the data to it
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
        // if the file exists, then just save the data to it
        else {
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
    }

    /**
     * This method is used to load the data from the file.
     * 
     * @return
     */
    public static Object loadData(String fileName) {
        // check if the file exists
        File file = new File(FILE_PATH + fileName);
        if (file.exists()) {
            // load the data
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fis);
                Object data = in.readObject();
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
