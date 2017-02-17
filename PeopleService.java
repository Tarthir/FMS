package service;

import infoObjects.PeopleRequest;
import models.Person;

/**
 * Created by tyler on 2/14/2017.
 * Called by our PersonHandler to grab all the people related to a user
 */

public class PeopleService {
    //private PeopleRequest people;

    public PeopleService(PeopleRequest people) {
        //this.people = people;
    }
    /***
     * Calls/Creates our DAO to interact with our database and get the requested people
     * @PARAM PeopleRequest, the request to find all people related to a user
     * @Return All people related to the user are returned
     */
    public Person[] getPeople(PeopleRequest p) {
        return null;
    }
}
