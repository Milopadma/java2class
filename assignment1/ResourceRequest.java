import java.time.LocalDateTime;

public class ResourceRequest extends Request{
    // this class manages ResourceRequest data, !TODO not sure what this class really does
    // class variables
    private String resourceType;
    private int numRequired;

    // class constructor
    public ResourceRequest(int requestID, LocalDateTime requestDate, String requestStatus, String desription, String resourceType, int numRequired){
        super(requestID, requestDate, requestStatus, desription);
        this.resourceType = resourceType;
        this.numRequired = numRequired;
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

    // toString method
    @Override
    public String toString() {
        return "ResourceRequest [numRequired=" + numRequired + ", resourceType=" + resourceType + "]";
    }
}
