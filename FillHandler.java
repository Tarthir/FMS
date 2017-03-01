package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import infoObjects.FillRequest;

/**
 * Created by tyler on 2/13/2017.
 * Called by our Main server. We get our requests and pass them off to the FillService class which will use the
 * DAOs to access the database
 */

public class FillHandler implements HttpHandler {
    public FillHandler() {}

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
