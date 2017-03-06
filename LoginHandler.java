package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import encode.Encoder;
import encode.JsonData;
import infoObjects.LoginRequest;
import infoObjects.LoginResult;
import service.LoginService;

/**
 * Created by tyler on 2/13/2017.
 * Handles login requests from our client. Unpackages JSON object into appropriate object
 */
public class LoginHandler implements HttpHandler {
    public LoginHandler( ) {
    }

    @Override
    /**This method handles the login request from the server*/
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream respBody;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                //Headers reqHeaders = exchange.getRequestHeaders();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                LoginService service = new LoginService();
                respBody = exchange.getResponseBody();
                Encoder encode = new Encoder();

                LoginRequest request = encode.decodeLogin(exchange);
                //System.out.print(request.getUserName() + request.getPassWord());
                LoginResult result = service.login(request);

                encode.encode(result,respBody);
                respBody.close();
            }

            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            // e.printStackTrace();
        }
    }
}