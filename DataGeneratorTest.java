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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 2/27/2017.
 * Tests our data generator
 */

public class DataGeneratorTest {
    private DataGenerator dGen;
    private DataBase db;
    private Connection connection;
    private Location[] locations = {new Location("lat","long","provo","USA"),new Location("lat2","long2","tucson","USA"),new Location("lat3","long3","Richland","USA")};
    private String[] fNames = {"sally","jo","sue"};
    private String[] mNames = {"Bob","Bobby","billy"};
    private String[] lNames = {"Matthews","Brady","Spicer"};
    private UserDao uDao;
    private PersonDao pDao;
    private EventDao eDao;
    private FillRequest request;

    @Before
    public void setUp(){
        try {
            dGen = new DataGenerator("userID");
            uDao = new UserDao();
            db = new DataBase();
            pDao = new PersonDao();
            eDao = new EventDao();
            connection = db.openConnection();
            db.createTables(connection);
            User user = new User("userID","userName","password","email","first","last","m");
            assertTrue(uDao.register(user));
            request = new FillRequest(4, "userName");
            request.setfNames(fNames);
            request.setlNames(lNames);
            request.setLocations(locations);
            request.setmNames(mNames);
        }catch(SQLException e){e.printStackTrace();}
    }

    @After
    public void tearDown() {
        connection = db.openConnection();
        try {
            db.dropTables(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
    @Test
    public void genData() {
        try {
            FillResult result = new FillResult(31,123);
            FillResult actual = dGen.genData(request);
            assertEquals(result.getNumOfEvents(),actual.getNumOfEvents());
            assertEquals(result.getNumOfPersons(),actual.getNumOfPersons());
            assertEquals(result.getResult(),actual.getResult());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
    }


    /*@Test
    private void genPeopleTest(){

    }

    @Test
    private void getFemaleTest(){

    }

    @Test
    private void getMaleTest(){
    }

    @Test
    private void genEventsTest(){

    }
    @Test
    private void getYearTest(){

    }
    @Test
    private void getBirthDatesTest(){

    }

    @Test
    private void getBaptismDatesTest(){

    }

    @Test
    private void createMarriageTest(){
    }
    @Test
    private void getDeathDatesTest(){

    }

    @Test
    private void insertDataTest(){

    }*/
}
