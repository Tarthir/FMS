package service;

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
    RegisterResult register(RegisterRequest request){
        newUser = makeUserModel(request);
        UserDao dao = new UserDao();
        if(dao.register(newUser)){
            AuthTokenDao auth = new AuthTokenDao();
            AuthToken authToken = new AuthToken();//gets the timestamp and UUID in the model object
            auth.insertAuthToken(newUser.getID(),authToken);
            //NEED TO GENERATE DATA DATA
            //RegisterResult = new RegisterResult(authToken.getAuthToken(),newUser.getUserName());
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
        User user;
        return user = new User(request.getUserName(),request.getPassWord(),
                request.getEmail(),request.getfName(),request.getlName(),request.getGender(),uuid.toString());
    }

}
