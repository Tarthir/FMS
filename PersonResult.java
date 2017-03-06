package infoObjects;

import models.Person;

/**
 * Created by tyler on 3/6/2017.
 * THe result of querying aperson from the database
 */

public class PersonResult {
    /**Array of people that is our result*/
    private Person person;
    /**An exception that may have been thrown*/
    private Exception e;
    /**Our exception result*/
    private String message;

    public PersonResult(Person person) {
        this.person = person;
    }

    public PersonResult(Exception e) {
        this.e = e;
        message = e.getMessage();
    }

    public PersonResult() {
    }
    public Person getPerson() {
        return person;
    }

    public Exception getE() {
        return e;
    }
}
