package service;

import java.util.ArrayList;

import dataAccess.PersonDao;
import infoObjects.PersonRequest;
import models.Person;

/**
 * Created by tyler on 2/14/2017.
 * Called by PersonHandler to use our DAO to grab one person from our database
 */

public class PersonService {

    public PersonService() {
    }

    /***
     * Calls/Creates our DAO to interact with our database to get the requested person
     * @PARAM PersonRequest, the request to get one person from our database; related to oa user
     * @RETURN Gets the person requested
     */
    public Person getPerson(PersonRequest p) {
        PersonDao pDao = new PersonDao();
        ArrayList<String> person = pDao.getPerson(p);
        //SAME WITH PERSON SERVICE. YOU NEED TO USE A uDao and more PDaos to get the info for the user, and mother/father/spouse
        return null;
    }
}
