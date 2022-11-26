import java.util.ArrayList;

public class Volunteer extends User {
    // this class manages Volunteer data
    // class variables
    private int dateOfBirth;
    private String occupation;

    // each volunteer can have multiple offers
    private ArrayList<Offer> offers = new ArrayList<Offer>();

    // class constructor
    public Volunteer(String username, String password, String fullname, String email, Long phone, int dateOfBirth,
            String occupation) {
        super(username, password, fullname, email, phone);
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
    }

    // class setter and getter methods
    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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
        return "Volunteer | [Date of birth: " + dateOfBirth + ", occupation: " + occupation + "]";
    }
}
