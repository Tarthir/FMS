package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import functionObjects.RegisterRequest;

/**
 * Created by tyler on 2/13/2017.
 */

public class RegisterHandler implements HttpHandler{
    private RegisterRequest request;

    public RegisterHandler(RegisterRequest request) {
        this.request = request;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
