package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dataAccess.MultiDao;
import dataAccess.PersonDao;
import infoObjects.PersonRequest;
import dataAccess.PeopleCreator;
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
     * @PARAM String, an authtoken
     * @RETURN Gets the person requested
     */
    public Person getPerson(PersonRequest p,String authToken) throws SQLException,IllegalArgumentException{
        PersonDao pDao = new PersonDao();
        MultiDao multi = new MultiDao();
        PeopleCreator maker = new PeopleCreator();
        if(multi.ValidateAuthToken(p.getPersonID(),authToken)) {
            ArrayList<String> personData = pDao.getPerson(p);
            return maker.createPerson(personData);
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}
