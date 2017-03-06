package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dataAccess.AuthTokenDao;
import dataAccess.EventsCreator;
import dataAccess.EventDao;
import infoObjects.EventRequest;
import infoObjects.EventResult;
import models.Event;

/**
 * Created by tyler on 2/14/2017.
 * Called by the EventHandler class to get a particular event through our DAO in our database
 */

public class EventService {
    //EventRequest request;
    public EventService() {}
    /**Gets the result of trying to get one particular event
     * @PARAM request, the request to get a particular event
     * @PARAM String, an authToken
     * @RETURN The result of attempting to get a particular event. May return an error
     * */
    public EventResult getEvent(EventRequest request,String authToken){
        try {
            EventDao eDao = new EventDao();
            EventsCreator create = new EventsCreator();
            AuthTokenDao aDao = new AuthTokenDao();
            Event event = null;
            if (aDao.validateAuthToken(authToken)){
                event = create.createEvent(eDao.getEvent(request));
                if (event != null) {
                    return new EventResult(event.getDescendent(), event, event.getPersonID());
                }
            }
            else{
                return new EventResult(new Exception("Invalid AuthToken"));
            }
        }catch(SQLException e){
            return new EventResult(e);
        }
        return new EventResult(new Exception("No such Event found"));
    }
}
