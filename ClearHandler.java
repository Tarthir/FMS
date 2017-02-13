package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by tyler on 2/13/2017.
 */

public class ClearHandler implements HttpHandler {

    public ClearHandler(){}
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
