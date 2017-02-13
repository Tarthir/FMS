package models;

/**
 * Created by tyler on 2/10/2017.
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

    public Event(String eventID, User descendant, Person person, String latitude,
                 String longitude, Location location, String eventType, String year) {
        this.eventID = eventID;
        this.descendant = descendant;
        this.person = person;
        this.location = location;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public User getDescendant() {
        return descendant;
    }

    public void setDescendant(User descendant) {
        this.descendant = descendant;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
