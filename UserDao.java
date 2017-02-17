package dataAccess;

import java.sql.Connection;

import infoObjects.LoginRequest;
import infoObjects.LoginResult;
import infoObjects.RegisterRequest;
import infoObjects.RegisterResult;

/**
 * Created by tyler on 2/10/2017.
 * This class interacts with the database, inserting/grabbing information
 */

public class UserDao {
    /**A database object to use to get our connection*/
    DataBase db;
    public UserDao() {
            db = new DataBase();
    }

    /***
     * A method to register a new user
     * @PARAM request, the request to register a new user
     * @RETURN the result of trying to register a user
     */
    private RegisterResult register(RegisterRequest request){
        Connection conn = db.openConnection();
       /* String userName = request.getUserName();
        String passWord = request.getPassWord();
        String email = request.getEmail();
        String fName = request.getfName();
        String lName = request.getlName();
        String gender = request.getGender();*/
        //db.createTables();

        return null;
    }
    /***
     * A method to login a user
     * @Param request, this object holds the info needed to successfully login
     * @RETURN the result of trying to login a user
     */
    private LoginResult login(LoginRequest request){

        return null;
    }
}
