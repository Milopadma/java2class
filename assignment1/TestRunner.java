// I GUSTI BAGUS MILO PADMA WIJAYA //E2000426   
// this class is custom made for this project,
// not included and needed for the assignment 
// but just makes it easier to run tests on 
// all the features for the assignment
// Test Driven Development time???????????????
public class TestRunner {
    private static SchoolHELP schoolHELP = new SchoolHELP();
    // private static User currentUser = null;

    // TESTCASE1: Register a new School (also includes creating and adding a new
    // SchoolAdmin)
    public static void TESTCASE1() {
        // create a new school
        School school = new School("TestSchool", 123, "TestAddress", "TestCity");
        // create a new school admin
        SchoolAdmin testSchoolAdmin = new SchoolAdmin("TestSchoolAdmin", "TestPassword", "TestFullName", "TestEmail",
                12345, 123,
                "TestPosition", school);
        // add the school admin to the schoolHELP users list
        schoolHELP.addUser(testSchoolAdmin);
        // add the school to the schoolHELP schools list
        schoolHELP.addSchool(school);
        // add the school admin to the school admins list
        school.addSchoolAdmin(testSchoolAdmin);
        // check if the school is added, and if the school admin is added to the school
        if (schoolHELP.getSchools().contains(school) && school.getSchoolAdmins().contains(testSchoolAdmin)) {
            System.out.println("TESTCASE1: PASSED");
        } else {
            System.out.println("TESTCASE1: FAILED");
        }
    }


    public static void main(String[] args) {
        // test for first use case, which is Registering a New School
        TESTCASE1();

    }
}
