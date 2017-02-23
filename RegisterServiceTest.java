package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;

import dataAccess.AuthTokenDao;
import dataAccess.DataBase;
import dataAccess.UserDao;
import infoObjects.RegisterRequest;
import infoObjects.RegisterResult;
import models.User;
import service.RegisterService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 2/22/2017.
 * Tests our register service class
 */

public class RegisterServiceTest {
    private RegisterService rService;
    private DataBase db;
    private Connection connection;

    @Before
    public void setUp() throws IOException {
        rService = new RegisterService();
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
    public void testRegister() {
        RegisterRequest request = new RegisterRequest("username","password","email","first","last","f");
        AuthTokenDao authDao = new AuthTokenDao();
        UserDao uDao = new UserDao();
        RegisterResult result = rService.register(request);
        assertEquals(result.getUserName(),"username");
        assertNotEquals(result.getUserName(),"usernameNotInTable");
        assertEquals(result.getAuthToken(),authDao.getAuthToken(result.getPersonID()).get(0));
        assertEquals(uDao.getUserIDWithNames("first","last"),authDao.getUserIDFromAuthToken(result.getAuthToken()));//should get same userID
    }

    @Test
    public void testRegisterMultiple() {
        RegisterRequest request = new RegisterRequest("username","password","email","first","last","f");
        RegisterRequest request2 = new RegisterRequest("username2","password2","email2","first","last2","f");
        RegisterRequest request3 = new RegisterRequest("username","password","email","first","last","f");
        AuthTokenDao authDao = new AuthTokenDao();
        UserDao uDao = new UserDao();
        RegisterResult result = rService.register(request);
        RegisterResult result2 = rService.register(request2);
        RegisterResult result3 = rService.register(request3);
        assertNotEquals(result.getAuthToken(),result2.getAuthToken());
        assertNotEquals(result.getUserName(),result2.getUserName());
        assertNotEquals(result.getPersonID(),result2.getPersonID());
        assertNotEquals(uDao.getUserIDWithNames("first","last"),uDao.getUserIDWithNames("first","last2"));
        assertNotEquals(authDao.getAuthToken(uDao.getUserIDWithNames("first","last")),authDao.getAuthToken(uDao.getUserIDWithNames("first","last2")));

    }

    @Test
    public void multiRegisterSame() {
        RegisterRequest request = new RegisterRequest("username","password","email","first","last","f");
        RegisterRequest request2 = new RegisterRequest("username2","password2","email2","first","last2","f");
        RegisterRequest request3 = new RegisterRequest("username2","password","email","first","last","f");

        RegisterResult result = rService.register(request);
        RegisterResult result2 = rService.register(request2);
        RegisterResult result3 = rService.register(request3);

        assertNotEquals(result,null);
        assertNotEquals(result2,null);
        assertEquals(result3,null);
    }
}
