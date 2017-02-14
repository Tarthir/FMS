package handler;

import functionObjects.EventRequest;
import functionObjects.EventResult;

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
    public EventResult event(EventRequest request){
        return null;
    }
}
