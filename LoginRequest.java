package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * Contains the info needed to login to the application
 */

public class LoginRequest {
    /**The username of this loginrequest*/
    String userName;
    /**The password of this loginrequest*/
    String passWord;

    public LoginRequest(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
    /**@RETURN The userName of this loginrequest*/
    public String getUserName() {
        return userName;
    }
    /**@RETURN The password of this loginrequest*/
    public String getPassWord() {
        return passWord;
    }
}
