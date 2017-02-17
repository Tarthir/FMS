package dataAccess;

import infoObjects.PeopleRequest;
import infoObjects.PersonRequest;
import models.Person;

/**
 * Created by tyler on 2/10/2017.
 */

public class PersonDao {

    public PersonDao() {
    }

    /***
     * A method to get a user's ancestor's info
     *
     * @PARAM userID, the ID for a specific user
     * @PARAM personID, the ID for a specific ancestor
     */
    Person getPerson(PersonRequest request){
        return null;
    }

    /***
     * A method to get all of a users ancestor's
     *
     * @PARAM userID, the ID for a specific user
     */
    Person[] getPeople(PeopleRequest request){
        return null;
    }
}
