package service;

import java.sql.SQLException;
import java.util.IllegalFormatCodePointException;

import dataAccess.AuthTokenDao;
import dataAccess.EventDao;
import dataAccess.MultiDao;
import dataAccess.PersonDao;
import dataAccess.UserDao;
import infoObjects.LoadRequest;
import infoObjects.LoadResult;
import models.Event;
import models.Person;
import models.User;

/**
 * Created by tyler on 2/14/2017.
 * This class is called/Created by the Load handler, it loads pre made info into our database
 */

public class LoadService {

    public LoadService() {
    }

    /**
     * This function returns the result of attempting to load arrays of users,events, and persons into the database
     *
     * @PARAM LoadRequest, contains the data that needs to be inputted into the Database
     * @RETURN the result, successful or not, of attempting to load in data into the database
     */
    public LoadResult load(LoadRequest request) throws SQLException{
        MultiDao mDao = new MultiDao();
        if (request.isValidRequest()) {//if its a valid request
            mDao.doClear();
            return new MultiDao().loadDataBase(request);
        }
        return new LoadResult(new IllegalArgumentException("Invalid request"));

    }
}
