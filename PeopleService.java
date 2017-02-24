package service;

import java.util.ArrayList;

import dataAccess.AuthTokenDao;
import dataAccess.PersonDao;
import infoObjects.PeopleRequest;
import models.Person;
import models.PeopleMaker;

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
    public ArrayList<Person> getPeople(PeopleRequest p) {
        PersonDao pDao = new PersonDao();
        AuthTokenDao authDao = new AuthTokenDao();
        PeopleMaker create = new PeopleMaker();
        String userID = authDao.getUserIDFromAuthToken(p.getAuth().getAuthToken());
        ArrayList<ArrayList<String>> allPeople = pDao.getPeople(userID);
        return create.createPeople(allPeople);
    }


}
