package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by tyler on 2/24/2017.
 * Used when we need to access the Database from multiple Daos in a particular order or at the same time
 */

public class MultiDao {
    private PersonDao pDao;
    private EventDao eDao;
    //private LocationDao lDao;
    private AuthTokenDao aDao;

    public MultiDao(){
        pDao = new PersonDao();
        eDao = new EventDao();
        //lDao = new LocationDao();
        aDao = new AuthTokenDao();
    }
    /**
     * Takes a userID and deletes all data related to it
     * @PARAM A String userID
     * @RETURN boolean
     * @EXCEPTION SQLException
     */
    public boolean deleteFromDataBase(String userName) throws SQLException{
        //ArrayList<String> locationIDs = eDao.getLocationIDs(userID);//get the UserIDs
        boolean delete = true;//If we ever return false, then this failed
        UserDao uDao = new UserDao();
        String userID = uDao.getUserIDWithUserName(userName);

        /*for (String id : locationIDs) {
            if(delete){delete = lDao.deleteLocation(id);}
        }*/
        if(delete){delete = eDao.deleteEvents(userID);}
        if(delete){delete = pDao.deletePerson(userID);}
       //if(delete){delete = aDao.deleteAuthToken(userID);}
        return delete;
    }

}
