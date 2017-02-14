package functionObjects;

import models.Event;
import models.Person;

/**
 * Created by tyler on 2/13/2017.
 * Gets the results of our query of the database for all the events of a users family
 */

public class EventsResults {
    /**The person array*/
    private Person[] people;
    /**The events array*/
    private Event[] events;

    public EventsResults(Person[] people, Event[] events) {
        this.people = people;
        this.events = events;
    }

    /**@RETURN The people array*/
    public Person[] getPeople() {
        return people;
    }
    /**@RETURN The events array*/
    public Event[] getEvents() {
        return events;
    }
}
