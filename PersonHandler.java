package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by tyler on 2/13/2017.
 */

public class PersonHandler implements HttpHandler {
    int userID;
    int eventID;
    public PersonHandler(int userID) {
        this.userID = userID;
    }
    public PersonHandler(int userID, int eventID) {
        this.userID = userID;
        this.eventID=eventID;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
