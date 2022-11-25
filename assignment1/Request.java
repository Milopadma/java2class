import java.time.LocalDateTime;
import java.util.ArrayList;

public class Request {
    // this class receives from the Offer class and each School class has multiple Requests
    // class variables
    private int requestID;
    private LocalDateTime requestDate;
    private String requestStatus;
    private String description;

    // class Request has a 1-many relationship with the Offer class
    private ArrayList<Offer> offers = new ArrayList<Offer>();

    // class constructor
    public Request(int requestID, LocalDateTime requestDate, String requestStatus, String description){
        this.requestID = requestID;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
        this.description = description;
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

    public void setDescription(String description) {
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

    // toString method
    @Override
    public String toString() {
        return "Request [description=" + description + ", requestDate=" + requestDate + ", requestID=" + requestID
                + ", requestStatus=" + requestStatus + "]";
    }
}

