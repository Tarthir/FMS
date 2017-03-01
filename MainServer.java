package server;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.xml.crypto.Data;

import dataAccess.DataBase;
import handler.ClearHandler;
import handler.EventHandler;
import handler.FillHandler;
import handler.IndexHandler;
import handler.LoadHandler;
import handler.LoginHandler;
import handler.PersonHandler;
import handler.RegisterHandler;
import models.Person;
import infoObjects.*;

/**
 * Created by tyler on 2/13/2017.
 * This class calls our handlers to process requests
 * Gets the IP address and JSON and sends data to appropriate Handler
 * Uses HttpServer class
 */

public class MainServer {

    private static final int MAX_WAITING_CONNECTIONS = 10;

    private HttpServer server;
    /**
     * Runs our server
     * */
    private void run(String portNumber) {
        //System.out.println("Initializing HTTP Server");
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null); // use the default executor

        server.createContext("/uer/register",new RegisterHandler());
        server.createContext("/user/login",new LoginHandler());
        server.createContext("/fill",new FillHandler());//gives username/generations
        server.createContext("/load",new LoadHandler());
        server.createContext("/person",new PersonHandler());//gives all people
        server.createContext("/event",new EventHandler());//gives personID
        server.createContext("/",new IndexHandler());//for CSS and index handler
        DataBase db = new DataBase();
        db.createTables(db.openConnection());//initialize the DB

        server.start();
    }

    public static void main(String[] args) {
        //String portNumber = args[0];
        new MainServer().run("8080");
    }
}
