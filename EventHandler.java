package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by tyler on 2/13/2017.
 */

public class EventHandler implements HttpHandler {
    int userID,eventID;
    public EventHandler(int userID, int eventID) {
        this.userID = userID;
        this.eventID = eventID;
    }
    public EventHandler(int userID) {
        this.userID = userID;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
