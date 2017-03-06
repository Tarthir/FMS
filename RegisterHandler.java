package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import encode.Encoder;
import encode.FemaleNamesHolder;
import encode.JsonData;
import encode.MaleNamesHolder;
import encode.SurNamesHolder;
import infoObjects.RegisterRequest;
import infoObjects.RegisterResult;
import service.RegisterService;

/**
 * Created by tyler on 2/13/2017.
 * Handles requests to register users
 */

public class RegisterHandler implements HttpHandler {

    public RegisterHandler() {

    }

    @Override
    /**This method handles the register request from the server*
     * @PARAM HttpExchange variable holding required info
     * @RETURN void
     */
    public void handle(HttpExchange exchange) throws IOException {
        Encoder encode = new Encoder();
        OutputStream respBody = exchange.getResponseBody();
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                RegisterService service = new RegisterService();
                RegisterRequest request = encode.decodeReg(exchange);
                //request = (RegisterRequest) new JsonData().setupJSONArrays(request);//grabs the arrays we need
                if (request.isValidRequest()) {

                    RegisterResult result = service.register(request);
                    if (result.getE() != null) {
                        encode.encode(result.getE().getMessage(), respBody);
                    }
                    else {
                        encode.encode(result, respBody);
                    }
                    respBody.close();
                    return;
                }
            }
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            encode.encode("Invalid Request",respBody);
            respBody.close();
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            encode.encode(e.getMessage(),respBody);
            respBody.close();
           // e.printStackTrace();
        }
        //System.out.println("errorout");
    }


}
