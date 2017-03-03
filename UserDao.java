package dataAccess;

import java.sql.Connection;

import infoObjects.LoginRequest;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.crypto.Data;

/**
 * Created by tyler on 2/10/2017.
 * This class interacts with the database, inserting/grabbing information
 */

public class UserDao {
    /**Our insert string to create a user*/
    private String insertIntoUser = "insert into user (userID, userName, password, email, firstName, lastName, gender) values ( ?, ?, ?, ?, ?, ?, ?)";
    /**A database object to use to get our connection*/
    private DataBase db;
    public UserDao() {
            db = new DataBase();
    }

    /***
     * A method to register a new user. Create's a user object and passes it to the UserCreator class
     * @PARAM user, the new user we are making
     * @RETURN the result of trying to register a user
     * @EXCEPTION throws SQLException
     */
    public boolean register(User newUser)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;//insert statement
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
                return true;
            }
            if(!conn.isClosed()){db.closeConnection(false, conn);}
        }catch(SQLException e){
            //e.printStackTrace();
            db.closeConnection(false, conn);
            throw e;
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
     * @RETURN the userID of the userName/Password combo
     * @EXCEPTION throws SQLException
     */
    public String login(LoginRequest request)throws SQLException{
        return getUserID(request.getUserName(),request.getPassWord(),"SELECT userID FROM user WHERE userName = ? AND password = ?");
    }

    /**
     * A method get a user ID from the fName/lName of a user
     * @Param fName, the first name of the user
     * @Param lName, the last name of the user
     * @RETURN the userID of the fName/lName combo
     * @EXCEPTION throws SQLException
     */
    public String getUserIDWithNames(String fName,String lName)throws SQLException{
        return getUserID(fName,lName,"SELECT userID FROM user WHERE firstName = ? AND lastName = ?");
    }
    /**
     * A method get a user ID from the userName of a user
     * @Param String, the userName of the user
     * @RETURN the userID
     * @EXCEPTION throws SQLException
     */
    public String getUserIDWithUserName(String userName)throws SQLException{
        return getUserID(userName,"SELECT userID FROM user WHERE userName = ?");
    }
    /**
     * A method get a user ID from the database from two inputs
     * @Param input1, the first input
     * @Param input2, the second input
     * @RETURN the userID related to these two inputs
     * @EXCEPTION throws SQLException
     */
    private String getUserID(String input1,String input2,String SQLString)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;//insert statement
        ResultSet rs = null;
        String output = "";
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(SQLString);
            stmt.setString(1,input1);
            stmt.setString(2,input2);
            rs = stmt.executeQuery();//execute the statement
            if(rs.next()){
                output = rs.getString(1);
                db.closeConnection(true, conn);
            }
            if(!conn.isClosed()){db.closeConnection(false, conn);}
        }catch(SQLException e){
            //e.printStackTrace();
            db.closeConnection(false, conn);
            throw e;
        }
        finally {
            DataBase.safeClose(rs);
            DataBase.safeClose(stmt);
        }
        return output;
    }

    private String getUserID(String userName,String SQLString)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;//insert statement
        ResultSet rs = null;
        String output = "";
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(SQLString);
            stmt.setString(1,userName);
            rs = stmt.executeQuery();//execute the statement
            if(rs.next()){
                output = rs.getString(1);
                db.closeConnection(true, conn);
            }
            if(!conn.isClosed()){db.closeConnection(false, conn);}
        }catch(SQLException e){
            //e.printStackTrace();
            db.closeConnection(false, conn);
            throw e;
        }
        finally {
            DataBase.safeClose(rs);
            DataBase.safeClose(stmt);
        }
        return output;
    }
    //DO DELETES
    /**
     * Checks if this userName alreadyExists
     * @PARAM userName, the userName to be checked against the databse
     * @RETURN whether it is true of not that this userName is already in the darabase
     * @EXCEPTION throws SQLException
     * */
    public boolean checkUserName(String userName)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;//insert statement
        ResultSet rs = null;
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement("SELECT userName FROM user WHERE userName = ?");
            stmt.setString(1,userName);
            rs = stmt.executeQuery();//execute the statement
            if(rs.next()){
                db.closeConnection(true, conn);
                return true;
            }
            if(!conn.isClosed()){db.closeConnection(false, conn);}
        }catch(SQLException e){
            //e.printStackTrace();
            db.closeConnection(false, conn);
            throw e;
        }
        finally {
            DataBase.safeClose(rs);
            DataBase.safeClose(stmt);
        }
        return false;
    }

    /** Selects from all rows and columns on any table from the UserName
     * @PARAM String userName
     * @RETURN  userInfo
     * @EXCEPTION SQLException
     */
    public ArrayList<String> selectAllFromUser(String userName) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement("SELECT userID,userName,password,email,firstName,lastName,gender FROM user WHERE userName = ?");
            stmt.setString(1,userName);
            rs = stmt.executeQuery();//execute the statement
            int columns = rs.getMetaData().getColumnCount();
            ArrayList<String> user = new ArrayList<>();
            if (rs.next()) {
                for(int i = 1; i <= columns; i++){
                    user.add(rs.getString(i));
                }
            }
            if(user.size() == 0){db.closeConnection(false, conn);}
            else{//if we got a result
                db.closeConnection(true, conn);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            db.closeConnection(false, conn);
            throw e;
        } finally {
            DataBase.safeClose(rs);
            DataBase.safeClose(stmt);
        }
        return null;
    }

}
