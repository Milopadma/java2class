
// imports
import java.io.*;
import java.util.*;

public class Student {
    // class headers
    private String name;
    private int studentNumber;

    // class contructors (with args)
    public Student(String name) {
        this.name = name;
        // initialize the collection of Subjects
        this.subjects = new ArrayList<Subject>();
        // "student number is automatically generated"
        this.studentNumber = (int) (Math.random() * 1000000);
    }

    // addSubject that adds the passed in Subject to the collection of Subjects
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    // allSubject method that returns a string containing all the enrolled subjects,
    // one per line
    public String allSubjects() {
        return this.subjects.stream().map(Subject::toString).collect(Collectors.joining("\n"));
    }

    // numPaidSubjects method that returns a number of FullFeeSubjects
    public int numPaidSubjects() {
        return (int) this.subjects.stream().filter(subject -> subject instanceof FullFeeSubject)
                .count();
    }

    // totalCostPaidSubjects method that returns the total number of fees paid for
    // FullFeeSubjects
    public double totalCostPaidSubjects() {
        return this.subjects.stream().filter(subject -> subject instanceof FullFeeSubject)
                .mapToDouble(subject -> ((FullFeeSubject) subject).getCost()).sum();
    }

    // subjectSearch method that accepts a representing name of a subject and
    // returns the subject that matches it
    public Subject subjectSearch(String name) {
        return this.subjects.stream().filter(subject -> subject.getName().equals(name)).findFirst()
                .orElse(null);
    }

    // subjectCodeSearch method that takes the first three characters of a subject
    // code and returns a list that contains all the subjects that matches
    public ArrayList<Subject> subjectCodeSearch(String code) {
        return this.subjects.stream().filter(subject -> subject.getCode().startsWith(code))
                .collect(Collectors.toList());
    }
}
