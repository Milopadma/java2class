
// I GUSTI BAGUS MILO PADMA WIJAYA // e2000426
// imports
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Student {
    // class headers
    private String name;
    private int studentNumber;
    private ArrayList<Subject> subjects;

    // class contructors (with args)
    public Student(String name) {
        this.name = name;
        // initialize the collection of Subjects
        this.subjects = new ArrayList<Subject>();
        // "student number is automatically generated"
        this.studentNumber = (int) (Math.random() * 1000000);
    }

    // class getters and setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentNumber() {
        return this.studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public ArrayList<Subject> getSubjects() {
        return this.subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    // class methods
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
        return (ArrayList<Subject>) this.subjects.stream().filter(subject -> subject.getCode().startsWith(code))
                .collect(Collectors.toList());
    }

    // sortSubjects method that sorts the subjects in the collection by name, no
    // sorting, or according to subject type followed by subjetc name.
    public void sortSubjects(String type) {
        if (type.equals("name")) {
            this.subjects.sort(Comparator.comparing(Subject::getName));
        } else if (type.equals("type")) {
            this.subjects.sort(Comparator.comparing(Subject::getClass).thenComparing(Subject::getName));
        } else if (type.equals("original")) {
            // do nothing, literally...
        } else {
            // catch edge case where nothing is passed in or an invalid string is passed in
            System.out.println("Invalid type");
        }
    }
}
