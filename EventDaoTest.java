package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

import dataAccess.DataBase;
import dataAccess.EventDao;
import infoObjects.EventRequest;
import models.Event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 2/23/2017.
 * Tests our eventDao
 */

public class EventDaoTest {
    private EventDao eDao;
    private DataBase db;
    private Connection connection;

    @Before
    public void setUp() throws IOException {
        eDao = new EventDao();
        db = new DataBase();
        connection = db.openConnection();
        db.createTables(connection);
    }

    @After
    public void tearDown() {
        connection = db.openConnection();
        db.dropTables(connection);
        return;
    }

    @Test
    public void testInsertEvent() {
        Event event = new Event("eventID","userID","personID","1994","Birth","23");
        assertTrue(eDao.insertEvent(event));
    }

    @Test
    public void testgetEvent() {
        Event event = new Event("eventID","userID","personID","1994","Birth","23");
        assertTrue(eDao.insertEvent(event));
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("eventID","userID","personID","1994","Birth","23"));
        EventRequest request = new EventRequest("eventID");
        assertEquals(expected,eDao.getEvent(request));
    }

    @Test
    public void testgetEvents() {
        Event event = new Event("eventID","userID","personID","1994","Birth","23");
        Event event2 = new Event("eventID2","userID","personID2","1994","Birth","232");
        Event event3 = new Event("eventID3","userID","personID3","1994","Birth","233");
        Event event4 = new Event("eventID4","userID2","personID2","1994","Birth2","236");
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("eventID","userID","personID","1994","Birth","23"));
        ArrayList<String> expected2 = new ArrayList<>(Arrays.asList("eventID2","userID","personID2","1994","Birth","232"));
        ArrayList<String> expected3 = new ArrayList<>(Arrays.asList("eventID3","userID","personID3","1994","Birth","233"));
        assertTrue(eDao.insertEvent(event));
        assertTrue(eDao.insertEvent(event2));
        assertTrue(eDao.insertEvent(event3));
        assertTrue(eDao.insertEvent(event4));
        ArrayList<ArrayList<String>> events= new ArrayList<>(Arrays.asList(expected,expected2,expected3));
        assertEquals(events, eDao.getEvents("userID"));

    }
}
