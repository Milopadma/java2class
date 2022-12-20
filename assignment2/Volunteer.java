
// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.util.ArrayList;

/**
 * 
 * Volunteer class manages the data of Volunteer instances. Contains arraylists
 * of offers that this Volunteer instance has made.
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class Volunteer extends User {
    // this class manages Volunteer data
    // class variables
    private int dateOfBirth;
    private String occupation;

    // each volunteer can have multiple offers
    private ArrayList<Offer> offers = new ArrayList<Offer>();

    // class constructor
    /**
     * 
     * Volunteer class constructor. With arguments.
     * 
     * @param username
     * @param password
     * @param fullname
     * @param email
     * @param phone
     * @param dateOfBirth
     * @param occupation
     */
    public Volunteer(String username, String password, String fullname, String email, Long phone, int dateOfBirth,
            String occupation) {
        super(username, password, fullname, email, phone);
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
    }

    // class setter and getter methods
    /**
     * 
     * Method that returns the dateOfBirth of this Volunteer class.
     * 
     * @return int
     */
    public int getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * 
     * Method that sets the dateOfBirth of this Volunteer class.
     * 
     * @param dateOfBirth
     */
    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * 
     * Method that returns the occupation of this Volunteer class.
     * 
     * @return String
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * 
     * Method that sets the occupation of this Volunteer class.
     * 
     * @param occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * 
     * Method that returns the offers of this Volunteer class.
     * 
     * @return ArrayList<Offer>
     */
    public ArrayList<Offer> getOffers() {
        return offers;
    }

    /**
     * 
     * Method that sets the offers of this Volunteer class.
     * 
     * @param offers
     */
    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    /**
     * 
     * Method that adds an offer to the offers arraylist of this Volunteer class.
     * 
     * @param offer
     */
    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    // toString method
    /**
     * 
     * Method that returns the string representation of this Volunteer class.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Volunteer | [Date of birth: " + dateOfBirth + ", occupation: " + occupation + "]";
    }
}
