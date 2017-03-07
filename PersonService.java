package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dataAccess.AuthTokenDao;
import dataAccess.MultiDao;
import dataAccess.PersonDao;
import infoObjects.PersonRequest;
import dataAccess.PeopleCreator;
import infoObjects.PersonResult;
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
    public PersonResult getPerson(PersonRequest p, String authToken){
        try {
            PersonDao pDao = new PersonDao();
            AuthTokenDao aDao = new AuthTokenDao();
            PeopleCreator maker = new PeopleCreator();
            if (aDao.validateAuthToken(authToken)) {//if is valid authtoken
                ArrayList<String> personData = pDao.getPerson(p);
                return new PersonResult(maker.createPerson(personData));
            } else {
                return new PersonResult("Invalid Authtoken");
            }
        }catch(SQLException e){
            return new PersonResult(e.getMessage());
        }
    }
}
