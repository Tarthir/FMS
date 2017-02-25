package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import dataAccess.DataBase;
import dataAccess.EventDao;
import dataAccess.LocationDao;
import models.Event;
import models.Location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 2/23/2017.
 * Tests our locationDaos
 */

public class LocationDaoTest {
    private LocationDao lDao;
    private DataBase db;
    private Connection connection;

    @Before
    public void setUp() throws IOException {
        try {
            lDao = new LocationDao();
            EventDao eDao = new EventDao();
            db = new DataBase();
            connection = db.openConnection();
            db.createTables(connection);
            Event event = new Event("eventID", "userID", "personID", "1994", "Birth", "23");
            Event event2 = new Event("eventID2", "userID", "personID2", "1994", "Birth", "232");
            Event event3 = new Event("eventID3", "userID", "personID3", "1994", "Birth", "233");
            Event event4 = new Event("eventID4", "userID2", "personID2", "1994", "Birth2", "236");
            assertTrue(eDao.insertEvent(event));
            assertTrue(eDao.insertEvent(event2));
            assertTrue(eDao.insertEvent(event3));
            assertTrue(eDao.insertEvent(event4));
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
    public void insertLocationTest(){
        try {
            Location loc1 = new Location("locID", "Provo", 213.7, 123.7, "USA");
            Location loc2 = new Location("locID2", "Provo", 213.7, 123.7, "USA");
            Location loc3 = new Location("locID3", "Provo", 213.7, 123.7, "USA");
            Location loc4 = new Location("locID4", "Provo", 213.7, 123.7, "USA");
            assertTrue(lDao.insertLocation(loc1));
            assertTrue(lDao.insertLocation(loc2));
            assertTrue(lDao.insertLocation(loc3));
            assertTrue(lDao.insertLocation(loc4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLocationTest(){
        try {
            Location loc1 = new Location("locID", "Provo", 213.7, 123.7, "USA");
            Location loc2 = new Location("locID2", "Provo", 213.7, 123.7, "USA");
            Location loc3 = new Location("locID3", "Provo", 213.7, 123.7, "USA");
            Location loc4 = new Location("locID4", "Provo", 213.7, 123.7, "USA");
            assertTrue(lDao.insertLocation(loc1));
            assertTrue(lDao.insertLocation(loc2));
            assertTrue(lDao.insertLocation(loc3));
            assertTrue(lDao.insertLocation(loc4));

            assertEquals(loc1, lDao.getLocation("locID"));
            assertEquals(loc2, lDao.getLocation("locID2"));
            assertEquals(loc3, lDao.getLocation("locID3"));
            assertEquals(loc4, lDao.getLocation("locID4"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getLocationTestFail(){
        try {
            Location loc1 = new Location("locID", "Provo", 213.7, 123.7, "USA");
            Location loc2 = new Location("locID2", "Provo", 213.7, 123.7, "USA");
            Location loc3 = new Location("locID3", "Provo", 213.7, 123.7, "USA");
            Location loc4 = new Location("locID4", "Provo", 213.7, 123.7, "USA");
            assertTrue(lDao.insertLocation(loc1));
            assertTrue(lDao.insertLocation(loc2));
            assertTrue(lDao.insertLocation(loc3));
            assertTrue(lDao.insertLocation(loc4));

            assertEquals(loc1, lDao.getLocation("locID"));
            assertEquals(loc2, lDao.getLocation("locID2"));
            assertEquals(loc3, lDao.getLocation("locID3"));
            assertEquals(null, lDao.getLocation("locIDnotinTable"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
