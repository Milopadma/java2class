import java.util.ArrayList;

public class Volunteer extends User{
    //this class manages SchoolAdmin data 
    // class variables
    private int dateOfBirth;
    private String occupation;

    // class constructor
    public Volunteer(String username, String password, String fullname, String email, int phone, int dateOfBirth, String occupation){
        super(username, password, fullname, email, phone);
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
    }
    // class setter and getter methods
    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    // toString method
    @Override
    public String toString() {
        return "Volunteer [dateOfBirth=" + dateOfBirth + ", occupation=" + occupation + "]";
    }
    public ArrayList viewAllRequests(ArrayList<School> schools) {
        ArrayList<Request> allRequests = new ArrayList<Request>();
        for (School school : schools) {
            allRequests.addAll(school.getRequests());
        }
        return allRequests;
    }
}
