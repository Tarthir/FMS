package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * Requests all the events pertaining to a users family
 */

public class EventsRequest {
    /**The UserID of this event Request*/
    int userID;

    public EventsRequest(int userID) {
        this.userID = userID;
    }
    /**@RETURN The UserID of this event Request*/
    public int getUserID() {
        return userID;
    }
}
