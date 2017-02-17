package service;

import infoObjects.RegisterRequest;
import infoObjects.RegisterResult;

/**
 * Created by tyler on 2/14/2017.
 * Called/Created by our RegisterHandler to use the DAO to try and register a new user and add them to the Database
 */

public class RegisterService {
   // private RegisterRequest request;

    public RegisterService(RegisterRequest request) {
        //this.request = request;
    }
    /**
     * Calls/Creates our DAO classes to interact with the database and get the RegisterResult
     * @PARAM request: to register a new user
     * @RETURN Gets the result of trying to register a new user
     * */
    RegisterResult register(RegisterRequest request){
        return null;
    }
}
