package controllers.user;

public class UserSession {

    private static UserSession instance;

    public String email = null;

    public UserSession() {

    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void cleanUserSession() {
        email = "";
    }

}

