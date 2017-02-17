package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * THe result of attempting to register a new user
 */

public class RegisterResult {
    /** Non-empty auth token string*/
    private String authToken;
    /** User name passed in with request*/
    private String userName;
    /** Non-empty string containing the Person ID of the user’s generated Person object*/
    private String personID;


    public RegisterResult(String authToken, String userName, String personID) {
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }
    /***
     * @RETURN Gets the authtoken
     */
    public String getAuthToken() {
        return authToken;
    }
    /***
     * @RETURN Gets the Username
     */
    public String getUserName() {
        return userName;
    }
    /***
     * @RETURN Gets the personID
     */
    public String getPersonID() {
        return personID;
    }

}
