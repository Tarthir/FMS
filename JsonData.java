package encode;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import infoObjects.FillRequest;
import infoObjects.RegisterRequest;
import jdk.nashorn.internal.parser.JSONParser;
import models.Location;

/**
 * Created by tyler on 3/1/2017.
 * Grabs our Json data that we need for Fill request and Register Requests
 */

public class JsonData {

    public JsonData() {
    }

    public Object setupJSONArrays(Object request) throws IllegalArgumentException{
        if(request instanceof FillRequest){
            return setupJSONArraysFill((FillRequest) request);
        }
        else if(request instanceof RegisterRequest){
            return setupJSONArraysReg((RegisterRequest) request);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    private RegisterRequest setupJSONArraysReg(RegisterRequest request){
        String filePathStr = "C:\\Users\\tyler\\AndroidStudioProjects\\FamilyMap\\locations.json";
        FileReader reader = null;
        try {
            reader = new FileReader(filePathStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        request.setfNames(new FemaleNamesHolder().getFnames());
        request.setlNames(new SurNamesHolder().getSnames());
        request.setLocations(gson.fromJson(reader,Location[].class));
        request.setmNames(new MaleNamesHolder().getMnames());
        return request;
    }

    private FillRequest setupJSONArraysFill(FillRequest request){
        String filePathStr = "C:\\Users\\tyler\\AndroidStudioProjects\\FamilyMap\\locations.json";
        FileReader reader = null;
        try {
            reader = new FileReader(filePathStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        request.setfNames(new FemaleNamesHolder().getFnames());
        request.setlNames(new SurNamesHolder().getSnames());
        request.setLocations(gson.fromJson(reader,Location[].class));
        request.setmNames(new MaleNamesHolder().getMnames());
        return request;
    }
}
