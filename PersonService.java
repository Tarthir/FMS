package service;

import infoObjects.PersonRequest;
import models.Person;

/**
 * Created by tyler on 2/14/2017.
 * Called by PersonHandler to use our DAO to grab one person from our database
 */

public class PersonService {
    //private PersonRequest person;

    public PersonService(PersonRequest person) {
        //this.person = person;
    }

    /***
     * Calls/Creates our DAO to interact with our database to get the requested person
     * @PARAM PersonRequest, the request to get one person from our database; related to oa user
     * @RETURN Gets the person requested
     */
    public Person getPerson(PersonRequest p) {
        return null;
    }
}
