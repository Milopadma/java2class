import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public ResourceRequest(int requestID, LocalDateTime requestDate, String requestStatus, String desription,
            String resourceType, int numRequired, School school) {
        super(requestID, requestDate, requestStatus, desription, school);
        this.resourceType = resourceType;
        this.numRequired = numRequired;
        this.school = school;
        this.requestStatus = requestStatus;
        this.requestDate = requestDate;
        this.requestID = requestID;
    }

    // class setter and getter methods
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getNumRequired() {
        return numRequired;
    }

    public void setNumRequired(int numRequired) {
        this.numRequired = numRequired;
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
        return "Resource Request | [Request Status: " + requestStatus
                + ", request date: " + requestDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"))
                + ", of school: " + school.getSchoolName()
                + ", request ID: " + requestID
                + ", number of resources required: " + numRequired
                + ", resource type: " + resourceType
                + "]";
    }
}
