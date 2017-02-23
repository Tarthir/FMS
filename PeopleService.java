package service;

import java.util.ArrayList;

import dataAccess.PersonDao;
import infoObjects.PeopleRequest;
import models.Person;

/**
 * Created by tyler on 2/14/2017.
 * Called by our PersonHandler to grab all the people related to a user
 */

public class PeopleService {

    public PeopleService() {
    }
    /***
     * Calls/Creates our DAOs to interact with our database and get the requested people
     * @PARAM PeopleRequest, the request to find all people related to a user
     * @Return All people related to the user are returned
     */
    public Person[] getPeople(PeopleRequest p) {
        PersonDao pDao = new PersonDao();
        ArrayList<ArrayList<String>> allPeople = pDao.getPeople(p);
        //SAME WITH PERSON SERVICE. YOU NEED TO USE A uDao and more PDaos to get the info for the user, and mother/father/spouse
        return null;
    }
}
