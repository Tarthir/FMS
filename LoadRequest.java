package functionObjects;

import models.Event;
import models.Person;
import models.User;

/**
 * Created by tyler on 2/13/2017.
 * Contains the info to load up our database
 */

public class LoadRequest {
    private User[] users;
    private Person[] persons[];
    private Event[] events;

    public LoadRequest(User[] users, Person[][] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public User[] getUsers() {
        return users;
    }

    public Person[][] getPersons() {
        return persons;
    }

    public Event[] getEvents() {
        return events;
    }
}
