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
import infoObjects.LoadRequest;
import infoObjects.LoadResult;
import infoObjects.PersonRequest;
import models.Event;
import models.Location;
import models.Person;
import models.User;
import service.DataGenerator;
import service.FillService;
import service.LoadService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 2/24/2017.
 */

public class LoadServiceTest {
    private LoadService lService;
    private DataBase db;
    private Connection connection;
    private Person[] persons = {new Person("personID", "userID", "fName", "lName", "m", "", "", ""), new Person("personID2", "userID2", "fName", "lName", "m", "", "", "")};
    private Event[] events = {new Event("eventID", "userID", "personID", new Location("lat", "long", "city", "USA"), "2999", "Birth"),
            new Event("eventID2", "userID2", "personID2", new Location("lat", "long", "city", "USA"), "2999", "Birth")};
    private User[] users = {new User("userName", "password", "email", "name", "lname", "m"), new User("userName", "password", "email", "name", "lname", "m")};

    @Before
    public void setUp(){
        //try {
        lService = new LoadService();
        db = new DataBase();
        try {
            connection = db.openConnection();
            db.createTables(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws SQLException {
        connection = db.openConnection();
        try {
            db.dropTables(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return;
    }

    @Test
    public void genDataAndDeletion() {
        LoadResult result = null;
        try {
            result = lService.load(new LoadRequest(users, persons, events));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LoadResult expected = new LoadResult(2, 2, 2);
        assertEquals(result.getResultMessage(), expected.getResultMessage());
    }
}
