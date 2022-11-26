
// I GUSTI BAGUS MILO PADMA WIJAYA //E2000426
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * {@summary} This class manages instances of User and School classes, both in
 * ArrayLists.
 *
 */
public class SchoolHELP {
    // class variables
    private static ArrayList<User> users = new ArrayList<User>();
    private static ArrayList<School> schools = new ArrayList<School>();

    // class constructor
    public SchoolHELP() {
        // default ArrayList constructor with a single default school
        schools.add(new School("HELP", 1, "Jalan Damansara", "Kuala Lumpur"));
        // default ArrayList constructor with a single default SchoolAdmin user
        users.add(
                new SchoolAdmin("admin", "admin", "MILO PADMA", "defaultadmin@milopadma.com", (long) 628113344512L, 001,
                        "SchoolHELPAdmin", schools.get(0)));
    }

    // class setter and getter methods
    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        SchoolHELP.users = users;
    }

    // singular user
    public User getUser(String adminUsername, String adminPassword) {
        for (User user : users) {
            if (user.getUsername().equals(adminUsername)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<School> getSchools() {
        return schools;
    }

    public void setSchools(ArrayList<School> schools) {
        SchoolHELP.schools = schools;
    }

    // class methods
    public void addUser(User user) {
        users.add(user);
    }

    public void addSchool(School school) {
        schools.add(school);
    }

    /**
     * {@summary} A method of SchoolHELP, gets the school by school name string, and
     * returns the school object if it exists.
     * 
     * @param schoolName
     * @return
     */
    public School getSchool(String schoolName) {
        for (School school : schools) {
            if (school.getSchoolName().equals(schoolName)) {
                return school;
            }
        }
        return null;
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void removeSchool(School school) {
        schools.remove(school);
    }

    /**
     * {@summary} A method of SchoolHELP, gets all the requests of all the schools
     * in the Array List.
     * 
     * @return ArrayList of type Request
     */
    public ArrayList<Request> getAllRequests() {
        ArrayList<Request> allRequests = new ArrayList<Request>();
        for (School school : schools) {
            allRequests.addAll(school.getRequests());
        }
        return allRequests;

    }

    // functional class methods

    // to check if the user is an admin in the user list array or not
    /**
     * @param adminUsername
     * @param adminPassword
     * @return boolean
     */
    public boolean isUserAdmin(String adminUsername, String adminPassword) {
        // find this user in the users arraylist
        User user = users.stream()
                .filter(u -> u.getUsername().equals(adminUsername) && u.getPassword().equals(adminPassword)).findAny()
                .orElse(null);
        if (user == null) {
            return false;
        }
        // check if this user instance is a SchoolAdmin or not
        if (user.isSchoolAdmin()) {
            return true;
        } else {
            return false;
        }
    }

    // to check if the user is a volunteer in the user list array or not
    /**
     * @param volunteerUsername
     * @param volunteerPassword
     * @return boolean
     */
    public boolean isUserVolunteer(String volunteerUsername, String volunteerPassword) {
        // find this user in the users arraylist
        User user = users.stream()
                .filter(u -> u.getUsername().equals(volunteerUsername) && u.getPassword().equals(volunteerPassword))
                .findAny().orElse(null);
        if (user == null) {
            return false;
        } // check if this user instance is a Volunteer or not
        if (user.isVolunteer() == true) {
            return true;
        } else {
            return false;
        }
    }

    // to check if that username exists in the ArrayList already or not
    /**
     * @param username
     * @return boolean
     */
    public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSchool(String schoolName) {
        for (School school : schools) {
            if (school.getSchoolName().equals(schoolName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCity(String cityName) {
        for (School school : schools) {
            if (school.getCity().equals(cityName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@summary} A method of SchoolHELP, get the school by receiving cityName as a
     * param, then returns the schools in that city, if they exist.
     * 
     * @param cityName
     * @return ArrayList of type School
     */
    public ArrayList<Request> getRequestsByCity(String cityName) {
        for (School school : schools) {
            if (school.getCity().equals(cityName)) {
                return school.getRequests();
            }
        }
        return null;
    }

    public boolean isValidDate(int requestDate) {
        // using the convention of YYYYMMDD
        // to validate whatever is requestDate
        // if it is a valid date, return true
        // if it is not a valid date, return false
        try {
            LocalDateTime.of(requestDate / 10000, (requestDate % 10000) / 100, requestDate % 100,
                    0,
                    0);
        } catch (DateTimeException e) {
            return false;
        }
        return true;

    }

    /**
     * {@summary} to return all the requests in the ArrayList of type Request,
     * that has the same date
     * 
     * @param requestDate
     * @return ArrayList
     */
    public ArrayList<Request> getRequestsByDate(int requestDate) {
        // converting the passed in requestDate to a LocalDateTime format
        // this is apparently YEAR-MONTH-DAY
        LocalDateTime date = LocalDateTime.of(requestDate / 10000, (requestDate % 10000) / 100, requestDate % 100, 0,
                0);
        // then, loop through the schools arraylist, and get the requests of each school
        // and loop through the newly created requests arraylist, and check if the date
        // is the same
        ArrayList<Request> requests = new ArrayList<Request>();
        for (School school : schools) {
            for (Request request : school.getRequests()) {
                if (request.getRequestDate().equals(date)) {
                    requests.add(request);
                }
            }
        }
        return requests;
    }

    /**
     * {@summary} Method of class SchoolHELP, to validate if the request is valid by
     * passing a type int requestID param.
     * 
     * @param requestID
     * @return
     */
    public boolean isRequest(int requestID) {
        for (Request request : getAllRequests()) {
            if (request.getRequestID() == requestID) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@summary} Method of class SchoolHELP, to return a singular request from the
     * ArrayList in School arrays. Traverses through the ArrayList of type School,
     * and then the ArrayList of type Request, and returns the request if it exists.
     * 
     * @param requestID
     * @return Request
     */
    public Request getRequest(int requestID) {
        for (School school : schools) {
            for (Request request : school.getRequests()) {
                if (request.getRequestID() == requestID) {
                    return request;
                }
            }
        }
        return null;
    }

    /**
     * {@summary} Method of class SchoolHELP, to validate the time passed in as an
     * int. Uses HHMM time format.
     * 
     * @param proposedTime
     * @return
     */
    public boolean isValidTime(int proposedTime) {
        // the basic time format HHMM,
        // where HH is the hour, and MM is the minute
        // HH must not be above 24, MM must not be above 59
        // first, check if the int its a 4 digit number or not
        if (proposedTime < 1000 || proposedTime > 2400) {
            return false;
        } else {
            // then, break the time into HH, MM
            int HH = proposedTime / 100;
            int MM = proposedTime % 100;

            // then, check if HH is above 24, MM is above 59
            if (HH > 24 || MM > 59) {
                return false;
            } else {
                return true;
            }
        }
    }
}
