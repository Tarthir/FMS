package service;

import infoObjects.FillRequest;
import infoObjects.FillResult;

/**
 * Created by tyler on 2/14/2017.
 * Called by our FillHandler/RegisterService and uses our DAO classes to fill up our Database with 4 generations of data
 */

public class FillService {
    /**The request to fill our database*/
    //private FillRequest request;

    public FillService() {}
    /**
     * Gets the result of a request to fill the database
     * @PARAM request, the request to fill the database
     * @RETURN the result of attempting to fill the database
     * */
    public FillResult fill(FillRequest request){
        return null;
    }
    /**
     * @RETURN Gets the request object
     * */
    //public FillRequest getRequest() {
    //    return request;
    //}
}
