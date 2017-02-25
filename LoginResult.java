package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * THe result of attempting to login to the application
 */

public class LoginResult {
    /** Non-empty auth token string*/
    private String authToken;
    /** User name passed in with request*/
    private String userName;
    /** Non-empty string containing the userID  of the user’s generated object*/
    private String userID;
    /**The exception thrown object*/
    private Exception e;


    public LoginResult(String authToken, String userName, String userID) {
        this.authToken = authToken;
        this.userName = userName;
        this.userID = userID;
    }
    /**@RETURN The authToken of this login result*/
    public String getAuthToken() {
        return authToken;
    }
    /**@RETURN The userName of this login result*/
    public String getUserName() {
        return userName;
    }
    /**@RETURN The personID of this login result*/
    public String getuserID() {
        return userID;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
}
