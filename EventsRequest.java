package functionObjects;

/**
 * Created by tyler on 2/13/2017.
 * Requests all the events pertaining to a users family
 */

public class EventsRequest {
    int userID;

    public EventsRequest(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }
}
