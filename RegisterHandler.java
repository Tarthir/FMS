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
        boolean success = false;//?
        OutputStream respBody = null;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {

                //Headers reqHeaders = exchange.getRequestHeaders();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                RegisterService service = new RegisterService();
                respBody = exchange.getResponseBody();
                Encoder encode = new Encoder();
                RegisterRequest request = encode.decodeReg(exchange);

                request = (RegisterRequest) new JsonData().setupJSONArrays(request);//grabs the arrays we need
                RegisterResult result = service.register(request);
                if(result.getE() != null){
                    encode.encode(result.getE(),respBody);
                }
                else {
                    encode.encode(result, respBody);
                }
                respBody.close();
                success = true;
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            // e.printStackTrace();
        }
    }

}
