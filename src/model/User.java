package model;

public class User extends BaseModel {
    private String email;
    private String username;
    private String password;
    private boolean isAdmin;

    public User(String email, String username, String password, boolean isAdmin) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
