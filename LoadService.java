package service;

import java.sql.SQLException;

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
     * @PARAM request, contains the data that needs to be inputted into the Database
     * @RETURN the result, successful or not, of attempting to load in data into the database
     */
    public LoadResult load(LoadRequest request){
        UserDao uDao = new UserDao();
        PersonDao pDao = new PersonDao();
        EventDao eDao = new EventDao();
        try {
            for (User user : request.getUsers()) {
                uDao.register(user);
            }
            for (Person person : request.getPersons()) {
                pDao.insertPerson(person);
            }
            for (Event event : request.getEvents()) {
                eDao.insertEvent(event);
            }
        } catch (SQLException e) {
            return new LoadResult(e);
            //return result;
        }

        return new LoadResult(request.getUsers().length,request.getPersons().length,request.getEvents().length);
       // return load;
    }
}
