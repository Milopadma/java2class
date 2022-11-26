
// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class manages the data of request instances, and its children classes.
 * Contains an array list of offers as it has a 1-many relationship with the
 * Offer class.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class Request {
    // this class receives from the Offer class and each School class has multiple
    // Requests
    // class variables
    private int requestID;
    private LocalDateTime requestDate;
    private String requestStatus;
    private String description;

    // class Request has a 1-many relationship with the Offer class
    private ArrayList<Offer> offers = new ArrayList<Offer>();

    // class Request also only has a 1-1 relationship with School class
    private School school;

    // class constructor
    /**
     * This is the class constructor for the Request class.
     * 
     * @param requestID
     * @param requestDate
     * @param requestStatus
     * @param description
     * @param school
     */
    public Request(int requestID, LocalDateTime requestDate, String requestStatus, String description, School school) {
        this.requestID = requestID;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
        this.description = description;
        this.school = school;
    }

    // class setter and getter methods
    /**
     * This method returns the request ID.
     * 
     * @return
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * This method sets the request ID.
     * 
     * @param requestID
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    /**
     * This method returns the request date.
     * 
     * @return
     */
    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    /**
     * This method returns the request date as a string, necessary in some cases
     * such as comparisons.
     * 
     * @return String
     * @param requestDate
     */
    public String getRequestDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return requestDate.format(formatter);
    }

    /**
     * This method sets the request date. Takes in a LocalDateTime param.
     * 
     * @param requestDate
     */
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * This method returns the request status.
     * 
     * @return String
     */
    public String getRequestStatus() {
        return requestStatus;
    }

    /**
     * This method sets the request status.
     * 
     * @param requestStatus
     */
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    /**
     * This method returns the request description.
     * 
     * @return String
     */
    public String getRequestDescription() {
        return description;
    }

    /**
     * This method sets the request description.
     * 
     * @param description
     */
    public void setRequestDescription(String description) {
        this.description = description;
    }

    /**
     * This method returns the offers of this request..
     * 
     * @return ArrayList
     */
    public ArrayList<Offer> getOffers() {
        return offers;
    }

    /**
     * This method sets the offers of this request.
     * 
     * @param offers
     */
    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    /**
     * This method adds an offer to the offers array list.
     * 
     * @param offer
     */
    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    /**
     * This method returns the school of this request.
     * 
     * @return School
     */
    public School getSchool() {
        return school;
    }

    /**
     * This method sets the school of this request.
     * 
     * @param school
     */
    public void setSchool(School school) {
        this.school = school;
    }

    // toString method
    /**
     * This method returns the request ID, request date, request status, and
     * description as a string.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Request | [Request description: " + description + ", request date: "
                + requestDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ", request ID: "
                + requestID
                + ", request status:  " + requestStatus + ", of school: " + school + "]";
    }

    /**
     * {@summary} Method of Request class, returns either "Resource" or "Tutorial"
     * request depending on the instance of the class.
     * 
     * @return String "Resource" or "Tutorial"
     */
    public String getRequestType() {

        if (this instanceof ResourceRequest) {
            return "Resource";
        } else if (this instanceof TutorialRequest) {
            return "Tutorial";
        } else {
            return "Unknown";
        }

    }
}
