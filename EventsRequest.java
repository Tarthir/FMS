package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * Requests all the events pertaining to a users family
 */

public class EventsRequest {
    /**The authToken of the user are related to*/
    private String authToken;

    public EventsRequest(String authToken) {
        this.authToken = authToken;
    }
    /**@RETURN The authToken of the user are related to*/
    public String getAuthToken() {
        return authToken;
    }
}
