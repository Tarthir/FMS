package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * Requests one particular event from a user's descendent
 */

public class EventRequest {
    /**The userID of this Event Request*/
    int eventID;

    public EventRequest(int eventID) {
        this.eventID = eventID;
    }
    /**@RETURN the eventID of this Event Request*/
    public int getEventID() {
        return eventID;
    }
}
