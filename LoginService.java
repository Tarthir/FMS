package service;

import dataAccess.AuthTokenDao;
import dataAccess.UserDao;
import infoObjects.LoginRequest;
import infoObjects.LoginResult;
import infoObjects.RegisterResult;
import models.AuthToken;

/**
 * Created by tyler on 2/14/2017.
 * Called by LoginHandler to use our DAO classes to try and login
 */

public class LoginService {

    public LoginService() {
    }
    /**
     * Gets the result of trying to login
     * @PARAM LoginRequest: the request to login into the application. holds userName and password
     * @RETURN the result of trying to login
     * */
    public LoginResult login(LoginRequest request){
        UserDao dao = new UserDao();
        String userID = dao.login(request);
        if(!userID.equals("")){
            AuthTokenDao authDao = new AuthTokenDao();
            AuthToken authToken = new AuthToken();//gets the timestamp and UUID in the model object
            if(authDao.insertAuthToken(userID,authToken)) {
                return new LoginResult(authToken.getAuthToken(), request.getUserName(), userID);
            }
        }
        return null;
    }


}
