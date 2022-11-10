import java.util.ArrayList;

public class SchoolHELP {
    // this class manages instances of User and School classes

    // class variables
    private static ArrayList<User> users = new ArrayList<User>();
    private static ArrayList<School> schools = new ArrayList<School>();

    // class constructor
    public SchoolHELP() {
        //default ArrayList constructor with a single default SchoolAdmin user
        users.add(new SchoolAdmin("admin", "admin", "default admin", "defaultadmin@milopadma.com", 000, 1, "Admin"));
    }

    // class setter and getter methods
    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        SchoolHELP.users = users;
    }

    public static ArrayList<School> getSchools() {
        return schools;
    }

    public static void setSchools(ArrayList<School> schools) {
        SchoolHELP.schools = schools;
    }

    // class methods
    public static void addUser(User user){
        users.add(user);
    }

    public static void addSchool(School school){
        schools.add(school);
    }

    public static void removeUser(User user){
        users.remove(user);
    }

    public static void removeSchool(School school){
        schools.remove(school);
    }

    // functional class methods
    public boolean isUserAdmin(String adminUsername, String adminPassword){
        // find this user in the users arraylist
        User user = users.stream().filter(u -> u.getUsername().equals(adminUsername) && u.getPassword().equals(adminPassword)).findAny().orElse(null);
        if (user == null) {
            return false;
        }
        // check if this user instance is a SchoolAdmin or not
        if(user.isSchoolAdmin()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isUserVolunteer(String volunteerUsername, String volunteerPassword){
        // find this user in the users arraylist
        User user = users.stream().filter(u -> u.getUsername().equals(volunteerUsername) && u.getPassword().equals(volunteerPassword)).findAny().orElse(null);
        if (user == null) {
            return false;
        }       // check if this user instance is a Volunteer or not
        if(user.isVolunteer() == true){
            return true;
        }else{
            return false;
        }
    }

}

