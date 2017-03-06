package service;

import com.sun.org.apache.xpath.internal.operations.Mult;

import java.sql.SQLException;
import java.util.ArrayList;

import dataAccess.AuthTokenDao;
import dataAccess.MultiDao;
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
    public PeopleResult getPeople(PeopleRequest request) {
        AuthTokenDao aDao = new AuthTokenDao();
        PeopleCreator create = new PeopleCreator();
        ArrayList<ArrayList<String>> allPeople = new ArrayList<>();
        try {
            if(aDao.validateAuthToken(request.getAuthToken())){
                PersonDao pDao = new PersonDao();

                String userID = aDao.getUserIDFromAuthToken(request.getAuthToken());
                allPeople = pDao.getPeople(userID);

                if(allPeople == null){//if no people found
                    return new PeopleResult(new Exception("No People found under this userName"));
                }
            }
        }
        catch(SQLException e){return new PeopleResult(e);}
        return new PeopleResult(create.createPeople(allPeople));
    }


}
