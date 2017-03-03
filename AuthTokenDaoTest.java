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
import dataAccess.UserDao;
import models.AuthToken;
import models.User;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 2/21/2017.
 * Tests authTokenDao
 */

public class AuthTokenDaoTest {
    private AuthTokenDao aDao;
    private UserDao uDao;
    private DataBase db;
    private Connection connection;

    @Before
    public void setUp() throws IOException {
        aDao = new AuthTokenDao();
        db = new DataBase();
        connection = db.openConnection();
        db.createTables(connection);
        //to Setup
        uDao = new UserDao();
        User user = new User("5", "name", "password", "email", "first", "last", "m");
        try {
            assertTrue(uDao.register(user));
            assertEquals(uDao.getUserIDWithNames("first", "last"), "5");
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
    public void insertAuthTokenTest() {
        AuthToken auth = new AuthToken();
        try {
            String userID = uDao.getUserIDWithNames("first", "last");//Get the old userID
            assertTrue(aDao.insertAuthToken(userID, auth));
            assertFalse(aDao.insertAuthToken("NotInTable", auth));//will throw exception, which is fine in this case

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            assertFalse(aDao.insertAuthToken("NotInTable", auth));//will throw exception, which is fine in this case
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteAuthTokenTest() {
        AuthToken auth = new AuthToken();
        try {
            String userID = uDao.getUserIDWithNames("first", "last");//Get the old userID
            assertTrue(aDao.insertAuthToken(userID, auth));
            assertTrue(aDao.deleteAuthToken(userID));
            assertEquals(aDao.getAuthToken(userID),new ArrayList<String>());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getAuthTokenTest() {
        try {
            //Setup
            String userID = uDao.getUserIDWithNames("first", "last");//Get the old userID
            AuthToken auth = new AuthToken();
            assertTrue(aDao.insertAuthToken(userID, auth));
            //endSetup
            ArrayList<String> authToks;
            // ArrayList<String> authToksExpected = new ArrayList<>();
            authToks = aDao.getAuthToken("5");//get the authtoken(s) related to the userID of 5
            for (String str : authToks) {//for every authToken we found
                assertEquals(aDao.getUserIDFromAuthToken(str), "5");//can we see that its related to this userID?
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAuthTokenTestFail() {

        try {
            //Setup
            String userID = uDao.getUserIDWithNames("first", "last");//Get the old userID
            AuthToken auth = new AuthToken();
            assertTrue(aDao.insertAuthToken(userID, auth));
            //endSetup
            ArrayList<String> authToks;
            // ArrayList<String> authToksExpected = new ArrayList<>();
            authToks = aDao.getAuthToken("5");//get the authtoken(s) related to the userID of 5
            for (String str : authToks) {//for every authToken we found
                assertNotEquals(aDao.getUserIDFromAuthToken(str), "notintable");//can we see that its related to this userID?
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
