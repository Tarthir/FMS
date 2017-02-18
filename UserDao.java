package dataAccess;

import java.sql.Connection;

import infoObjects.LoginRequest;
import infoObjects.LoginResult;
import infoObjects.RegisterResult;
import models.AuthToken;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.crypto.Data;

/**
 * Created by tyler on 2/10/2017.
 * This class interacts with the database, inserting/grabbing information
 */

public class UserDao {
    /**Our insert string to create a user*/
    private String insertIntoUser = "insert into user (userID userName password email firstName lastName gender) values ( ?, ?, ?, ?, ?, ?, ?)";
    /**Our insert string to fill our authtoken table*/
    private String insertIntoAuth = "insert into authToken (userID authToken timeStamp) values ( ?, ?, ? )";
    /**Our insert string to get a userID*/
    private String getUserID = "SELECT userID FROM user WHERE firstName = ? AND lastName = ?";
    /**Our insert string to get an authToken*/
    private String getAuthToken = "SELECT authToken FROM authToken WHERE userID = ?";
    /**A database object to use to get our connection*/
    DataBase db;
    public UserDao() {
            db = new DataBase();
    }

    /***
     * A method to register a new user. Create's a user object and passes it to the CreateUsers class
     * @PARAM user, the new user we are making
     * @RETURN the result of trying to register a user
     */
    //return result or boolean? how should i make the authtoken? Do I do it as part of this transaction?
    public boolean register(User newUser){
        Connection conn = null;
        PreparedStatement stmt = null;//insert statement
        //CHECK TO SEE if USER IS UNIQUE
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(insertIntoUser);
            stmt.setString(1,newUser.getID());
            stmt.setString(2,newUser.getPassWord());
            stmt.setString(3,newUser.getEmail());
            stmt.setString(4,newUser.getfName());
            stmt.setString(5,newUser.getlName());
            stmt.setString(6,newUser.getGender());
            if(stmt.executeUpdate() == 1){//execute the statement
                db.closeConnection(true, conn);
                //ALSO LOG US ON
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            db.closeConnection(false, conn);
        }
        finally {
            DataBase.safeClose(stmt);
        }
        return false;
    }

    /**
     * A method to login a user
     * @Param request, this object holds the info needed to successfully login
     * @RETURN the result of trying to login a user
     */
    public boolean login(LoginRequest request){
        Connection conn = null;
        PreparedStatement stmt = null;//insert statement
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(getUserID);
            stmt.setString(1,request.getUserName());
            stmt.setString(2,request.getPassWord());
            if(stmt.executeUpdate() == 1){//execute the statement
                db.closeConnection(true, conn);
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            db.closeConnection(false, conn);
            //THROW AN ERROR HERE SO THAT THE RESULT CREATED HOLDS AN ERROR?
        }
        finally {
            DataBase.safeClose(stmt);
        }
        return false;
    }
}
