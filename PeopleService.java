package service;

import java.util.ArrayList;

import dataAccess.AuthTokenDao;
import dataAccess.PersonDao;
import infoObjects.PeopleRequest;
import infoObjects.PeopleResult;
import dataAccess.PeopleCreator;

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
    public PeopleResult getPeople(PeopleRequest p) throws Exception{
        PeopleCreator create = new PeopleCreator();
        PersonDao pDao = new PersonDao();
        AuthTokenDao authDao = new AuthTokenDao();
        String userID = authDao.getUserIDFromAuthToken(p.getAuthToken());
        ArrayList<ArrayList<String>> allPeople = pDao.getPeople(userID);
        return new PeopleResult(create.createPeople(allPeople));
    }


}
