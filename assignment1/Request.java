public class Request {
    // this class receives from the Offer class and each School class has multiple Requests
    // class variables
    private int requestID;
    private int requestDate;
    private String requestStatus;
    private String description;

    // class constructor
    public Request(int requestID, int requestDate, String requestStatus, String description){
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

    public int getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(int requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method
    @Override
    public String toString() {
        return "Request [description=" + description + ", requestDate=" + requestDate + ", requestID=" + requestID
                + ", requestStatus=" + requestStatus + "]";
    }
}

