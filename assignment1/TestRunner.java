
// I GUSTI BAGUS MILO PADMA WIJAYA //E2000426   
// this class is custom made for this project,
// not included and needed for the assignment 
// but just makes it easier to run tests on 
// all the features for the assignment
// Test Driven Development time???????????????
import java.time.LocalDateTime;

public class TestRunner {
    private static SchoolHELP schoolHELP = new SchoolHELP();
    // private static User currentUser = null;

    private static LocalDateTime now = LocalDateTime.now();
    // current user admin
    private static SchoolAdmin currentUserAdmin = new SchoolAdmin(null, null, null, null, 0, 0, null, null);

    // TESTCASE1: Register a new School (also includes creating and adding a new
    // SchoolAdmin)
    public static void TESTCASE1() {
        School school = new School("TestSchool", 123, "TestAddress", "TestCity");
        SchoolAdmin testSchoolAdmin = new SchoolAdmin("TestSchoolAdmin", "TestPassword", "TestFullName", "TestEmail",
                12345, 123,
                "TestPosition", school);
        currentUserAdmin = testSchoolAdmin;
        schoolHELP.addUser(testSchoolAdmin);
        schoolHELP.addSchool(school);
        school.addSchoolAdmin(testSchoolAdmin);
        // check if the school is added, and if the school admin is added to the school
        if (schoolHELP.getSchools().contains(school) && school.getSchoolAdmins().contains(testSchoolAdmin)) {
            System.out.println("TESTCASE1: PASSED");
        } else {
            System.out.println("TESTCASE1: FAILED");
        }
    }

    // TESTCASE2: Submitting a new Request
    public static void TESTCASE2() {
        School school = new School("TestSchool", 123, "TestAddress", "TestCity");
        SchoolAdmin testSchoolAdmin = new SchoolAdmin("TestSchoolAdmin", "TestPassword", "TestFullName", "TestEmail",
                12345, 123,
                "TestPosition", school);
        schoolHELP.addUser(testSchoolAdmin);
        schoolHELP.addSchool(school);
        school.addSchoolAdmin(testSchoolAdmin);

        TutorialRequest tutorialRequest = new TutorialRequest(12345, now, "TestStatus", "TestDescription", 1212, 12, 1,
                290);

        ResourceRequest resourceRequestTest = new ResourceRequest(12345, now, "TestStatus", "TestDescription", "TestType",
                12);
        currentUserAdmin.getSchool().addRequest(tutorialRequest);
        currentUserAdmin.getSchool().addRequest(resourceRequestTest);
        
        // check if the request is added to the school
        if (currentUserAdmin.getSchool().getRequests().contains(tutorialRequest) && currentUserAdmin.getSchool().getRequests().contains(resourceRequestTest)) {
            System.out.println("TESTCASE2: PASSED");
        } else {
            System.out.println("TESTCASE2: FAILED");
        }
    }

    public static void main(String[] args) {
        // test for first use case, which is Registering a New School
        TESTCASE1();
        // test for the second use case, which is Submitting a Request
        TESTCASE2();

    }
}
