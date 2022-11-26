import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    public Request(int requestID, LocalDateTime requestDate, String requestStatus, String description, School school) {
        this.requestID = requestID;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
        this.description = description;
        this.school = school;
    }

    // class setter and getter methods
    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestDescription() {
        return description;
    }

    public void setRequestDescription(String description) {
        this.description = description;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    // toString method
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
