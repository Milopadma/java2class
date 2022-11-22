// I GUSTI BAGUS MILO PADMA WIJAYA // e2000426
// this Main class is used to test the class of the program
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<Student>();

        students.add(new Student("I Gusti Bagus Milo Padma Wijaya"));
        students.add(new Student("I Gusti Bagus Agung Wijaya"));
        students.add(new Student("I Gusti Bagus Agung Wijaya"));

        System.out.println("Students:");
        for (Student student : students) {
            System.out.println(student.getName());
        }

        // 1. create a student object
        Student miloStudent = new Student("I Gusti Bagus Milo Padma Wijaya");

        // 2. the two different subject objects
        // create a subject object (normal)
        Subject javaSubject = new Subject("JAVA 2", "BIT203", 20);

        // create a full fee subject object (the fullFeeSubject)
        FullFeeSubject javaFullFeeSubject = new FullFeeSubject("JAVA 2", "BIT203", 20, 100);

        // 3. adding the subjects objects to the student object
        // add the subject to the student
        miloStudent.addSubject(javaSubject);

        // add the full fee subject to the student
        miloStudent.addSubject(javaFullFeeSubject);

        // adding more Subject instances to the student
        miloStudent.addSubject(new Subject("Digital Photography", "CSW201", 10));
        miloStudent.addSubject(new Subject("Enterprise Architecture", "BIT308", 40));
        miloStudent.addSubject(new Subject("Networking", "BIT204", 20));

        // 4. displaying the number of FullFeeSubject enrolled by the student
        System.out.println("Number of Full Fee Subjects: " + miloStudent.numPaidSubjects());

        // 5. displaying the details in which the subject codes matches "BIT"
        System.out.println("Subject Code Search: " + miloStudent.subjectCodeSearch("BIT"));
    }
}
