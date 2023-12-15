public class UserLogin {
    public static UserLogin loggedInUser;
    private final String username;
    private final String password;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
