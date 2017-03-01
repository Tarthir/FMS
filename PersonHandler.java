package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import infoObjects.PeopleRequest;
import infoObjects.PersonRequest;

/**
 * Created by tyler on 2/13/2017.
 * Handles multiple people or one person requests and sends appropriate info to the correct service routines
 */

public class PersonHandler implements HttpHandler {

    public PersonHandler() {}

    @Override
    /**This method handles the getPerson request from the server*/
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
