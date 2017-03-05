package handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import dataAccess.MultiDao;
import encode.Encoder;
import infoObjects.PeopleRequest;
import infoObjects.PersonRequest;
import models.Person;
import service.PersonService;

/**
 * Created by tyler on 2/13/2017.
 * Handles multiple people or one person requests and sends appropriate info to the correct service routines
 */

public class PersonHandler implements HttpHandler {

    public PersonHandler() {
    }

    @Override
    /**This method handles the getPerson request from the server*/
    public void handle(HttpExchange exchange) throws IOException {
        Encoder encode = new Encoder();
        OutputStream respBody = exchange.getResponseBody();
        PersonService service = new PersonService();
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                String authToken = exchange.getRequestHeaders().getFirst("Authorization");
                String[] params  = exchange.getRequestURI().toString().split("/");

                PersonRequest request = new PersonRequest(params[2]);
                Person person = service.getPerson(request, authToken);

                if (person == null) {//if no person was made because the given ID isnt in our Databse
                    throw new IllegalArgumentException("Invalid PersonID");
                }
                else {
                    encode.encode(person, respBody);
                }
                respBody.close();
            }
            else {
                //if we got an Error in the request we will reach here
                //System.out.println("errorPerson");
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                encode.encode("Should get GET not POST", respBody);
                exchange.getRequestBody().close();
            }
        }
        catch(IllegalArgumentException e){
            // System.out.println("errorPersonIllegal");
            encode.encode(e.getMessage(),respBody);
            respBody.close();
        }
        catch (SQLException e) {
            encode.encode(e.getMessage(), respBody);
            respBody.close();
        }
        catch (IOException e) {
            //System.out.println("errorPersonIO");
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            encode.encode(e.getMessage(),respBody);
            respBody.close();
            //e.printStackTrace();
        }

    }
}
