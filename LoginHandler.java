package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import functionObjects.LoginRequest;

/**
 * Created by tyler on 2/13/2017.
 * Handles login requests from our client. Unpackages JSON object into appropriate object
 */
public class LoginHandler implements HttpHandler {
    private LoginRequest request;
    public LoginHandler(LoginRequest request) {
        this.request = request;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
    /**
     * @RETURN the login request
     * */
    public LoginRequest getRequest() {
        return request;
    }
}