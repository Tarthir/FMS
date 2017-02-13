package server;
import com.sun.net.httpserver.HttpServer;

import models.Person;
import models.Event;


/**
 * Created by tyler on 2/10/2017.
 */

public class ServerFacade implements IServer {
    HttpServer server;

   // static void main(){

   // }
    /***
     * Registers a new user
     * @PARAM request, the request to register a new user
     */
    public void register(String request) {
        RegisterHandler r = new RegisterHandler();
    }
    /***
     * Logs in a user
     * @PARAM request, the request to login a user
     */
    public void login(String request) {
        LoginHandler login = new LoginHandler();
    }
    /***
     * Gets a user's ancestor's info
     * @PARAM userID, the ID for a specific user
     * @PARAM personID, the ID for a specific ancestor
     */
    public Person getPerson(int userID,int personID) {
        PersonHandler person = new PersonHandler();
        return null;
    }
    /***
     * Gets all of a users ancestor's
     * @PARAM userID, the ID for a specific user
     */
    public Person[] getPeople(int userID) {
        PersonHandler person = new PersonHandler();
        return null;
    }
    /***
     * Calls a method to get a specific event
     * @PARAM userID, the ID for a specific user
     * @PARAM eventID, the ID for a specific event
     */
    public Event getEvent(int userID, int eventID) {
        EventHandler event = new EventHandler();
        return null;
    }
    /***
     * Calls a method to get all of a user's ancestor's events
     * @PARAM userID, the ID for a specific user
     */
    public Event[] getEvents(int userID) {
        EventHandler events = new EventHandler();
        return null;
    }
    /***
     * Clears the database
     */
    public void clear() {
        ClearHandler clear = new ClearHandler();
    }
    /***
     * Fills the database with new data
     */
    public void fill() {
        FillHandler fill = new FillHandler();
    }
}
