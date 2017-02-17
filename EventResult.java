package infoObjects;

import models.Event;
import models.Person;

/**
 * Created by tyler on 2/13/2017.
 * Contains the reference to one particular event of a users family member
 */

public class EventResult {
    private Event event;
    private Person person;


    public EventResult(Event event, Person person) {
        this.event = event;
        this.person = person;
    }

    public Event getEvent() {
        return event;
    }

    public Person getPerson() {
        return person;
    }

}
