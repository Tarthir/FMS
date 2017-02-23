package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
    private String SELECT = "SELECT from events WHERE eventID = ?";
    private final String SELECT_STAR = "SELECT * from events";
    private String insertEvents = "insert into events (eventID, person, year, eventType) values (?,?,?,?)";
    private String insertTookPlaceAt = "insert into events (eventID, locationID) values (?,?)";
    public EventDao() {
        db = new DataBase();
    }

    /***
     * A method to get a specific event
     *
     * @PARAM request, the info for a specific event
     * @RETURN gets the result of attempting to get a specific event
     */
    public EventResult getEvent(EventRequest request){
        Connection conn = null;
        try {
            conn = db.openConnection();
            PreparedStatement stmt = conn.prepareStatement(SELECT);
            stmt.setString(1,request.getEventID());
            stmt.execute();//execute the statement

            db.closeConnection(true, conn);//how is the data getting in the database?
           // return EventResult = new EventResult(event,person);
        }catch(SQLException e){e.printStackTrace();}
        finally {
            if(conn != null) {
                db.closeConnection(false, conn);
            }
            return null;
        }
    }

    /***
     * A method to get all of a user's ancestor's events
     *
     * @PARAM request, the info to get multiple events
     * @RETURN gets the result of attempting to get all the users ancestor's events
     */
    public EventResult getEvents(EventsRequest request){
        //HOW DO I KNOW ITS GETTING THE RIGHT USER?
        Connection conn = null;
        try {
            conn = db.openConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(SELECT_STAR);
            db.closeConnection(true, conn);//how is the data getting in the database?
            // return EventResult =
            if(!conn.isClosed()){db.closeConnection(false, conn);}
        }catch(SQLException e){
            e.printStackTrace();
            db.closeConnection(false, conn);
        }
        finally {
            return null;
        }
    }
    /***
     * A method to insert all of a user's ancestor's events. Calls appropiate Daos
     *
     * @PARAM request, the info to get multiple events
     * @RETURN void
     */
    public void insertEvents(){

    }
//DO DELETES
}
