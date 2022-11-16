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
        this.subjects.add(subject);
    }

    // allSubject method that returns a string containing all the enrolled subjects,
    // one per line
    public String allSubjects() {
        String allSubjects = "";
        for (Subject subject : this.subjects) {
            allSubjects += subject.toString() + "\n";
        }
        return allSubjects;
    }

    // numPaidSubjects method that returns a number of FullFeeSubjects
    public int numPaidSubjects() {
        int count = 0;
        for (Subject subject : this.subjects) {
            if (subject instanceof FullFeeSubject) { // if the subject is a FullFeeSubject
                count++;
            }
        }
        return count;
    }

    // totalCostPaidSubjects method that returns the total number of fees paid for
    // FullFeeSubjects
    public double totalCostPaidSubjects() {
        double totalCost = 0;
        for (Subject subject : this.subjects) {
            if (subject instanceof FullFeeSubject) { // if the subject is a FullFeeSubject
                totalCost += ((FullFeeSubject) subject).getCost(); // call subjects' getCost
            }
        }
        return totalCost;
    }

    // subjectSearch method that accepts a representing name of a subject and
    // returns the subject that matches it
    public Subject subjectSearch(String name) {
        for (Subject subject : this.subjects) {
            // return the subject if it is found
            if (subject.getName().equals(name)) {
                return subject;
            }
        }
        // if its not found, return null
        return null;
    }

    // subjectCodeSearch method that takes the first three characters of a subject
    // code and returns a list that contains all the subjects that matches
    public ArrayList<Subject> subjectCodeSearch(String code) {
        // init the collection that will be returned
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        for (Subject subject : this.subjects) {
            // if the subject code starts with the code passed in, add it to the list
            if (subject.getCode().startsWith(code)) {
                subjects.add(subject);
            }
        }
        // return the collection of subjects
        return subjects;
    }
}
