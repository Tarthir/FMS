package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * Contains the info needed to login to the application
 */

public class LoginRequest {
    /**The username of this loginrequest*/
    String username;
    /**The password of this loginrequest*/
    String password;

    public LoginRequest(String userName, String passWord) {
        this.username = userName;
        this.password = passWord;
    }

    public LoginRequest() {
    }

    /**@RETURN The userName of this loginrequest*/
    public String getUserName() {
        return username;
    }
    /**@RETURN The password of this loginrequest*/
    public String getPassWord() {
        return password;
    }
    /**Checks to see if this request is valid
     * @RETURN boolean
     * */
    public boolean isValidRequest(){
        return (!this.username.isEmpty()) && (!this.password.isEmpty());
    }
}
