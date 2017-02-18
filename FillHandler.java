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
    /**The request to fill our database*/
    private FillRequest request;
    //HANDLERS WILL TAKE IN JSON OBJECTS
    public FillHandler(FillRequest request) {this.request = request;}

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
    /**
     * @RETURN gets the fill request
     * */
    public FillRequest getRequest() {
        return request;
    }
}
