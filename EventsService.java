package service;

import infoObjects.EventsRequest;
import infoObjects.EventsResults;

/**
 * Created by tyler on 2/14/2017.
 * Called by EventHandler to grab ALL events through our DAO in our Database of a user
 */

public class EventsService {
    //EventsRequest request;
    public EventsService() {}
    /**Gets the result of trying to get all events of all of the users ancestors
     * @PARAM request, the request to get all events
     * @RETURN The result of attempting to get all events
     * */
    public EventsResults event(EventsRequest request){
        return null;
    }
}
