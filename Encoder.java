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
import java.util.Iterator;
import java.util.List;

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

}
