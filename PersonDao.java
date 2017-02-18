package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import infoObjects.PeopleRequest;
import infoObjects.PersonRequest;
import models.Person;

/**
 * Created by tyler on 2/10/2017.
 * Our DAO to access the database, and get a person or multiple people from it
 */

public class PersonDao {
    DataBase db;
    private String SELECT = "SELECT from person WHERE personID = ?";
    private final String SELECT_STAR = "SELECT * from person";
    public PersonDao() {
        db = new DataBase();
    }

    /***
     * A method to get a user's ancestor's info
     *
     * @PARAM userID, the ID for a specific user
     * @PARAM personID, the ID for a specific ancestor
     */
    Person getPerson(PersonRequest request){
        Connection conn = null;
        PreparedStatement stmt = null;//insert statement
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(SELECT);
            stmt.setString(1,request.getPersonID());
            stmt.execute();//execute the statement
            db.closeConnection(true, conn);//how is the data getting in the database?
        }catch(SQLException e){
            e.printStackTrace();
            db.closeConnection(false, conn);
        }
        finally {
            DataBase.safeClose(stmt);
        }
        return null;
    }

    /***
     * A method to get all of a users ancestor's
     *
     * @PARAM userID, the ID for a specific user
     */
    Person[] getPeople(PeopleRequest request){
        Connection conn = null;
        try {
            conn = db.openConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(SELECT_STAR);//executes, STILL NEED TO GET THE DATA
            //Person [] =
            db.closeConnection(true, conn);//how is the data getting in the database?
        }catch(Exception e){e.printStackTrace();}
        finally {
            if(conn != null) {
                db.closeConnection(false, conn);
            }
            return null;
        }
    }
}
