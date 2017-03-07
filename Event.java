package models;

/**
 * Created by tyler on 2/10/2017.
 * This object is an Event, IE: marriage, birth.
 */

public class Event {
    /**The unique event ID for this event*/
    private String eventID;
    /**The user this event belongs to*/
    private String descendent;
    /**The person this event belongs to*/
    private String personID;
    /**The latitude this event occurred at*/
    private Location location;
    /**The eventType of this event*/
    private String description;
    /**The year this event took place*/
    private String year;

    public Event(String eventID, String descendent, String personID, Location location, String eventType, String year) {
        this.eventID = eventID;
        this.descendent = descendent;
        this.personID = personID;
        this.location = location;
        this.description = eventType;
        this.year = year;
    }



    @Override
    public boolean equals(Object o) {
        if(this.getClass() != o.getClass()){return false;}
        Event other = (Event)o;
        if(!other.getDescendent().equals(this.getDescendent())){return false;}
        if(!other.getEventID().equals(this.getEventID())){return false;}
        if(!other.getEventType().equals(this.getEventType())){return false;}
        if(!other.getLocation().equals(this.getLocation())){return false;}
        if(!other.getYear().equals(this.getYear())){return false;}
        if(!other.getPersonID().equals(this.getPersonID())){return false;}
        return true;
    }

    public String getEventID() {
        return eventID;
    }

    public String getDescendent() {
        return descendent;
    }

    public String getPersonID() {
        return personID;
    }

    public Location getLocation() {
        return location;
    }

    public String getEventType() {
        return description;
    }

    public String getYear() {
        return year;
    }
}
