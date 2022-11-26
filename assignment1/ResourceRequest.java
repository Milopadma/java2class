import java.time.LocalDateTime;

public class ResourceRequest extends Request {
    // this class manages ResourceRequest data,
    // really does
    // class variables
    private String resourceType;
    private int numRequired;

    // each tutorial request has a 1-1 relationship with a School class
    private School school;

    // class constructor
    public ResourceRequest(int requestID, LocalDateTime requestDate, String requestStatus, String desription,
            String resourceType, int numRequired, School school) {
        super(requestID, requestDate, requestStatus, desription, school);
        this.resourceType = resourceType;
        this.numRequired = numRequired;
        this.school = school;
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
        return "Resource Request | [Number of resources required: " + numRequired + ", resource type: " + resourceType
                + ", of school: " + school + "]";
    }
}
