package service;

import java.rmi.ServerError;
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
    //private User newUser;

    public RegisterService() {
    }

    /**
     * Calls/Creates our DAO classes to interact with the database and get the RegisterResult
     *
     * @PARAM request: to register a new user
     * @RETURN Gets the result of trying to register a new user
     * @Exception throws SQLException
     */
    public RegisterResult register(RegisterRequest request) {
        try {
            User newUser = makeUserModel(request);
            UserDao dao = new UserDao();
            if (!dao.checkUserName(request.getUserName())) {//check to see if the userName already exists then if register is successful
               // System.out.println("Checked userName");
                if (dao.register(newUser)) {
                    //System.out.println("register worked");
                    return getResult(request,newUser);
                }
            }
            else {
                return new RegisterResult(new IllegalArgumentException());
            }
        } catch (SQLException e) {
            return new RegisterResult(e);
        }
        return new RegisterResult(new Exception());
    }
    /**
     * Gets the result of registering a user
     *
     * @PARAM request: to register a new user
     * @RETURN Gets the result of trying to register a new user
     * @PARAM the user that was made
     * @Exception throws SQLException
     */
    private RegisterResult getResult(RegisterRequest request,User newUser)throws SQLException{
        AuthTokenDao authDao = new AuthTokenDao();
        AuthToken authToken = new AuthToken();//gets the timestamp and UUID in the model object
        if (authDao.insertAuthToken(newUser.getID(), authToken)) {
            DataGenerator dataGenerator = new DataGenerator(newUser.getID());
            //Generates my data right here Setting up the request first
            int numOfGenerations = 4;
            FillRequest fillRequest = new FillRequest(numOfGenerations, newUser.getUserName());
            fillRequest.setLocations(request.getLocations());
            fillRequest.setfNames(request.getfNames());
            fillRequest.setmNames(request.getmNames());
            fillRequest.setlNames(request.getlNames());

            FillResult result = null;
            try {
                result = dataGenerator.genData(fillRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (result.getE() == null) {//if we didnt have an error
                return new RegisterResult(authToken.getAuthToken(), newUser.getUserName(),result.getUserPersonID());
            }
            else {
                return new RegisterResult(result.getE());
            }//we had an error
        }
        //System.out.println("bad");
        return new RegisterResult(new ServerError("Inserting the authtoken failed",new Error()));
    }
    /**
     * Makes the user for the register method to use
     * @PARAM request, the request we are creating the user from
     * @RETURN The user object
     * */
    private User makeUserModel(RegisterRequest request) {
        UUID uuid = UUID.randomUUID();
        return new User(uuid.toString(), request.getUserName(), request.getPassWord(),
                request.getEmail(), request.getfName(), request.getlName(), request.getGender());
    }

}
