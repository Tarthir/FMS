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
import infoObjects.EventRequest;
import infoObjects.EventResult;
import models.Event;
import models.Location;
import service.EventService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 2/23/2017.
 * tests our EventService class
 */

public class EventServiceTest {
    private EventDao eDao;
    private EventService eService;
    private DataBase db;
    private Connection connection;

    @Before
    public void setUp() throws IOException {
        try {
            eService = new EventService();
            eDao = new EventDao();
            db = new DataBase();
            connection = db.openConnection();
            db.createTables(connection);
            Event event = new Event("eventID", "userID", "personID", "1994", "Birth", new Location( "213.7", "123.7","Provo", "USA"));
            Event event2 = new Event("eventID2", "userID", "personID2", "1994", "Birth", new Location( "213.7", "123.7","Provo", "USA"));
            Event event3 = new Event("eventID3", "userID", "personID3", "1994", "Birth", new Location("213.7", "123.7","Provo",  "USA"));
            Event event4 = new Event("eventID4", "userID2", "personID4", "1994", "Birth2", new Location( "213.7", "123.7","Provo", "USA"));
            assertTrue(eDao.insertEvent(event));
            assertTrue(eDao.insertEvent(event2));
            assertTrue(eDao.insertEvent(event3));
            assertTrue(eDao.insertEvent(event4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void testGetEvent() {
       //try {
            Event event3 = new Event("eventID3", "userID", "personID3", "1994", "Birth", new Location( "213.7", "123.7","Provo", "USA"));
            EventRequest request = new EventRequest("eventID3");
            EventResult result = new EventResult("userID", event3, "personID3");
            assertEquals(eService.getEvent(request).getUserID(), result.getUserID());
            assertEquals(eService.getEvent(request).getPersonID(), result.getPersonID());
            assertEquals(eService.getEvent(request).getEvent(), result.getEvent());
       // } catch (SQLException e) {
        //    e.printStackTrace();
        //}
    }

}
