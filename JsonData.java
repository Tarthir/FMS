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
    /**
     *Takes our Json arrays we have and then converts them to java arrays
     * @PARAM Object, a type of request
     * @RETURN The request Object with updated fields containg the arrays we setup
     * @EXCEPTION IllegalArgumentException
     * */
    public Object setupJSONArrays(Object request) throws IllegalArgumentException {
        System.out.println("json Data");
        if (request instanceof FillRequest) {
            System.out.println("FillRequest");
            return setupJSONArraysFill((FillRequest) request);
        } else if (request instanceof RegisterRequest) {
            System.out.println("RegisterRequest");
            return setupJSONArraysReg((RegisterRequest) request);
        } else {
            System.out.println("Illegal");
            throw new IllegalArgumentException();
        }
    }
    /**
     *Takes our Json arrays we have and then converts them to java arrays
     * @PARAM RegisterRequest, a type of request
     * @RETURN The RegisterRequest object with updated fields containg the arrays we setup
     * */
    private RegisterRequest setupJSONArraysReg(RegisterRequest request) {
        String filePathStr = "C:\\Users\\tyler\\AndroidStudioProjects\\FamilyMap\\DefaultFiles\\locations.json";
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(filePathStr);
            request.setfNames(new FemaleNamesHolder().getFnames());
            request.setlNames(new SurNamesHolder().getSnames());
            request.setLocations(gson.fromJson(reader, LocationDataHolder.class).getLocArray());
            request.setmNames(new MaleNamesHolder().getMnames());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return request;
    }
    /**
     *Takes our Json arrays we have and then converts them to java arrays
     * @PARAM FillRequest, a type of request
     * @RETURN The FillRequest object with updated fields containg the arrays we setup
     * */
    private FillRequest setupJSONArraysFill(FillRequest request) {
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
        request.setLocations(gson.fromJson(reader, Location[].class));
        request.setmNames(new MaleNamesHolder().getMnames());
        return request;
    }
}
