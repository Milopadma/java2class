
// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.util.ArrayList;

// School class
/**
 * 
 * School class manages the data of School instances. Contains arraylists of
 * schoolAdmins instances and requests instances.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class School {
    // this class manages School data and also SchoolAdmin classes

    // class variables
    private String schoolName;
    private int schoolID;
    private String address;
    private String city;

    // each school has multiple SchoolAdmins
    private ArrayList<SchoolAdmin> schoolAdmins = new ArrayList<SchoolAdmin>();

    // each school has multiple Requests
    private ArrayList<Request> requests = new ArrayList<Request>();

    // class constructor
    /**
     * 
     * School class constructor. With arguments.
     * 
     * @param schoolName
     * @param schoolID
     * @param address
     * @param city
     */
    public School(String schoolName, int schoolID, String address, String city) {
        this.schoolName = schoolName;
        this.schoolID = schoolID;
        this.address = address;
        this.city = city;
    }

    // class constructor without args
    /**
     * 
     * School class constructor. Without arguments.
     */
    public School() {
        this.schoolName = "default school";
        this.schoolID = 000;
        this.address = "default address";
        this.city = "default city";
    }

    // class setter and getter methods
    /**
     * 
     * Method that returns the schoolName of this School class.
     * 
     * @return String
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 
     * Method that sets the schoolName of this School class.
     * 
     * @param schoolName
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * 
     * Method that returns the schoolID of this School class.
     * 
     * @return int
     */
    public int getSchoolID() {
        return schoolID;
    }

    /**
     * 
     * Method that sets the schoolID of this School class.
     * 
     * @param schoolID
     */
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    /**
     * 
     * Method that returns the address of this School class.
     * 
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * Method that sets the address of this School class.
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * Method that returns the city of this School class.
     * 
     * @return String
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * Method that sets the city of this School class.
     * 
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    // setter and getters for the ArrayLists
    /**
     * 
     * Method that returns the schoolAdmins of this School class.
     * 
     * @return ArrayList
     */
    public ArrayList<SchoolAdmin> getSchoolAdmins() {
        return schoolAdmins;
    }

    /**
     * 
     * Method that sets the schoolAdmins of this School class.
     * 
     * @param schoolAdmins
     */
    public void setSchoolAdmins(ArrayList<SchoolAdmin> schoolAdmins) {
        this.schoolAdmins = schoolAdmins;
    }

    /**
     * 
     * Method that returns the requests of this School class.
     * 
     * @return ArrayList
     */
    public ArrayList<Request> getRequests() {
        return requests;
    }

    /**
     * 
     * Method that sets the requests of this School class.
     * 
     * @param requests
     */
    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    // singular getRequestByID
    /**
     * 
     * Method that returns a singular Request instance from the requests arraylist,
     * using its requestID as a search parameter.
     * 
     * @param requestID
     * @return
     */
    public Request getRequestByID(int requestID) {
        for (Request request : requests) {
            if (request.getRequestID() == requestID) {
                return request;
            }
        }
        return null;
    }

    // add a SchoolAdmin to the school (singular)
    /**
     * 
     * Method that adds a SchoolAdmin instance to the schoolAdmins arraylist.
     * 
     * @param schoolAdmin
     */
    public void addSchoolAdmin(SchoolAdmin schoolAdmin) {
        schoolAdmins.add(schoolAdmin);
    }

    // add a Request to the school (singular)
    /**
     * 
     * Method that adds a Request to the request arraylist of this School class.
     * 
     * @param request
     */
    public void addRequest(Request request) {
        requests.add(request);
    }

    // custom class methods
    /**
     * {@summary} This method returns a boolean value depending on wether there are
     * any NEW requests in the school request array list or not.
     * 
     * @return boolean
     */
    public boolean anyNewRequests() {
        for (Request request : requests) {
            if (request.getRequestStatus().equals("NEW")) {
                return true;
            }
        }
        return false;
    }

    // toString method
    /**
     * 
     * Method that returns a String representation of this School class.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "School | [School address: " + address + ", city: " + city + ", school ID: " + schoolID
                + ", school name: "
                + schoolName + "]";
    }

}
