package handler;

import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import encode.Encoder;
import encode.JsonData;
import infoObjects.LoadRequest;
import infoObjects.LoadResult;
import service.LoadService;

/**
 * Created by tyler on 2/14/2017.
 * This class handles calls on the server to load preset information into the database
 */

public class LoadHandler implements HttpHandler{

    public LoadHandler() {
    }

    @Override
    /**This method handles the load request from the server*/
    public void handle(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
        OutputStream respBody;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                //Headers reqHeaders = exchange.getRequestHeaders();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                LoadService service = new LoadService();
                respBody = exchange.getResponseBody();
                Encoder encode = new Encoder();
                //DO I NEED TO MANUALLY GET THESE ARRAYS?
                LoadRequest request = encode.decodeLoad(exchange);

                request = (LoadRequest) new JsonData().setupJSONArrays(request);//grabs the arrays we need
                LoadResult result = service.load(request);
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
