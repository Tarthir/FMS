package encode;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import infoObjects.FillRequest;


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
    public FillRequest setupJSONArrays(FillRequest request) throws IllegalArgumentException,IOException {
        return setupJSONArraysFill(request);
    }
    /**
     *Takes our Json arrays we have and then converts them to java arrays
     * @PARAM FillRequest, a type of request
     * @RETURN The FillRequest object with updated fields containg the arrays we setup
     * */
    private FillRequest setupJSONArraysFill(FillRequest request) throws IOException {
        String filePathStr = "C:\\Users\\tyler\\AndroidStudioProjects\\FamilyMap\\DefaultFiles\\locations.json";
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(filePathStr);

            request.setfNames(new FemaleNamesHolder().getFnames());
            request.setlNames(new SurNamesHolder().getSnames());
            LocationDataHolder holder = gson.fromJson(reader,LocationDataHolder.class);
            request.setLocations(holder.getLocArray());
            request.setmNames(new MaleNamesHolder().getMnames());

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return request;
    }
}
