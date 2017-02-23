package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import infoObjects.PeopleRequest;
import infoObjects.PersonRequest;
import models.Person;

/**
 * Created by tyler on 2/10/2017.
 * Our DAO to access the database, and get a person or multiple people from it
 */

public class PersonDao {
    DataBase db;
    private String SELECT = "SELECT personID,firstName,lastName,father,mother,spouse FROM person WHERE personID = ?";
    private final String SELECT_ON_USER = "SELECT personID,firstName,lastName,father,mother,spouse FROM person WHERE userID = ?";
    public PersonDao() {
        db = new DataBase();
    }

    /***
     * A method to get a user's ancestor's info
     *
     * @PARAM userID, the ID for a specific user
     * @PARAM personID, the ID for a specific ancestor
     * @RETURN returns the data needed to start to make a person object
     */
    public ArrayList<String> getPerson(PersonRequest request){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;//insert statement
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(SELECT);
            stmt.setString(1,request.getPersonID());
            rs = stmt.executeQuery();//execute the statement
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            if(rs.next()){
                ArrayList<String> resultSet = new ArrayList<>();
                for(int i = 1; i <= columnCount; i++){
                    resultSet.add(rs.getString(i));
                }
                db.closeConnection(true, conn);
            }
        }catch(SQLException e){
            e.printStackTrace();
            db.closeConnection(false, conn);
        }
        finally {
            DataBase.safeClose(rs);
            DataBase.safeClose(stmt);
            if(conn != null) {
                db.closeConnection(false, conn);
            }
        }
        return null;
    }

    /***
     * A method to get all of a users ancestor's
     *
     * @PARAM userID, the ID for a specific user
     * @RETURN returns the data needed to start to make people objects
     */
    public ArrayList<ArrayList<String>> getPeople(PeopleRequest request){
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        ArrayList<ArrayList<String>> allPeople = new ArrayList<>();//all the people
        try {
            conn = db.openConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ON_USER);
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            while(rs.next()){
                ArrayList<String> resultSet = new ArrayList<>();
                for(int i = 1; i <= columnCount; i++){
                    resultSet.add(rs.getString(i));
                }
                allPeople.add(resultSet);
            }
            if(allPeople.size() == 0) {//nothing was added
                db.closeConnection(false, conn);
            }
            else{
                db.closeConnection(true, conn);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            db.closeConnection(false, conn);}
        finally {
            DataBase.safeClose(rs);
            DataBase.safeClose(stmt);
            return null;
        }
    }
    //DO DELETES
}
