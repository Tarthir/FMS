package service;

import java.sql.SQLException;
import java.util.UUID;

import dataAccess.AuthTokenDao;
import dataAccess.UserDao;
import infoObjects.FillRequest;
import infoObjects.FillResult;
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
     * @Exception throws SQLException
     * */
    public RegisterResult register(RegisterRequest request){
        try {
            newUser = makeUserModel(request);
            UserDao dao = new UserDao();
            if (!dao.checkUserName(request.getUserName()) && dao.register(newUser)) {//check to see if the userName already exists then if register is successful
                AuthTokenDao authDao = new AuthTokenDao();
                AuthToken authToken = new AuthToken();//gets the timestamp and UUID in the model object
                if (authDao.insertAuthToken(newUser.getID(), authToken)) {
                    FillService fillService = new FillService();
                    DataGenerator dataGenerator = new DataGenerator(newUser.getID());
                    //Generates my data right here
                    FillResult result = dataGenerator.genData(new FillRequest(4, newUser.getUserName(), request.getLocations(), request.getfNames(), request.getlNames(), request.getmNames()));
                    if (result.getE() == null){//if we didnt have an error
                        return new RegisterResult(authToken.getAuthToken(), newUser.getUserName(), newUser.getID());
                    }
                    else{return new RegisterResult(result.getE());}//we had an error
                }
            }
        }catch(SQLException e){
            return new RegisterResult(e);
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
