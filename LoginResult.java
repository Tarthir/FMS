package functionObjects;

/**
 * Created by tyler on 2/13/2017.
 * THe result of attempting to login to the application
 */

public class LoginResult {
    private String authToken;// Non-empty auth token string
    private String userName; // User name passed in with request
    private String personID;// Non-empty string containing the Person ID of the user’s generated Person object


    public LoginResult(String authToken, String userName, String personID) {
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
