package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import models.AuthToken;

/**
 * Created by tyler on 2/10/2017.
 * Gets our Auth Tokens from our database
 */

public class AuthTokenDao {
    /**Our insert string to fill our authtoken table*/
    private String insertIntoAuth = "insert into authToken (userID authToken timeStamp) values ( ?, ?, ? )";
    /**Our insert string to get an authToken*/
    private String getAuthToken = "SELECT authToken FROM authToken WHERE userID = ?";
    DataBase db;
    public AuthTokenDao() {
        db = new DataBase();
    }

    /**Inserts authtokens into the database keyed to userIDs*/
    public boolean insertAuthToken(String userID,AuthToken authTok){
        Connection conn = null;
       // ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(insertIntoAuth);
            conn = db.openConnection();
            stmt.setString(1,userID);
            stmt.setString(2, authTok.getAuthToken());
            stmt.setLong(3, authTok.getTimeStamp());
            if(stmt.executeUpdate() == 1){//execute the statement
                db.closeConnection(true, conn);
                return true;
            }
        }
        catch(SQLException e){
         e.printStackTrace();
         db.closeConnection(false, conn);
        }
        finally {
            DataBase.safeClose(stmt);
        }
        return false;
    }

    /**Gets the Authtoken(s) of a particular user*/
    public boolean getAuthToken(String userID){
        Connection conn = null;
        // ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(getAuthToken);
            conn = db.openConnection();
            stmt.setString(1,userID);
            if(stmt.executeUpdate() == 1){//execute the statement
                db.closeConnection(true, conn);
                return true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            db.closeConnection(false, conn);
        }
        finally {
            DataBase.safeClose(stmt);
        }
        return false;
    }
}
