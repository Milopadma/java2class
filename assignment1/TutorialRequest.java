import java.time.LocalDateTime;

public class TutorialRequest extends Request {
    // this class handles all things about the tutorial request and its data

    // class variables
    private int proposedDate;
    private int proposedTime;
    private int studentLevel;
    private int numStudents;

    // class constructor
    public TutorialRequest(int requestID, LocalDateTime requestDate, String requestStatus, String desription,
            int proposedDate,
            int proposedTime, int studentLevel, int numStudents, School school) {
        super(requestID, requestDate, requestStatus, desription, school);
        this.proposedDate = proposedDate;
        this.proposedTime = proposedTime;
        this.studentLevel = studentLevel;
        this.numStudents = numStudents;
    }

    // class setter and getter methods
    public int getProposedDate() {
        return proposedDate;
    }

    public void setProposedDate(int proposedDate) {
        this.proposedDate = proposedDate;
    }

    public int getProposedTime() {
        return proposedTime;
    }

    public void setProposedTime(int proposedTime) {
        this.proposedTime = proposedTime;
    }

    public int getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(int studentLevel) {
        this.studentLevel = studentLevel;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    // toString method
    @Override
    public String toString() {
        return "TutorialRequest [numStudents=" + numStudents + ", proposedDate=" + proposedDate + ", proposedTime="
                + proposedTime + ", studentLevel=" + studentLevel + "]";
    }
}
