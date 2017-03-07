package dataAccess;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import infoObjects.FillRequest;
import infoObjects.LoadRequest;
import infoObjects.LoadResult;
import models.Event;
import models.Person;
import models.User;

/**
 * Created by tyler on 2/24/2017.
 * Used when we need to access the Database from multiple Daos in a particular order or at the same time
 */

public class MultiDao {
    /**A PersonDao*/
    private PersonDao pDao;
    /**A EventDao*/
    private EventDao eDao;

    public MultiDao(){
        pDao = new PersonDao();
        eDao = new EventDao();
    }
    /**
     * Takes a userID and deletes all data related to it
     * @PARAM A String userID
     * @RETURN boolean
     * @EXCEPTION SQLException
     */
    public boolean deleteFromDataBase(String userName) throws SQLException{
        //boolean delete = true;//If we ever return false, then this failed
        UserDao uDao = new UserDao();
        return (eDao.deleteEvents(userName) && pDao.deletePerson(userName));
    }

    public LoadResult loadDataBase(LoadRequest request) throws SQLException{
        UserDao uDao = new UserDao();
        PersonDao pDao = new PersonDao();
        EventDao eDao = new EventDao();
        System.out.println(true);
        //TODO: CHECK FOR INVALID INPUTS? OR LET DAO THROW SQL
            for (User user : request.getUsers()) {
                uDao.register(user);
            }
            for (Person person : request.getPersons()) {
                pDao.insertPerson(person);
            }
            for (Event event : request.getEvents()) {
                //UUID uuid = UUID.randomUUID();
                //event.setEventID(uuid.toString());
                eDao.insertEvent(event);
            }
        return new LoadResult(request.getUsers().length,request.getPersons().length,request.getEvents().length);
    }

    /**
     * Clears the database
     * @RETURN VOID
     * @EXCEPTION throws SQLException
     * */
    public void doClear()throws SQLException{
        DataBase db = new DataBase();
        Connection conn = null;
        db.dropTables(db.openConnection());//connection is closed in the method. drops the tables
        db.createTables(db.openConnection());

    }
    /**
     * Validates authtokens given by the user
     * @PARAM String,A personID
     * @PARAM String, an Authtoken
     * @RETURN boolean, if is validated
     * @EXCEPTION SQLException
     * */
    public boolean validate(String authTok)throws SQLException{
        AuthTokenDao aDao = new AuthTokenDao();
        return aDao.validateAuthToken(authTok);
    }

}
