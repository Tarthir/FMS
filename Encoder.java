package encode;


//import com.sun.corba.se.impl.orbutil.ObjectWriter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

//import javax.xml.ws.spi.http.HttpExchange;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.net.httpserver.HttpExchange;

import infoObjects.ClearResult;
import infoObjects.EventResult;
import infoObjects.EventsRequest;
import infoObjects.EventsResult;
import infoObjects.FillRequest;
import infoObjects.FillResult;
import infoObjects.LoadRequest;
import infoObjects.LoadResult;
import infoObjects.LoginRequest;
import infoObjects.LoginResult;
import infoObjects.PeopleRequest;
import infoObjects.PeopleResult;
import infoObjects.PersonRequest;
import infoObjects.PersonResult;
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
     * @PARAM String, input
     * @RETURN A result object
     * @EXCEPTION IOException
     * */
    public RegisterResult decodeReg(String input)throws IOException{
        return gson.fromJson(input, RegisterResult.class);
    }
    /**
     * This decodes java objects from JSON
     * @PARAM String, input
     * @RETURN A PersonResult object
     * @EXCEPTION IOException
     * */
    public PersonResult decodePersonResult(InputStream input)throws IOException{
        Reader reader = new InputStreamReader(input);
        return gson.fromJson(reader, PersonResult.class);
    }

    /**
     * This decodes java objects from JSON
     * @PARAM String, input
     * @RETURN A PersonResult object
     * @EXCEPTION IOException
     * */
    public EventResult decodeEventResult(InputStream input)throws IOException{
        Reader reader = new InputStreamReader(input);
        return gson.fromJson(reader, EventResult.class);
    }
    /**
     * This decodes java objects from JSON
     * @PARAM String, input
     * @RETURN A PersonResult object
     * @EXCEPTION IOException
     * */
    public EventsResult decodeEventsResult(InputStream input)throws IOException{
        Reader reader = new InputStreamReader(input);
        return gson.fromJson(reader, EventsResult.class);
    }

    /**
     * This decodes java objects from JSON
     * @PARAM String, input
     * @RETURN A PersonResult object
     * @EXCEPTION IOException
     * */
    public ClearResult decodeClearResult(InputStream input)throws IOException{
        Reader reader = new InputStreamReader(input);
        return gson.fromJson(reader, ClearResult.class);
    }

    /**
     * This decodes java objects from JSON
     * @PARAM String, input
     * @RETURN A PersonResult object
     * @EXCEPTION IOException
     * */
    public FillResult decodeFillResult(InputStream input)throws IOException{
        Reader reader = new InputStreamReader(input);
        return gson.fromJson(reader, FillResult.class);
    }

    /**
     * This decodes java objects from JSON
     * @PARAM String, input
     * @RETURN A PeopleResult object
     * @EXCEPTION IOException
     * */
    public PeopleResult decodePeopleResult(InputStream input)throws IOException{
        Reader reader = new InputStreamReader(input);
        return gson.fromJson(reader, PeopleResult.class);
    }

    /**
     * This decodes java objects from JSON
     * @PARAM String Json object
     * @RETURN LoginRequest object
     * @EXCEPTION IOException
     * */
    public LoginResult decodeLogin(InputStream input)throws IOException{
        Reader reader = new InputStreamReader(input);
        return gson.fromJson(reader, LoginResult.class);
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
     * @RETURN LoadRequest object
     * @EXCEPTION IOException
     * */
    public LoadRequest decodeLoad(HttpExchange exchange)throws IOException,IllegalArgumentException{
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        LoadDataHolder holder = gson.fromJson(reader, LoadDataHolder.class);//put into holder class
        //System.out.println(holder.getUsers()[0].getfName());
        if(holder.getEvents().length > 0 && holder.getPersons().length > 0 && holder.getUsers().length > 0) {
            //System.out.println(holder.getUsers()[0].getfName());
            return new LoadRequest(holder.getUsers(), holder.getPersons(), holder.getEvents());
        }
        else{
            throw new IllegalArgumentException("LoadData incomplete error");
        }
    }

    /**
     * This decodes java objects from JSON
     * @PARAM InputStream object
     * @RETURN LoadRequest object
     * @EXCEPTION IOException
     * */
    public LoadResult decodeLoadResult(InputStream input)throws IOException,IllegalArgumentException{
        Reader reader = new InputStreamReader(input);
        return gson.fromJson(reader, LoadResult.class);
    }
}
