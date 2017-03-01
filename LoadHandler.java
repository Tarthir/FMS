package handler;

import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import infoObjects.LoadRequest;

/**
 * Created by tyler on 2/14/2017.
 * This class handles calls on the server to load preset information into the database
 */

public class LoadHandler implements HttpHandler{

    public LoadHandler() {
    }

    @Override
    /**This method handles the load request from the server*/
    public void handle(com.sun.net.httpserver.HttpExchange httpExchange) throws IOException {

    }
}
