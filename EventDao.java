package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import infoObjects.EventResult;
import infoObjects.EventRequest;
import infoObjects.EventsRequest;
import models.Event;

/**
 * Created by tyler on 2/10/2017.
 * Our DAO class for events
 */

public class EventDao {
    DataBase db;
    /**Select on the whole table where eventID = ?*/
    private String SELECT = "SELECT eventID,userID,personID,year,eventType,locationID FROM events WHERE eventID = ?";
    /**Select on the whole table where userID = ?*/
    private String SELECT_STAR = "SELECT eventID,userID,personID,year,eventType,locationID FROM events WHERE userID = ?";
    /**Insert into every column of the table the table creating a new row*/
    private String insertEvents = "insert into events (eventID,userID,personID,year,eventType,locationID) values (?,?,?,?,?,?)";
    public EventDao() {
        db = new DataBase();
    }
    /**
     * Inserts Events into our database
     * @PARAM event, the event to insert
     * @RETURN boolean
     * @EXCEPTION throws SQL exception
     * */
    public boolean insertEvent(Event event) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(insertEvents);
            stmt.setString(1,event.getEventID());
            stmt.setString(2, event.getUserID());
            stmt.setString(3, event.getPersonID());
            stmt.setString(4, event.getYear());
            stmt.setString(5, event.getEventType());
            stmt.setString(6, event.getLocationID());
            if(stmt.executeUpdate() == 1){//execute the statement
                db.closeConnection(true, conn);
                return true;
            }
            if(!conn.isClosed()){db.closeConnection(false, conn);}
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
    /***
     * A method to get a specific event
     *
     * @PARAM request, the info for a specific event
     * @RETURN gets the result of attempting to get a specific event
     * @EXCEPTION throws SQL exception
     */
    public ArrayList<String> getEvent(EventRequest request) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;//insert statement
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(SELECT);
            stmt.setString(1,request.getEventID());
            rs = stmt.executeQuery();//execute the statement
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            ArrayList<String> columns = new ArrayList<>();
            if(rs.next()){
                for(int i = 1; i <= columnCount; i++){
                    columns.add(rs.getString(i));
                }
            }
            if(columns.size() == 0){db.closeConnection(false, conn);}
            else{//if we got a result
                db.closeConnection(true, conn);
                return columns;
            }
        }catch(SQLException e){
            e.printStackTrace();
            db.closeConnection(false, conn);
        }
        finally {
            DataBase.safeClose(rs);
            DataBase.safeClose(stmt);
        }
        return null;
    }

    /***
     * A method to get all of a user's ancestor's events
     *
     * @PARAM the userID we are getting all the events from
     * @RETURN gets the result of attempting to get all the users ancestor's events
     * @EXCEPTION throws SQL exception
     */
    public ArrayList<ArrayList<String>> getEvents(String userID){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;//insert statement
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(SELECT_STAR);
            stmt.setString(1,userID);
            rs = stmt.executeQuery();//execute the statement
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            ArrayList<ArrayList<String>> rows = new ArrayList<>();
            while(rs.next()){
                ArrayList<String> columns = new ArrayList<>();
                for(int i = 1; i <= columnCount; i++){
                    columns.add(rs.getString(i));
                }
                rows.add(columns);
            }
            if(rows.size() == 0){db.closeConnection(false, conn);}
            else{//if we got a result
                db.closeConnection(true, conn);
                return rows;
            }
        }catch(SQLException e){
            e.printStackTrace();
            db.closeConnection(false, conn);
        }
        finally {
            DataBase.safeClose(rs);
            DataBase.safeClose(stmt);
        }
        return null;
    }
//DO DELETES
}
