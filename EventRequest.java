package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * Requests one particular event from a user's descendent
 */

public class EventRequest {
    /**The userID of this Event Request*/
    String eventID;

    public EventRequest(String eventID) {
        this.eventID = eventID;
    }

    public EventRequest() {
    }
    /**
     * Checks to see if request is valid
     * @RETURN BOOLEAN, whether request is valid or not
     * */
    public boolean isValidRequest(){
        return !eventID.isEmpty();
    }
    /**@RETURN the eventID of this Event Request*/
    public String getEventID() {
        return eventID;
    }
}
