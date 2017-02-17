package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import infoObjects.RegisterRequest;

/**
 * Created by tyler on 2/13/2017.
 * Handles requests to register users
 */

public class RegisterHandler implements HttpHandler{
    /**The onject holding the information which is needed to attempt to register a new user*/
    private RegisterRequest request;

    public RegisterHandler(RegisterRequest request) {
        this.request = request;
    }

    @Override
    /**This method handles the register request from the server*/
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
