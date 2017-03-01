package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import infoObjects.EventRequest;
import infoObjects.EventsRequest;

/**
 * Created by tyler on 2/13/2017.
 * Handles the cases of requests for one event or multiple events and passes that info to the
 * appropriate service routines which use the DAOs to access the database
 */

public class EventHandler implements HttpHandler {
    /**Creates an object which handles getting one event from a person*/
    public EventHandler() {
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
