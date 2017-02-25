package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dataAccess.AuthTokenDao;
import dataAccess.DataBase;
import dataAccess.EventDao;
import dataAccess.LocationDao;
import dataAccess.MultiDao;
import dataAccess.PersonDao;
import dataAccess.UserDao;
import infoObjects.EventRequest;
import infoObjects.PersonRequest;
import models.AuthToken;
import models.Event;
import models.Location;
import models.Person;
import models.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 2/24/2017.
 * Tests our multiDao class
 */

public class MultiDaoTest {
    private PersonDao pDao;
    private EventDao eDao;
    private LocationDao lDao;
    private AuthTokenDao aDao;
    private DataBase db;
    private Connection connection;
    private MultiDao mDao;
    private AuthToken authToken2;

    @Before
    public void setUp() throws IOException {
        mDao = new MultiDao();
        pDao = new PersonDao();
        eDao = new EventDao();
        lDao = new LocationDao();
        aDao = new AuthTokenDao();
        UserDao uDao = new UserDao();
        db = new DataBase();
        connection = db.openConnection();
        db.createTables(connection);
        try {
            User user = new User("userID", "name", "password", "email", "first", "last", "m");
            User user2 = new User("userID2", "name2", "password2", "email2", "first2", "last2", "f");
            assertTrue(uDao.register(user));
            assertTrue(uDao.register(user2));
            assertTrue(aDao.insertAuthToken("userID", new AuthToken()));
            authToken2 = new AuthToken();
            assertTrue(aDao.insertAuthToken("userID2", authToken2));
            assertTrue(pDao.insertPerson(new Person("personID", "userID", "fName", "lName", "m", "fatherID", "motherID", "spouseID")));
            assertTrue(pDao.insertPerson(new Person("personID2", "userID", "fName2", "lName2", "m", "fatherID2", "motherID2", "spouseID2")));
            assertTrue(pDao.insertPerson(new Person("personID3", "userID", "fName3", "lName3", "m", "fatherID3", "motherID3", "spouseID3")));
            assertTrue(pDao.insertPerson(new Person("personID4", "userID2", "fName4", "lName4", "f", "fatherID4", "motherID4", "spouseID4")));

            assertTrue(eDao.insertEvent(new Event("eventID", "userID", "personID", "1994", "Birth", "locID")));
            assertTrue(eDao.insertEvent(new Event("eventID2", "userID", "personID2", "1994", "Birth", "locID2")));
            assertTrue(eDao.insertEvent(new Event("eventID3", "userID", "personID3", "1994", "Birth", "locID3")));
            assertTrue(eDao.insertEvent(new Event("eventID4", "userID2", "personID4", "1994", "Birth2", "locID4")));

            assertNotEquals(eDao.getEvent(new EventRequest("eventID")),null);
            assertNotEquals(eDao.getEvent(new EventRequest("eventID2")),null);
            assertNotEquals(eDao.getEvent(new EventRequest("eventID3")),null);
            assertNotEquals(eDao.getEvent(new EventRequest("eventID4")),null);

            assertTrue(lDao.insertLocation(new Location("locID", "Provo", 213.7, 123.7, "USA")));
            assertTrue(lDao.insertLocation(new Location("locID2", "Provo", 213.7, 123.7, "USA")));
            assertTrue(lDao.insertLocation(new Location("locID3", "Provo", 213.7, 123.7, "USA")));
            assertTrue(lDao.insertLocation(new Location("locID4", "Provo", 213.7, 123.7, "USA")));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        connection = db.openConnection();
        db.dropTables(connection);
        return;
    }

    @Test
    public void deleteFromDataBaseTest(){
        try {
            assertTrue(mDao.deleteFromDataBase("name"));
            ArrayList<String> arr = eDao.getEvent(new EventRequest("eventID4"));
            ArrayList<String> arr2 = pDao.getPerson(new PersonRequest("personID4"));
            ArrayList<String> arr3 = eDao.getEvent(new EventRequest("eventID2"));
            ArrayList<String> arr4 = pDao.getPerson(new PersonRequest("personID2"));
            //ArrayList<String> arr3 = aDao.getAuthToken("userID2");
            assertNotEquals(arr,null);
            assertNotEquals(arr2,null);
            assertEquals(arr3,null);
            assertEquals(arr4,null);
        }catch(SQLException e){e.printStackTrace();}
    }
}
