package service;

import infoObjects.LoadRequest;
import infoObjects.LoadResult;

/**
 * Created by tyler on 2/14/2017.
 * This class is called/Created by the Load handler, it loads pre made info into our database
 */

public class LoadService {
    //private LoadRequest load;

    public LoadService(LoadRequest load) {
    //    this.load = load;
    }
    /**
     * This function returns the result of attempting to load arrays of users,events, and persons into the database
     * @PARAM request, contains the data that needs to be inputted into the Database
     * @RETURN the result, successful or not, of attempting to load in data into the database
     * */
    public LoadResult load(LoadRequest request){
        return null;
    }
}
