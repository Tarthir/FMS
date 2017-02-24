package service;

import java.sql.SQLException;
import java.util.UUID;

import dataAccess.AuthTokenDao;
import dataAccess.UserDao;
import infoObjects.RegisterRequest;
import infoObjects.RegisterResult;
import models.AuthToken;
import models.User;

/**
 * Created by tyler on 2/14/2017.
 * Called/Created by our RegisterHandler to use the DAO to try and register a new user and add them to the Database
 */

public class RegisterService {
    private User newUser;

    public RegisterService() {
    }
    /**
     * Calls/Creates our DAO classes to interact with the database and get the RegisterResult
     * @PARAM request: to register a new user
     * @RETURN Gets the result of trying to register a new user
     * */
    public RegisterResult register(RegisterRequest request) throws SQLException{
        newUser = makeUserModel(request);
        UserDao dao = new UserDao();
        if(!dao.checkUserName(request.getUserName()) && dao.register(newUser)){//check to see if the userName already exists then if register is successful
            AuthTokenDao authDao = new AuthTokenDao();
            AuthToken authToken = new AuthToken();//gets the timestamp and UUID in the model object
            if(authDao.insertAuthToken(newUser.getID(),authToken)) {
                //NEED TO GENERATE DATA DATA
                //HOW DO I GET LOGGED ON?
                return new RegisterResult(authToken.getAuthToken(), newUser.getUserName(), newUser.getID());
            }
        }
        return null;
    }
    /**
     * Makes the user for the register method to use
     * @PARAM request, the request we are creating the user from
     * @RETURN The user object
     * */
    private User makeUserModel(RegisterRequest request){
        UUID uuid = UUID.randomUUID();
        return new User(uuid.toString(),request.getUserName(),request.getPassWord(),
                request.getEmail(),request.getfName(),request.getlName(),request.getGender());
    }

}
