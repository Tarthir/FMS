package service;

        import java.sql.SQLException;

        import dataAccess.MultiDao;
        import dataAccess.UserDao;
        import encode.JsonData;
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
    public FillResult fill(FillRequest request) {
        try {
            if (request.getNumOfGenerations() > 0) {//if given a non negative integer
                MultiDao multiDao = new MultiDao();
                UserDao uDao = new UserDao();
                if (uDao.checkUserName(request.getUsername())) {
                   request = (FillRequest) new JsonData().setupJSONArrays(request);
                    if (multiDao.deleteFromDataBase(request.getUsername())) {//if the deletion works right
                        DataGenerator dataGenerator = new DataGenerator();
                        return dataGenerator.genData(request);//if it generates right
                    }
                }
            }
        } catch (SQLException e) {
            return new FillResult(e);//return the error
        } catch (Exception e) {
            //e.printStackTrace();
            return new FillResult(e);
        }
        return null;
    }
}
