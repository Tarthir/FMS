package infoObjects;

import java.util.ArrayList;

import models.Event;
import models.Person;

/**
 * Created by tyler on 2/13/2017.
 * Gets the results of our query of the database for all the events of a users family
 */

public class EventsResult {
    /**The array of events*/
    ArrayList<Event> events;
    /**The exception thrown object*/
    private Exception e;

    public EventsResult(ArrayList<Event> events) {
        this.events = events;
    }

    public EventsResult(Exception e) {
        this.e = e;
    }

    public EventsResult() {
    }

    /**@RETURN The events array*/
    public ArrayList<Event> getEvents() {
        return events;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
}
