
// I GUSTI BAGUS MILO PADMA WIJAYA //E2000426   
// this class is custom made for this project,
// not included and needed for the assignment 
// but just makes it easier to run tests on 
// all the features for the assignment
// Test Driven Development
import java.time.LocalDateTime;

public class TestRunner {
    private static SchoolHELP schoolHELP = new SchoolHELP();
    // private static User currentUser = null;

    private static LocalDateTime now = LocalDateTime.now();
    // current user admin
    private static SchoolAdmin currentUserAdmin = new SchoolAdmin(null, null, null, null, 0L, 0, null, null);

    // TESTCASE1: Register a new School (also includes creating and adding a new
    // SchoolAdmin)
    public static void TESTCASE1() {
        School school = new School("TestSchool", 123, "TestAddress", "TestCity");
        SchoolAdmin testSchoolAdmin = new SchoolAdmin("TestSchoolAdmin", "TestPassword", "TestFullName", "TestEmail",
                12345L, 123,
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
                12345L, 123,
                "TestPosition", school);
        schoolHELP.addUser(testSchoolAdmin);
        schoolHELP.addSchool(school);
        school.addSchoolAdmin(testSchoolAdmin);

        TutorialRequest tutorialRequest = new TutorialRequest(12345, now, "TestStatus", "TestDescription", 1212, 12, 1,
                290, school);

        ResourceRequest resourceRequestTest = new ResourceRequest(12345, now, "TestStatus", "TestDescription",
                "TestType",
                12, school);
        currentUserAdmin.getSchool().addRequest(tutorialRequest);
        currentUserAdmin.getSchool().addRequest(resourceRequestTest);

        // check if the request is added to the school
        if (currentUserAdmin.getSchool().getRequests().contains(tutorialRequest)
                && currentUserAdmin.getSchool().getRequests().contains(resourceRequestTest)) {
            System.out.println("TESTCASE2: PASSED");
        } else {
            System.out.println("TESTCASE2: FAILED");
        }
    }

    // TESTCASE3: Registering as a Volunteer
    public static void TESTCASE3() {
        Volunteer volunteer = new Volunteer("TestVolunteer", "TestPassword", "TestFullName", "TestEmail",
                (long) 12345123122L,
                123,
                "TestPosition");
        schoolHELP.addUser(volunteer);
        // check if the volunteer is added to the user list
        if (schoolHELP.getUsers().contains(volunteer)) {
            System.out.println("TESTCASE3: PASSED");
        } else {
            System.out.println("TESTCASE3: FAILED");
        }
    }

    // TESTCASE4: Allowing a Volunteer usersession to view requests that have been
    // submitted
    public static void TESTCASE4() {
        School school = new School("TestSchool", 123, "TestAddress", "TestCity");
        SchoolAdmin testSchoolAdmin = new SchoolAdmin("TestSchoolAdmin", "TestPassword", "TestFullName", "TestEmail",
                12345L, 123,
                "TestPosition", school);
        schoolHELP.addUser(testSchoolAdmin);
        schoolHELP.addSchool(school);
        school.addSchoolAdmin(testSchoolAdmin);

        TutorialRequest tutorialRequest = new TutorialRequest(12345, now, "TestStatus", "TestDescription", 1212, 12, 1,
                290, school);

        ResourceRequest resourceRequestTest = new ResourceRequest(12345, now, "TestStatus", "TestDescription",
                "TestType",
                12, school);
        currentUserAdmin.getSchool().addRequest(tutorialRequest);
        currentUserAdmin.getSchool().addRequest(resourceRequestTest);

        Volunteer volunteer = new Volunteer("TestVolunteer", "TestPassword", "TestFullName", "TestEmail", 12345123122L,
                123,
                "TestPosition");
        schoolHELP.addUser(volunteer);

        // check if the volunteer is added to the user list, and if the request is added
        // to the school
        if (schoolHELP.getUsers().contains(volunteer)
                && currentUserAdmin.getSchool().getRequests().contains(tutorialRequest)
                && currentUserAdmin.getSchool().getRequests().contains(resourceRequestTest)) {
            System.out.println("TESTCASE4: PASSED");
        } else {
            System.out.println("TESTCASE4: FAILED");
        }
    }

    // TESTCASE5: Submitting an Offer
    public static void TESTCASE5() {
        School school = new School("TestSchool", 123, "TestAddress", "TestCity");
        SchoolAdmin testSchoolAdmin = new SchoolAdmin("TestSchoolAdmin", "TestPassword", "TestFullName", "TestEmail",
                12345L, 123,
                "TestPosition", school);
        schoolHELP.addUser(testSchoolAdmin);
        schoolHELP.addSchool(school);
        school.addSchoolAdmin(testSchoolAdmin);

        TutorialRequest tutorialRequest = new TutorialRequest(12345, now, "TestStatus", "TestDescription", 1212, 12, 1,
                290, school);

        ResourceRequest resourceRequestTest = new ResourceRequest(12345, now, "TestStatus", "TestDescription",
                "TestType",
                12, school);
        currentUserAdmin.getSchool().addRequest(tutorialRequest);
        currentUserAdmin.getSchool().addRequest(resourceRequestTest);

        Volunteer volunteer = new Volunteer("TestVolunteer", "TestPassword", "TestFullName", "TestEmail", 1234123125L,
                123,
                "TestPosition");
        schoolHELP.addUser(volunteer);
        LocalDateTime offerNowTime = LocalDateTime.now();
        Offer offer = new Offer(offerNowTime, "testRemarks", "testStatus", volunteer);
        volunteer.addOffer(offer);

        // check if the offer is added to the volunteer
        if (volunteer.getOffers().contains(offer)) {
            System.out.println("TESTCASE5: PASSED");
        } else {
            System.out.println("TESTCASE5: FAILED");
        }
    }

    // TESTCASE6: Reviewing Offers, to allow school admins to review offers and
    // change the status of the offer
    public static void TESTCASE6() {
        School school = new School("TestSchool", 123, "TestAddress", "TestCity");
        SchoolAdmin testSchoolAdmin = new SchoolAdmin("TestSchoolAdmin", "TestPassword", "TestFullName", "TestEmail",
                12345L, 123,
                "TestPosition", school);
        schoolHELP.addUser(testSchoolAdmin);
        schoolHELP.addSchool(school);
        school.addSchoolAdmin(testSchoolAdmin);

        TutorialRequest tutorialRequest = new TutorialRequest(12345, now, "TestStatus", "TestDescription", 1212, 12, 1,
                290, school);

        ResourceRequest resourceRequestTest = new ResourceRequest(12345, now, "TestStatus", "TestDescription",
                "TestType",
                12, school);
        currentUserAdmin.getSchool().addRequest(tutorialRequest);
        currentUserAdmin.getSchool().addRequest(resourceRequestTest);

        Volunteer volunteer = new Volunteer("TestVolunteer", "TestPassword", "TestFullName", "TestEmail", 12342131325L,
                123,
                "TestPosition");
        schoolHELP.addUser(volunteer);

        LocalDateTime offerNowTime = LocalDateTime.now();
        Offer offer = new Offer(offerNowTime, "testRemarks", "testStatus", volunteer);
        // add this offer to the volunteer
        volunteer.addOffer(offer);

        // check if the offer is added to the volunteer
        if (volunteer.getOffers().contains(offer)) {
            System.out.println("TESTCASE6: PASSED");
            for (User user : schoolHELP.getUsers()) {
                if (user instanceof Volunteer) {
                    for (Offer offer1 : ((Volunteer) user).getOffers()) {
                        System.out.println(offer1);
                    }
                }
            }
        } else {
            System.out.println("TESTCASE6: FAILED");
        }

    }

    public static void main(String[] args) {
        // test for first use case, which is Registering a New School
        TESTCASE1();
        // test for the second use case, which is Submitting a Request
        TESTCASE2();
        // test for the third use case, which is Registering as a Volunteer
        TESTCASE3();
        // test for the fourth use case, which is Allowing a Volunteer usersession to
        // view requests that have been submitted
        TESTCASE4();
        // test for the fifth use case, which is Submitting an Offer
        TESTCASE5();
        // test for the sixth use case, which is Reviewing Offers
        TESTCASE6();

    }
}
