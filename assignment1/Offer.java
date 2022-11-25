public class Offer {
    // this class manages Offers data, sends for the Request class and is made by
    // the Volunteer class
    // class variables
    private int offerDate;
    private String remarks;
    private String offerStatus;

    // not included in the assignment but needed for offer-specific selection later
    // down the line
    private int offerID;

    // each offer instance is owned by a volunteer instance
    private Volunteer isOwnedBy;

    // class constructor
    public Offer(int offerDate, String remarks, String offerStatus, Volunteer isOwnedBy) {
        this.offerDate = offerDate;
        this.remarks = remarks;
        this.offerStatus = offerStatus;
        this.isOwnedBy = isOwnedBy;
        this.offerID = (int) (Math.random() * 9000) + 1000;
    }

    // class setter and getter methods
    public int getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(int offerDate) {
        this.offerDate = offerDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Volunteer getIsOwnedBy() {
        return isOwnedBy;
    }

    public void setIsOwnedBy(Volunteer isOwnedBy) {
        this.isOwnedBy = isOwnedBy;
    }

    public int getOfferID() {
        return offerID;
    }

    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    // toString method
    @Override
    public String toString() {
        return "Offer [offerDate: " + offerDate + ", offerStatus: " + offerStatus + ", remarks: " + remarks
                + ", offerID: " + offerID + "]";
    }

    // functional class method
    // to send an offer to a Request class
    // public void sendOffer(Request request) {
    // // this method sends an offer to a Request class
    // }
}
