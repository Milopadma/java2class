import java.util.ArrayList;

// School class
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
    public School(String schoolName, int schoolID, String address, String city) {
        this.schoolName = schoolName;
        this.schoolID = schoolID;
        this.address = address;
        this.city = city;
    }

    // class constructor without args
    public School() {
        this.schoolName = "default school";
        this.schoolID = 000;
        this.address = "default address";
        this.city = "default city";
    }

    // class setter and getter methods
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // setter and getters for the ArrayLists
    public ArrayList<SchoolAdmin> getSchoolAdmins() {
        return schoolAdmins;
    }

    public void setSchoolAdmins(ArrayList<SchoolAdmin> schoolAdmins) {
        this.schoolAdmins = schoolAdmins;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    // class methods

    // add a SchoolAdmin to the school (singular)
    public void addSchoolAdmin(SchoolAdmin schoolAdmin) {
        schoolAdmins.add(schoolAdmin);
    }

    // add a Request to the school (singular)
    public void addRequest(Request request) {
        requests.add(request);
    }

    // toString method
    @Override
    public String toString() {
        return "School [address: " + address + ", city: " + city + ", schoolID: " + schoolID + ", schoolName: "
                + schoolName + "]";
    }
}
