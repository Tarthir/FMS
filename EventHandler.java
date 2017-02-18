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
    /**The request to get one event*/
    EventRequest request;
    /**The request to get multiple events*/
    EventsRequest requests;
    /**Creates an object which handles getting one event from a person*/
    public EventHandler(EventRequest request) {
        this.request = request;
    }
    /**Creates an object which handles getting all events from all the people related to the user*/
    public EventHandler(EventsRequest requests) {
        this.requests = requests;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
    /**@RETURN Gets The request to get one event*/
    //public EventRequest getRequest() {
   //     return request;
   // }
    /**@RETURN Gets The request to get multiple events*/
   // public EventsRequest getRequests() {
       // return requests;
   // }
}
