package service;

import java.sql.SQLException;

import dataAccess.MultiDao;
import dataAccess.UserDao;
import infoObjects.FillRequest;
import infoObjects.FillResult;

/**
 * Created by tyler on 2/14/2017.
 * Called by our FillHandler/RegisterService and uses our DAO classes to fill up our Database with 4 generations of data
 */

public class FillService {

    public FillService() {
    }

    /**
     * Gets the result of a request to fill the database
     *
     * @PARAM request, the request to fill the database
     * @RETURN the result of attempting to fill the database
     */
    public FillResult fill(FillRequest request){
        try {
            if (request.getNumOfGenerations() > 0) {//if given a non negative integer
                MultiDao multiDao = new MultiDao();
                if (multiDao.deleteFromDataBase(request.getUsername())) {//if the deletion works right
                    UserDao uDao = new UserDao();
                    String userID = uDao.getUserIDWithUserName(request.getUsername());//get the userName
                    DataGenerator dataGenerator = new DataGenerator(userID);
                    return dataGenerator.genData(request);//if it generates right
                }
            }
        }catch(SQLException e){
            return new FillResult(e);//return the error
        }
        return null;
    }
}
