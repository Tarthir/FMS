package handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;

import encode.Encoder;
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

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {

                //Headers reqHeaders = exchange.getRequestHeaders();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                RegisterService service = new RegisterService();
                Encoder encode = new Encoder();
                RegisterResult result = service.register(encode.decodeReg(exchange));
                //encode.


                success = true;
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            // e.printStackTrace();
        }
    }
}
