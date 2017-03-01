package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import infoObjects.LoginRequest;

/**
 * Created by tyler on 2/13/2017.
 * Handles login requests from our client. Unpackages JSON object into appropriate object
 */
public class LoginHandler implements HttpHandler {
    public LoginHandler( ) {
    }

    @Override
    /**This method handles the login request from the server*/
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}