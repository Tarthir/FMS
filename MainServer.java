package server;
import models.Person;
import infoObjects.*;

/**
 * Created by tyler on 2/13/2017.
 * This class calls our handlers to process requests
 * Gets the IP address and JSON and sends data to appropriate Handler
 * Uses HttpServer class
 */

public class MainServer {

    /***
     * A method to register a new user
     *
     * @PARAM request, the request to register a new user
     * @Return the result, successful or not of the register attempt
     */
    public RegisterResult register(RegisterRequest request) {
        return null;
    }

    /***
     * A method to login a user
     * @Param request, this object holds the info needed to successfully login
     * @Return the result, successful or not of the login attempt
     */
    public LoginResult login(LoginRequest request) {
        return null;
    }

    /***
     * A method to get a user's ancestor's info
     *
     * @PARAM request, the info needed to make a request on the database for a specific ancestor
     * @Return the result, a person object; successful or not of the getPerson attempt
     */
    public Person getPerson(PersonRequest request) {
        return null;
    }

    /***
     * A method to get all of a users ancestor's
     *
     * @PARAM request, the info needed to make a request on the database for all ancestors
     * @Return the array of people related to the User
     */
    public Person[] getPeople(PeopleRequest request) {
        return new Person[0];
    }

    /***
     * A method to get a specific event
     *
     * @PARAM request, the info needed to make a request on a particular event of a particular person of a user
     * @Return the result, an event object; successful or not of the getEvent Attempt attempt
     */
    public EventResult getEvent(EventRequest request) {
        return null;
    }

    /***
     * A method to get all of a user's ancestor's events
     *
     * @PARAM request, the info needed to make a request on the database for all events of a user's ancestor
     * @Return the result, an event object array; successful or not of the getEvents Attempt attempt
     */
    public EventResult getEvents(EventsRequest request) {
        return null;
    }

    /***
     * Calls a method to clear the database
     * @Return the result, successful or not of the clear attempt
     */
    public ClearResult clear() {
        return null;
    }

    /***
     * Calls a method to fill the database with new data
     * @Return the result, a FillResult object; successful or not of the fill attempt
     */
    public FillResult fill(FillRequest request) {
        return null;
    }

    /***
     *Calls a method to fill the database with new Data
     * @Param request, A object which contains the info needs to load up a database
     * @Return the result, an loadResult object; successful or not of the load attempt
     */
    public LoadResult load(LoadRequest request) {
        return null;
    }
}
