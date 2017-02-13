package functionObjects;

/**
 * Created by tyler on 2/13/2017.
 * Requests one particular event from a user's descendent
 */

public class EventRequest {
    int userID, eventID;

    public EventRequest(int userID, int eventID) {
        this.userID = userID;
        this.eventID = eventID;
    }

    public int getUserID() {
        return userID;
    }

    public int getEventID() {
        return eventID;
    }
}
