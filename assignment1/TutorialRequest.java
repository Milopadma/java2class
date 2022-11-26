import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        this.requestID = requestID;
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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public String getRequestDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return requestDate.format(formatter);
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    // toString method
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
