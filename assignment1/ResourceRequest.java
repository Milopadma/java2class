
// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class manages the data of resourceRequest instances. Child of Request
 * class.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class ResourceRequest extends Request {
    // this class manages ResourceRequest data,
    // really does
    // class variables
    private String resourceType;
    private int numRequired;

    // each tutorial request has a 1-1 relationship with a School class
    private School school;

    // to show the request status
    private String requestStatus;

    // to show the request date
    private LocalDateTime requestDate;

    // to show this request ID
    private int requestID;

    // class constructor
    /**
     * 
     * The class contructor for the ResourceRequest class.
     * 
     * @param requestID
     * @param requestDate
     * @param requestStatus
     * @param description
     * @param resourceType
     * @param numRequired
     * @param school
     */
    public ResourceRequest(int requestID, LocalDateTime requestDate, String requestStatus, String description,
            String resourceType, int numRequired, School school) {
        super(requestID, requestDate, requestStatus, description, school);
        this.resourceType = resourceType;
        this.numRequired = numRequired;
        this.school = school;
        this.requestStatus = requestStatus;
        this.requestDate = requestDate;
        this.requestID = requestID;
    }

    // class setter and getter methods
    /**
     * This method returns the resource type.
     * 
     * @return String
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * This method sets the resource type.
     * 
     * @param resourceType
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * This method returns the number of resources required.
     * 
     * @return int
     */
    public int getNumRequired() {
        return numRequired;
    }

    /**
     * This method sets the number of resources required.
     * 
     * @param numRequired
     */
    public void setNumRequired(int numRequired) {
        this.numRequired = numRequired;
    }

    /**
     * This method returns the school.
     * 
     * @return School
     */
    public School getSchool() {
        return school;
    }

    /**
     * This method sets the school.
     * 
     * @param school
     */
    public void setSchool(School school) {
        this.school = school;
    }

    /**
     * This method sets the request status.
     * 
     * @param String
     */
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
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
     * This method sets the request date.
     * 
     * @param LocalDateTime
     */
    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    /**
     * This method returns the request date as a string.
     * 
     * @return String
     */
    public String getRequestDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return requestDate.format(formatter);
    }

    /**
     * This method sets the request date.
     * 
     * @param LocalDateTime
     */
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * This method returns the request ID.
     * 
     * @return int
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * This method sets the request ID.
     * 
     * @param int
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    // toString method
    /**
     * This method returns the string representation of the ResourceRequest class.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Resource Request | [Request Status: " + requestStatus
                + ", request date: " + requestDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + ", of school: " + school.getSchoolName()
                + ", request ID: " + requestID
                + ", number of resources required: " + numRequired
                + ", resource type: " + resourceType
                + "]";
    }
}
