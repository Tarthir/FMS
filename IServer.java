package server;

import models.Event;
import models.Person;

/**
 * Created by tyler on 2/10/2017.
 */

public interface IServer {

    /***
     * A method to register a new user
     *
     * @PARAM request, the request to register a new user
     */
    void register(String request);

    /***
     * A method to login a user
     */
    void login(String request);

    /***
     * A method to get a user's ancestor's info
     *
     * @PARAM userID, the ID for a specific user
     * @PARAM personID, the ID for a specific ancestor
     */
    Person getPerson(int userID, int personID);

    /***
     * A method to get all of a users ancestor's
     *
     * @PARAM userID, the ID for a specific user
     */
    Person[] getPeople(int userID);

    /***
     * A method to get a specific event
     *
     * @PARAM userID, the ID for a specific user
     * @PARAM eventID, the ID for a specific event
     */
    Event getEvent(int userID, int eventID);

    /***
     * A method to get all of a user's ancestor's events
     *
     * @PARAM userID, the ID for a specific user
     */
    Event[] getEvents(int userID);

    /***
     * Calls a method to clear the database
     */
    void clear();

    /***
     * Calls a method to fill the database with new data
     */
    void fill();
}
