package handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import service.RegisterService;

/**
 * Created by tyler on 2/28/2017.
 * Handles requests for Index.html
 */

//returns a website. returns the index.html file
// given 1.0.0.|:8080/index.html this is sent to the index handler. Also defaults to this if given
// 1.0.0.|:8080/
public class IndexHandler implements HttpHandler {
    //exchange variable holds the http info
    public void handle(HttpExchange exchange)throws IOException {
        //String myBaseFolder = "HTML" + File.separator;
        OutputStream respBody = null;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);//otherwise send Forbidden/BadRequest/etc as needed
                //Headers reqHeaders = exchange.getRequestHeaders();

                String filePathStr = "C:\\Users\\tyler\\AndroidStudioProjects\\FamilyMap\\DefaultFiles\\index.html";
                Path filePath = FileSystems.getDefault().getPath(filePathStr);
                Files.copy(filePath, exchange.getResponseBody());
                respBody = exchange.getResponseBody();

                //DO the .css file too if you want
                filePathStr = "C:\\Users\\tyler\\AndroidStudioProjects\\FamilyMap\\DefaultFiles\\main.css";
                filePath = FileSystems.getDefault().getPath(filePathStr);
                Files.copy(filePath, exchange.getResponseBody());

                respBody.close();//right?

            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

   /* private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }*/
}
