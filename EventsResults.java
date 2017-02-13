package functionObjects;

import models.Event;
import models.Person;

/**
 * Created by tyler on 2/13/2017.
 * Gets the results of our query of the database for all the events of a users family
 */

public class EventsResults {
    private Person[] people;
    private Event[] events;

    public EventsResults(Person[] people, Event[] events) {
        this.people = people;
        this.events = events;
    }
    public Person[] getPeople() {
        return people;
    }

    public void setPeople(Person[] people) {
        this.people = people;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
