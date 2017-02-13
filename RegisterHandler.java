package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by tyler on 2/13/2017.
 */

public class RegisterHandler implements HttpHandler{
    private String request;

    public RegisterHandler(String request){
        this.request = request;
    }
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
