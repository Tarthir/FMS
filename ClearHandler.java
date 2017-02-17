package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by tyler on 2/13/2017.
 * Handles clear commands that come in by calling/Creating a ClearService obj which will use the DAOs to access the database
 */

public class ClearHandler implements HttpHandler {

    public ClearHandler(){}
    @Override
    /**This method handles the clear request from the server*/
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
