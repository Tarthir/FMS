package dataAccess;

import models.Person;

/**
 * Created by tyler on 2/10/2017.
 * This handles cases where we have to get results from more than one table at a time
 */

public class MultiDao {
    /**The obejcts returns by a multiDao*/
    private Object[] functionObjects;

    public MultiDao() {
    }
    /**
     * Makes people objects. Called by the
     * */
    public Person makePerson() {
        return null;
    }

    /***
     * A method to get all of a user's ancestor's events
     *
     * @PARAM request, the info to get multiple events
     * @RETURN connects the location of the events and the event in the database
     */
    public void insertTookPlaceAt(){

    }

    /***
     * A method to get all of a user's ancestor's events
     *
     * @PARAM request, the info to get multiple events
     * @RETURN connects the location of the events and the event in the database
     */
    public void insertBelongsTo(){

    }
}
