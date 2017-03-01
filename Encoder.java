package encode;


//import com.sun.corba.se.impl.orbutil.ObjectWriter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;

//import javax.xml.ws.spi.http.HttpExchange;
import com.sun.net.httpserver.HttpExchange;

import infoObjects.EventsRequest;
import infoObjects.EventsResult;
import infoObjects.FillRequest;
import infoObjects.FillResult;
import infoObjects.LoadRequest;
import infoObjects.LoginRequest;
import infoObjects.LoginResult;
import infoObjects.PeopleRequest;
import infoObjects.PeopleResult;
import infoObjects.PersonRequest;
import infoObjects.RegisterRequest;
import infoObjects.RegisterResult;
import models.Location;
import models.Person;

/**
 * Created by tyler on 2/10/2017.
 * Decodes JSON to java objects and encodes java objects into JSON
 */

public class Encoder {
    private Gson gson = new Gson();
    public Encoder() {
    }
    /**
     * This encodes java objects into JSON
     * @RETURN JSon String
     * */
    public void encode(Object obj,OutputStream respBody) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(respBody);
        writer.write(gson.toJson(obj));
        writer.flush();
    }



    /***
     * This decodes java objects from JSON
     * @PARAM HttpExchange object
     * @RETURN String object
     * @EXCEPTION IOException
     * */
    public String decodeString(HttpExchange exchange)throws IOException{
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, String.class);
    }
   /***
     * This decodes java objects from JSON
    * @PARAM HttpExchange object
     * @RETURN Person object
    * @EXCEPTION IOException
    * */
    public Person decodePersonObj(HttpExchange exchange) throws IOException {
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, Person.class);
    }
    /***
     * This decodes java objects from JSON
     * @PARAM HttpExchange object
     * @RETURN Location object
     * @EXCEPTION IOException
     * */
    public Location decodeLocation(HttpExchange exchange)throws IOException{
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, Location.class);
    }
    /**
     * This decodes java objects from JSON
     * @PARAM HttpExchange object
     * @RETURN RegisterRequest object
     * @EXCEPTION IOException
     * */
    public RegisterRequest decodeReg(HttpExchange exchange)throws IOException{
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, RegisterRequest.class);
    }
    /**
     * This decodes java objects from JSON
     * @PARAM HttpExchange object
     * @RETURN LoginRequest object
     * @EXCEPTION IOException
     * */
    public LoginRequest decodeLogin(HttpExchange exchange)throws IOException{
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, LoginRequest.class);
    }
    /**
     * This decodes java objects from JSON
     * @PARAM HttpExchange object
     * @RETURN FillRequest object
     * @EXCEPTION IOException
     * */
    public FillRequest decodeFill(HttpExchange exchange)throws IOException{
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, FillRequest.class);
    }
    /**
     * This decodes java objects from JSON
     * @PARAM HttpExchange object
     * @RETURN PeopleRequest object
     * @EXCEPTION IOException
     * */
    public PeopleRequest decodePeople(HttpExchange exchange)throws IOException{
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, PeopleRequest.class);
    }
    /**
     * This decodes java objects from JSON
     * @PARAM HttpExchange object
     * @RETURN PersonRequest object
     * @EXCEPTION IOException
     * */
    public PersonRequest decodePerson(HttpExchange exchange)throws IOException{
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, PersonRequest.class);
    }
    /**
     * This decodes java objects from JSON
     * @PARAM HttpExchange object
     * @RETURN PersonRequest object
     * @EXCEPTION IOException
     * */
    public EventsRequest decodeEvents(HttpExchange exchange)throws IOException{
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, EventsRequest.class);
    }
    /**
     * This decodes java objects from JSON
     * @PARAM HttpExchange object
     * @RETURN PersonRequest object
     * @EXCEPTION IOException
     * */
    public PersonRequest decodeEvent(HttpExchange exchange)throws IOException{
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, PersonRequest.class);
    }
    /**
     * This decodes java objects from JSON
     * @PARAM HttpExchange object
     * @RETURN LoadRequest object
     * @EXCEPTION IOException
     * */
    public LoadRequest decodeLoad(HttpExchange exchange)throws IOException{
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        return gson.fromJson(reader, LoadRequest.class);
    }

}
