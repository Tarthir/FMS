package handler;

import functionObjects.LoginRequest;
import functionObjects.LoginResult;

/**
 * Created by tyler on 2/14/2017.
 * Called by LoginHandler to use our DAO classes to try and login
 */

public class LoginService {
    //private LoginRequest login;

    public LoginService(LoginRequest login) {
        //this.login = login;
    }
    /**
     * Gets the result of trying to login
     * @PARAM LoginRequest: the request to login into the application
     * @RETURN the result of trying to login
     * */
    public LoginResult login(LoginRequest request){
        return null;
    }


    /**
     * @RETURN Gets the login request object
     * */
    //public LoginRequest getLogin() {
    //    return login;
   // }
}
