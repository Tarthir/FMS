package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dataAccess.AuthTokenDao;
import dataAccess.EventsCreator;
import dataAccess.EventDao;
import infoObjects.EventsResult;
import infoObjects.EventsRequest;
import models.Event;

/**
 * Created by tyler on 2/14/2017.
 * Called by EventHandler to grab ALL events through our DAO in our Database of a user
 */

public class EventsService {
    public EventsService() {}
    /**Gets the result of trying to get all events of all of the users ancestors
     * @PARAM request, the request to get all events
     * @RETURN The result of attempting to get all events
     * */
    public EventsResult getEvents(EventsRequest request) {
        try {
            EventDao eDao = new EventDao();
            EventsCreator create = new EventsCreator();
            AuthTokenDao authDao = new AuthTokenDao();
            ;
            String userID = authDao.getUserIDFromAuthToken(request.getAuthToken());
            ArrayList<ArrayList<String>> result = eDao.getEvents(userID);
            if (result != null) {
                ArrayList<Event> events = create.createEvents(result);
                return new EventsResult(events);
            }
            else{ return null;}//found nothing
        }catch(SQLException e){
            return new EventsResult(e);//SQL error
        }
    }
}
