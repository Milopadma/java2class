
// I GUSTI BAGUS MILO PADMA WIJAYA // E2000426
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class manages the data of tutorialRequest instances. Child of Request
 * class.
 * 
 * 
 * @author I Gusti Bagus Milo Padma Wijaya
 * 
 */
public class TutorialRequest extends Request {
    // this class handles all things about the tutorial request and its data

    // class variables
    private int proposedDate;
    private int proposedTime;
    private int studentLevel;
    private int numStudents;

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
     * The class contructor for the TutorialRequest class.
     * 
     * @param requestID
     * @param requestDate
     * @param requestStatus
     * @param description
     * @param proposedDate
     * @param proposedTime
     * @param studentLevel
     * @param numStudents
     * @param school
     */
    public TutorialRequest(int requestID, LocalDateTime requestDate, String requestStatus, String description,
            int proposedDate,
            int proposedTime, int studentLevel, int numStudents, School school) {
        super(requestID, requestDate, requestStatus, description, school);
        this.proposedDate = proposedDate;
        this.proposedTime = proposedTime;
        this.studentLevel = studentLevel;
        this.numStudents = numStudents;
        this.requestStatus = requestStatus;
        this.school = school;
        this.requestDate = requestDate;
        this.requestID = requestIDvalidator(requestID);
    }

    /**
     * 
     * Method to validate the request ID.
     * 
     * @param requestIDtoValidate
     * @return int
     */
    private int requestIDvalidator(int requestIDtoValidate) {
        // to make sure that the requestID is a 6 digit number
        // if the requestID is not a 6 digit number, this will regenerate a new
        // number
        if (requestIDtoValidate < 100000 || requestIDtoValidate > 999999) {
            requestIDtoValidate = (int) (Math.random() * 900000) + 100000;
        }
        return requestIDtoValidate;
    }

    // class setter and getter methods
    /**
     * This method returns the proposed date.
     * 
     * @return int
     */
    public int getProposedDate() {
        return proposedDate;
    }

    /**
     * This method sets the proposed date.
     * 
     * @param proposedDate
     */
    public void setProposedDate(int proposedDate) {
        this.proposedDate = proposedDate;
    }

    /**
     * This method returns the proposed time.
     * 
     * @return int
     */
    public int getProposedTime() {
        return proposedTime;
    }

    /**
     * This method sets the proposed time.
     * 
     * @param proposedTime
     */
    public void setProposedTime(int proposedTime) {
        this.proposedTime = proposedTime;
    }

    /**
     * This method returns the student level.
     * 
     * @return int
     */
    public int getStudentLevel() {
        return studentLevel;
    }

    /**
     * This method sets the student level.
     * 
     * @param studentLevel
     */
    public void setStudentLevel(int studentLevel) {
        this.studentLevel = studentLevel;
    }

    /**
     * This method returns the number of students.
     * 
     * @return int
     */
    public int getNumStudents() {
        return numStudents;
    }

    /**
     * This method sets the number of students.
     * 
     * @param numStudents
     */
    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
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
     * This method returns the request date.
     * 
     * @return LocalDateTime
     */
    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    /**
     * This method returns the request date as a string.
     * 
     * @param String
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
     * This method returns the string representation of the TutorialRequest class.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Tutorial Request | [Request Status: " + requestStatus
                + ", request date: " + requestDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + ", of school: " + school.getSchoolName()
                + ", request ID: " + requestID
                + ", number of students: " + numStudents
                + ", student level: " + studentLevel
                + ", proposed date: " + proposedDate
                + ", proposed time: " + proposedTime
                + "]";
    }
}
