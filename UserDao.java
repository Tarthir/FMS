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
    private String insertIntoUser = "insert into user (userID, userName, password, email, firstName, lastName, gender) values ( ?, ?, ?, ?, ?, ?, ?)";
    /**Our insert string to get a userID*/
    private String getUserID = "SELECT userID FROM user WHERE firstName = ? AND lastName = ?";
    /**A database object to use to get our connection*/
    private DataBase db;
    public UserDao() {
            db = new DataBase();
    }

    /***
     * A method to register a new user. Create's a user object and passes it to the CreateUsers class
     * @PARAM user, the new user we are making
     * @RETURN the result of trying to register a user
     */
    public boolean register(User newUser){
        Connection conn = null;
        PreparedStatement stmt = null;//insert statement
        //CHECK TO SEE if USER IS UNIQUE
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(insertIntoUser);
            stmt.setString(1,newUser.getID());
            stmt.setString(2,newUser.getUserName());
            stmt.setString(3,newUser.getPassWord());
            stmt.setString(4,newUser.getEmail());
            stmt.setString(5,newUser.getfName());
            stmt.setString(6,newUser.getlName());
            stmt.setString(7,newUser.getGender());
            if(stmt.executeUpdate() == 1){//execute the statement
                db.closeConnection(true, conn);
                //ALSO LOG US ON
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

    public String getUserIDWithNames(String fName,String lName){
        Connection conn = null;
        PreparedStatement stmt = null;//insert statement
        ResultSet rs = null;
        String output = "";
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(getUserID);
            stmt.setString(1,fName);
            stmt.setString(2,lName);
            rs = stmt.executeQuery();//execute the statement
            if(rs.next()){
                output = rs.getString(1);
                db.closeConnection(true, conn);
            }
            if(!conn.isClosed()){
                db.closeConnection(false, conn);
            }
        }catch(SQLException e){
            e.printStackTrace();
            db.closeConnection(false, conn);
        }
        finally {
            DataBase.safeClose(rs);
            DataBase.safeClose(stmt);
        }
        return output;
    }
}
