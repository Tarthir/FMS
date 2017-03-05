package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import dataAccess.DataBase;
import dataAccess.EventDao;
import dataAccess.PersonDao;
import dataAccess.UserDao;
import infoObjects.EventRequest;
import infoObjects.FillRequest;
import infoObjects.FillResult;
import infoObjects.PersonRequest;
import models.Event;
import models.Location;
import models.Person;
import models.User;
import service.DataGenerator;
import service.FillService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 2/24/2017.
 */

public class FillServiceTest {
    private FillService fService;
    private DataBase db;
    private Connection connection;
    private Location[] locations = {new Location("lat","long","provo","USA"),new Location("lat2","long2","tucson","USA"),new Location("lat3","long3","Richland","USA")};
    private String[] fNames = {"sally","jo","sue"};
    private String[] mNames = {"Bob","Bobby","billy"};
    private String[] lNames = {"Matthews","Brady","Spicer"};
    private UserDao uDao;
    private PersonDao pDao;
    private EventDao eDao;

    @Before
    public void setUp(){
        try {
            fService = new FillService();
            uDao = new UserDao();
            db = new DataBase();
            pDao = new PersonDao();
            eDao = new EventDao();
            connection = db.openConnection();
            db.createTables(connection);
            User user = new User("userName","password","email","first","last","m");
            assertTrue(uDao.register(user));
        }catch(SQLException e){e.printStackTrace();}
    }

    @After
    public void tearDown() throws SQLException {
        connection = db.openConnection();
        try {
            db.dropTables(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
    @Test
    public void genDataAndDeletion() {
        try {
            //setup
            User user = new User("userName2","password","email","first","last","m");
            assertTrue(uDao.register(user));
            assertTrue(pDao.insertPerson(new Person("personID", "userName2", "fName", "lName", "m", "fatherID", "motherID", "spouseID")));
            assertTrue(pDao.insertPerson(new Person("personID2", "userName2", "fName2", "lName2", "m", "fatherID2", "motherID2", "spouseID2")));
            assertTrue(eDao.insertEvent(new Event("eventID", "userName2", "personID", new Location( "213.7", "123.7", "Provo","USA"), "1994", "Birth")));
            assertTrue(eDao.insertEvent(new Event("eventID2", "userName2", "personID2", new Location( "213.7", "123.7", "Provo","USA"), "1994", "Birth")));
            //setup end
            FillRequest request = new FillRequest(4, "userName2");
            request.setfNames(fNames);
            request.setlNames(lNames);
            request.setLocations(locations);
            request.setmNames(mNames);
            FillResult actual = fService.fill(request);

            //FillRequest request = new FillRequest(4, "userName2", locations, fNames, lNames, mNames);
            FillResult result = new FillResult(31,123);
           // FillResult actual = dGen.genData(request);
            assertEquals(eDao.getEvent(new EventRequest("eventID")),null);
            assertEquals(eDao.getEvent(new EventRequest("eventID2")),null);
            assertEquals(pDao.getPerson(new PersonRequest("personID")),null);
            assertEquals(pDao.getPerson(new PersonRequest("personID2")),null);
            assertEquals(result.getNumOfEvents(),actual.getNumOfEvents());
            assertEquals(result.getNumOfPersons(),actual.getNumOfPersons());
            assertEquals(result.getResult(),actual.getResult());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
