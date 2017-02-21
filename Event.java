package models;

/**
 * Created by tyler on 2/10/2017.
 * This object is an Event, IE: marriage, birth.
 */

public class Event {
    /**The unique event ID for this event*/
    private String eventID;
    /**The user this event belongs to*/
    private User descendant;
    /**The person this event belongs to*/
    private Person person;
    /**The latitude this event occurred at*/
    private Location location;
    /**The eventType of this event*/
    private String eventType;
    /**The year this event took place*/
    private String year;

    public Event(String eventID, User descendant, Person person, Location location, String eventType, String year) {
        this.eventID = eventID;
        this.descendant = descendant;
        this.person = person;
        this.location = location;
        this.eventType = eventType;
        this.year = year;
    }
    /**@RETURN get the eventID of this event*/
    public String getEventID() {
        return eventID;
    }
    /**@RETURN get the descendant related to this event*/
    public User getDescendant() {
        return descendant;
    }
    /**@RETURN get the person related to this event*/
    public Person getPerson() {
        return person;
    }
    /**@RETURN get the location of this event*/
    public Location getLocation() {
        return location;
    }
    /**@RETURN get the eventType of this event*/
    public String getEventType() {
        return eventType;
    }
    /**@RETURN get the year of this event*/
    public String getYear() {
        return year;
    }
}
