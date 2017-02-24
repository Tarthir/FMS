package models;

/**
 * Created by tyler on 2/10/2017.
 * This object is an Event, IE: marriage, birth.
 */

public class Event {
    /**The unique event ID for this event*/
    private String eventID;
    /**The user this event belongs to*/
    private String userID;
    /**The person this event belongs to*/
    private String personID;
    /**The latitude this event occurred at*/
    private String locationID;
    /**The eventType of this event*/
    private String eventType;
    /**The year this event took place*/
    private String year;

    public Event(String eventID, String userID, String personID, String year, String eventType, String locationID) {
        this.eventID = eventID;
        this.userID = userID;
        this.personID = personID;
        this.locationID = locationID;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventID() {
        return eventID;
    }

    public String getUserID() {
        return userID;
    }

    public String getPersonID() {
        return personID;
    }

    public String getLocationID() {
        return locationID;
    }

    public String getEventType() {
        return eventType;
    }

    public String getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if(this.getClass() != o.getClass()){return false;}
        Event other = (Event)o;
        if(!other.getUserID().equals(this.getUserID())){return false;}
        if(!other.getEventID().equals(this.getEventID())){return false;}
        if(!other.getEventType().equals(this.getEventType())){return false;}
        if(!other.getLocationID().equals(this.getLocationID())){return false;}
        if(!other.getYear().equals(this.getYear())){return false;}
        if(!other.getPersonID().equals(this.getPersonID())){return false;}
        return true;
    }
}
