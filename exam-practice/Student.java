
public class Student {
    // fields
    private String name;
    private int studentID;
    private double score;

    // constructor
    public Student(String name, int studentID, double score) {
        this.name = name;
        this.studentID = studentID;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getStudentID() {
        return studentID;
    }

    public double getScore() {
        return score;
    }

}
