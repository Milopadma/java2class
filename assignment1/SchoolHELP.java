import java.util.ArrayList;

public class SchoolHELP {
    // this class manages instances of User and School classes

    // class variables
    private static ArrayList<User> users = new ArrayList<User>();
    private static ArrayList<School> schools = new ArrayList<School>();

    // class constructor
    public SchoolHELP() {
        // default ArrayList constructor with a single default SchoolAdmin user
        users.add(new SchoolAdmin("admin", "admin", "default admin", "defaultadmin@milopadma.com", 000, 1, "Admin"));
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

    public void removeUser(User user) {
        users.remove(user);
    }

    public void removeSchool(School school) {
        schools.remove(school);
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
}
