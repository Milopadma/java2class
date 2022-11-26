import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Offer class that manages data of Offers, sends for the request class, and is
 * created by the Volunteer class.
 */
public class Offer {
    // class variables
    private LocalDateTime offerDate;
    private String remarks;
    private String offerStatus;

    // not included in the assignment but needed for offer-specific selection later
    // down the line
    private int offerID;

    // each offer instance is owned by a volunteer instance
    private Volunteer isOwnedBy;

    // class constructor
    /**
     * {@summary} Offer class constructor, takes in all the required fields.
     * 
     * @param offerDate   LocalDateTime
     * @param remarks     String
     * @param offerStatus String
     * @param isOwnedBy   Volunteer
     */
    public Offer(LocalDateTime offerDate, String remarks, String offerStatus, Volunteer isOwnedBy) {
        this.offerDate = offerDate;
        this.remarks = remarks;
        this.offerStatus = offerStatus;
        this.isOwnedBy = isOwnedBy;
        this.offerID = (int) (Math.random() * 9000) + 1000;
    }

    // class setter and getter methods
    /**
     * {@summary} Getter method for offerDate. Returns the offerDate.
     * 
     * @return int
     */
    public LocalDateTime getOfferDate() {
        return offerDate;
    }

    /**
     * {@summary} Setter method for offerDate. Takes in an int.
     * 
     * @param offerDate
     */
    public void setOfferDate(LocalDateTime offerDate) {
        this.offerDate = offerDate;
    }

    /**
     * {@summary} Getter method for remarks. Returns a String.
     * 
     * @return String
     */
    public String getOfferRemarks() {
        return remarks;
    }

    /**
     * {@summary} Setter method for remarks. Takes in a String.
     * 
     * @param remarks
     */
    public void setOfferRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * {@summary} Getter method for offerStatus. Returns a String.
     * 
     * @return String
     */
    public String getOfferStatus() {
        return offerStatus;
    }

    /**
     * {@summary} Setter method for offerStatus. Takes in a String.
     * 
     * @param offerStatus
     */
    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }

    /**
     * {@summary} Getter method for offerID. Returns a Volunteer object instance.
     * 
     * @return Volunteer
     */
    public Volunteer getIsOwnedBy() {
        return isOwnedBy;
    }

    /**
     * {@summary} Setter method for isOwnedBy. Takes in a Volunteer object instance.
     * 
     * @param isOwnedBy
     */
    public void setIsOwnedBy(Volunteer isOwnedBy) {
        this.isOwnedBy = isOwnedBy;
    }

    /**
     * {@summary} Getter method for offerID. Returns an int.
     * 
     * @return int
     */
    public int getOfferID() {
        return offerID;
    }

    /**
     * {@summary} Setter method for offerID. Takes in an int.
     * 
     * @param offerID
     */
    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    // toString method
    /**
     * {@summary} toString method for Offer class. Returns a String.
     */
    @Override
    public String toString() {
        return "Offer | [Offer date submitted: " + offerDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"))
                + ", offer status: " + offerStatus
                + ", offer ID: " + offerID
                + ", submitted by " + isOwnedBy.getFullname()
                + ", remarks: '" + remarks
                + "']";
    }

    // functional class method
    // to send an offer to a Request class
    // public void sendOffer(Request request) {
    // // this method sends an offer to a Request class
    // }
}
