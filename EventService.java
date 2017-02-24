package service;

import java.util.ArrayList;

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
     * @RETURN The result of attempting to get a particular event
     * */
    public EventResult getEvent(EventRequest request){
        EventDao eDao = new EventDao();
        EventsCreator create = new EventsCreator();
        ArrayList<String> result = eDao.getEvent(request);
        if(result != null){
            Event event = create.createEvent(result);
            return new EventResult(event.getUserID(),event,event.getPersonID());
        }
        return null;
    }
}
