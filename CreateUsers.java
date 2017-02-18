package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import infoObjects.RegisterRequest;
import infoObjects.RegisterResult;
import models.User;

/**
 * Created by tyler on 2/10/2017.
 * Creates users for the database. Adds them in
 */

public class CreateUsers {
    public CreateUsers() {
    }
    /***
     * A method to register a new user
     * @PARAM request, the request to register a new user
     * @Return the result, successful or not of the register attempt
     */
    RegisterResult register(User user,Connection conn) throws SQLException{

        return null;
    }
}
