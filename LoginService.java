package service;

import java.sql.SQLException;

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
    public LoginResult login(LoginRequest request) {
        try {
            UserDao dao = new UserDao();
            System.out.print(1);
            System.out.print(request.getUserName() + request.getPassWord());
            if (!request.isValidRequest()) {//if this is a valid request
                System.out.print(2);
                String userID = dao.login(request);
                if(!userID.equals("")) {//if founda matching userID
                    System.out.print(3);
                    AuthTokenDao authDao = new AuthTokenDao();
                    AuthToken authToken = new AuthToken();//gets the timestamp and UUID in the model object
                    if (authDao.insertAuthToken(userID, authToken)) {
                        System.out.print(4);
                        return new LoginResult(authToken.getAuthToken(), request.getUserName(), userID);
                    }
                }
            }
        }
       catch(SQLException e){
           return new LoginResult(e);
       }
        return new LoginResult(new Exception("Invalid Request"));
    }


}
