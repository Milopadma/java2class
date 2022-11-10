import java.util.ArrayList;

public class SchoolHELP {
    // this class manages instances of User and School classes

    // class variables
    private static ArrayList<User> users = new ArrayList<User>();
    private static ArrayList<School> schools = new ArrayList<School>();

    // class constructor
    public SchoolHELP() {
        

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
    public static boolean isUserAdmin(User user){
        if(user.getAdmin() == true){
            return true;
        }else{
            return false;
        }
    }


}

