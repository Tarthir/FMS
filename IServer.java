package server;

import functionObjects.EventResult;
import models.Person;
import functionObjects.*;

/**
 * Created by tyler on 2/10/2017.
 * The interface of our server classes
 */

public interface IServer {

    /***
     * A method to register a new user
     *
     * @PARAM request, the request to register a new user
     * @Return the result, successful or not of the register attempt
     */
    RegisterResult register(RegisterRequest request);

    /***
     * A method to login a user
     * @Param request, this object holds the info needed to successfully login
     * @Return the result, successful or not of the login attempt
     */
    LoginResult login(LoginRequest request);

    /***
     * A method to get a user's ancestor's info
     *
     * @PARAM request, the info needed to make a request on the database for a specific ancestor
     * @Return the result, a person object; successful or not of the getPerson attempt
     */
    Person getPerson(PersonRequest request);

    /***
     * A method to get all of a users ancestor's
     *
     * @PARAM request, the info needed to make a request on the database for all ancestors
     * @Return the array of people related to the User
     */
    Person[] getPeople(PeopleRequest request);

    /***
     * A method to get a specific event
     *
     * @PARAM request, the info needed to make a request on a particular event of a particular person of a user
     * @Return the result, an event object; successful or not of the getEvent Attempt attempt
     */
    EventResult getEvent(EventRequest request);

    /***
     * A method to get all of a user's ancestor's events
     *
     * @PARAM request, the info needed to make a request on the database for all events of a user's ancestor
     * @Return the result, an event object array; successful or not of the getEvents Attempt attempt
     */
    EventResult getEvents(EventsRequest request);

    /***
     * Calls a method to clear the database
     * @Return the result, successful or not of the clear attempt
     */
    ClearResult clear();

    /***
     * Calls a method to fill the database with new data
     * @Return the result, a FillResult object; successful or not of the fill attempt
     */
    FillResult fill(FillRequest request);
    /***
     *Calls a method to fill the database with new Data
     * @Param request, A object which contains the info needs to load up a database
     * @Return the result, an loadResult object; successful or not of the load attempt
     */
    LoadResult load(LoadRequest request);
}
