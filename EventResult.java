package infoObjects;

import models.Event;
import models.Person;

/**
 * Created by tyler on 2/13/2017.
 * Contains the reference to one particular event of a users family member
 */

public class EventResult {
    /**
     * The userID of this event
     * */
    private String userID;
    /**The event object*/
    private Event event;
    /**The personID string*/
    private String personID;
    /**The exception thrown object*/
    private Exception e;


    public EventResult(String userID,Event event, String personID) {
        this.userID = userID;
        this.event = event;
        this.personID = personID;
    }

    public EventResult(Exception e) {
        this.e = e;
    }

    public Exception getE() {
        return e;
    }

    public Event getEvent() {
        return event;
    }

    public String getPersonID() {
        return personID;
    }
    public String getUserID(){return userID;}

}
