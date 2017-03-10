package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

import encode.Encoder;
import infoObjects.*;
import models.*;

/**
 * Created by tyler on 2/10/2017.
 * This class interacts with the client and the web API
 * Sends JSON to the web(Main server)
 */

public class FamilyMapServerProxy {


    private String serverHost; //"128.187.83.252";//need to connect in a different way
    private String serverPort; //"8080";
    private String authToken;

    public FamilyMapServerProxy() {
            this.serverHost = "localhost";//InetAddress.getLocalHost().getHostAddress();
            System.out.println("ServerHost is localhost\nPortNumber: ");
            Scanner scan = new Scanner(System.in);
            //this.serverPort ="8080";
            this.serverPort = scan.next();;//InetAddress.getLocalHost().getHostName();
            scan.close();
    }

    /***
     * A method to register a new user
     *
     * @PARAM request, the request to register a new user
     * @Return the result, successful or not of the register attempt
     */
    public RegisterResult register(RegisterRequest request)  {

        try {
            //IP address and port
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/user/register");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is a request body, dofor all except clear

            http.addRequestProperty("Accept", "application/json");
            Encoder encode = new Encoder();
            encode.encode(request,http.getOutputStream());
            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream respBody = http.getInputStream();

                String respData = readString(respBody);

                RegisterResult result = encode.decodeReg(respData);
                authToken = result.getAuthToken();
                return result;
            }
            else {

                InputStream resBody = http.getErrorStream();

                String respData = readString(resBody);

                System.out.println(respData);

                System.out.println("ERROR: " + http.getResponseMessage());

                RegisterResult result = encode.decodeReg(respData);
                return result;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * A method to login a user
     * @Param request, this object holds the info needed to successfully login
     * @Return the result, successful or not of the login attempt
     */
    public LoginResult login(LoginRequest request) {

        try {
            //IP address and port
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/user/login");

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);    // There is a request body, dofor all except clear

            // http.addRequestProperty("Authorization", );
            //http.addRequestProperty("Accept", "application/json");
            Encoder encode = new Encoder();
            encode.encode(request, http.getOutputStream());
            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream respBody = http.getInputStream();
                return encode.decodeLogin(respBody);
            } else {

                InputStream resBody = http.getErrorStream();
                LoginResult result =  encode.decodeLogin(resBody);
                authToken = result.getAuthToken();
                return result;
            }
            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
    }

    /***
     * A method to get a user's ancestor's info
     *
     * @PARAM request, the info needed to make a request on the database for a specific ancestor
     * @Return the result, a person object; successful or not of the getPerson attempt
     */
    public PersonResult getPerson(PersonRequest request) {

        try {
            //IP address and port
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/person/" + request.getPersonID());

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setDoOutput(true);	// There is a request body, do for all except clear
            http.setRequestMethod("GET");


            http.addRequestProperty("Authorization",authToken);
            http.addRequestProperty("Accept", "application/json");
            Encoder encode = new Encoder();
            encode.encode(request,http.getOutputStream());
            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return encode.decodePersonResult(http.getInputStream());
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return encode.decodePersonResult(http.getErrorStream());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * A method to get all of a users ancestor's
     *
     * @PARAM request, the info needed to make a request on the database for all ancestors
     * @Return the array of people related to the User
     */
    public PeopleResult getPeople(PeopleRequest request) {

        try {
            //IP address and port
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/person/");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setDoOutput(true);	// There is a request body, do for all except clear
            http.setRequestMethod("GET");

            http.addRequestProperty("Authorization",authToken ); //NEED TO SEND authorization
            http.addRequestProperty("Accept", "application/json");
            Encoder encode = new Encoder();
            encode.encode(request,http.getOutputStream());
            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                return encode.decodePeopleResult(http.getInputStream());
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return encode.decodePeopleResult(http.getErrorStream());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * A method to get a specific event
     *
     * @PARAM request, the info needed to make a request on a particular event of a particular person of a user
     * @Return the result, an event object; successful or not of the getEvent Attempt attempt
     */
    public EventResult getEvent(EventRequest request) {

        try {
            //IP address and port
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/event/" + request.getEventID());

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setDoOutput(true);	// There is a request body, dofor all except clear
            http.setRequestMethod("GET");


            http.addRequestProperty("Authorization",authToken );
            http.addRequestProperty("Accept", "application/json");
            Encoder encode = new Encoder();
            encode.encode(request,http.getOutputStream());

            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                return encode.decodeEventResult(http.getInputStream());
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return encode.decodeEventResult(http.getErrorStream());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * A method to get all of a user's ancestor's events
     *
     * @PARAM request, the info needed to make a request on the database for all events of a user's ancestor
     * @Return the result, an event object array; successful or not of the getEvents Attempt attempt
     */
    public EventsResult getEvents(EventsRequest request) {

        try {
            //IP address and port
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/event");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setDoOutput(true);	// There is a request body, dofor all except clear
            http.setRequestMethod("GET");

            http.addRequestProperty("Authorization", authToken);
            http.addRequestProperty("Accept", "application/json");
            Encoder encode = new Encoder();
            encode.encode(request,http.getOutputStream());

            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                return encode.decodeEventsResult(http.getInputStream());
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return encode.decodeEventsResult(http.getErrorStream());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * Calls a method to clear the database
     * @Return the result, successful or not of the clear attempt
     */
    public ClearResult clear() {

        try {
            //IP address and port
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/clear");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(false);	// There is a request body, dofor all except clear
            http.addRequestProperty("Accept", "application/json");

            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                return new Encoder().decodeClearResult(http.getInputStream());
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new Encoder().decodeClearResult(http.getErrorStream());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * Calls a method to fill the database with new data
     * @Return the result, a FillResult object; successful or not of the fill attempt
     */
    public FillResult fill(FillRequest request) {

        try {
            //IP address and port
            URL url = null;
            if(request.getNumOfGenerations() == 0) {
                url = new URL("http://" + serverHost + ":" + serverPort + "/fill/" + request.getUserName());
            }
            else{
                url = new URL("http://" + serverHost + ":" + serverPort + "/fill/" + request.getUserName() +"/" + request.getNumOfGenerations());
            }

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is a request body, dofor all except clear

            http.addRequestProperty("Accept", "application/json");
            Encoder encode = new Encoder();
            encode.encode(request,http.getOutputStream());
            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                return new Encoder().decodeFillResult(http.getInputStream());
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new Encoder().decodeFillResult(http.getErrorStream());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     *Calls a method to fill the database with new Data
     * @Param request, A object which contains the info needs to load up a database
     * @Return the result, an loadResult object; successful or not of the load attempt
     */
    public LoadResult load(LoadRequest request) {

        try {
            //IP address and port
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/load");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is a request body, dofor all except clear
            http.addRequestProperty("Accept", "application/json");

            Encoder encode = new Encoder();
            encode.encode(request,http.getOutputStream());
            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                return encode.decodeLoadResult(http.getInputStream());
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return encode.decodeLoadResult(http.getErrorStream());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    public String getAuthToken() {
        return authToken;
    }
}
