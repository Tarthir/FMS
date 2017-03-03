package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import encode.Encoder;
import encode.JsonData;
import infoObjects.FillRequest;
import infoObjects.FillResult;
import service.FillService;

/**
 * Created by tyler on 2/13/2017.
 * Called by our Main server. We get our requests and pass them off to the FillService class which will use the
 * DAOs to access the database
 */

public class FillHandler implements HttpHandler {
    public FillHandler() {}

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream respBody;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                //Headers reqHeaders = exchange.getRequestHeaders();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                FillService service = new FillService();
                respBody = exchange.getResponseBody();
                Encoder encode = new Encoder();
                FillRequest request = encode.decodeFill(exchange);

                request = (FillRequest) new JsonData().setupJSONArrays(request);//grabs the arrays we need
                FillResult result = service.fill(request);
                if(result.getE() != null){
                    encode.encode(result.getE(),respBody);
                }
                else {
                    encode.encode(result, respBody);
                }
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
