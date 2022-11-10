public class Offer{
    // this class manages Offers data, sends for the Request class and is made by the Volunteer class
    // class variables
    private int offerDate;
    private String remarks;
    private String offerStatus;

    // class constructor
    public Offer(int offerDate, String remarks, String offerStatus){
        this.offerDate = offerDate;
        this.remarks = remarks;
        this.offerStatus = offerStatus;
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

    // toString method
    @Override
    public String toString() {
        return "Offer [offerDate=" + offerDate + ", offerStatus=" + offerStatus + ", remarks=" + remarks + "]";
    }

    // functional class methods
    // to send an offer to a Request class
    public void sendOffer(Request request){
        // this method sends an offer to a Request class
    }
}
