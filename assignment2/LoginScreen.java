// this class handles everything related to the login screen, the first class to be called when the program is run
// and it also handles both AdminLogin and VolunteerLogin classes
public abstract class LoginScreen {
    // class fields
    private String username;
    private String password;

    // constructor
    public LoginScreen(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // abstract methods
    public abstract void login();

    public abstract void logout();

    public abstract void register();

    public abstract void forgotPassword();

    public abstract void changePassword();

    public abstract void changeUsername();
}
