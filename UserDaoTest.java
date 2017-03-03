package test;

import org.junit.*;
import static org.junit.Assert.* ;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import dataAccess.DataBase;
import dataAccess.UserDao;
import infoObjects.LoginRequest;
import models.User;

/**
 * Created by tyler on 2/20/2017.
 */

public class UserDaoTest {
    private UserDao uDao;
    private DataBase db;
    private Connection connection;

    @Before
    public void setUp() throws IOException {
        uDao = new UserDao();
        db = new DataBase();
        connection = db.openConnection();
        db.createTables(connection);
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
    public void testRegister() {
        try{
        User user = new User("5","name","password","email","first","last","m");
        User user2 = new User("1","name2","password2","email2","first2","last2","f");;
        assertTrue(uDao.register(user2));
        assertEquals(uDao.getUserIDWithNames("first2","last2"),"1");
        assertNotEquals(uDao.getUserIDWithNames("first2","last2"),"0");
        assertNotEquals(uDao.getUserIDWithNames("first","last"),"1");

        assertTrue(uDao.register(user));
        assertEquals(uDao.getUserIDWithNames("first","last"),"5");
        assertNotEquals(uDao.getUserIDWithNames("first2","last2"),"0");
    }catch(SQLException e){e.printStackTrace();}
    }

    @Test
    public void testLogin() {
        try{
        User user = new User("5","name","password","email","first","last","m");
        assertTrue(uDao.register(user));
        LoginRequest request = new LoginRequest("name","password");
        LoginRequest request2 = new LoginRequest("name2","password2");
        LoginRequest request3 = new LoginRequest("name3","password3");
        assertEquals(uDao.login(request),"5");
        assertNotEquals(uDao.login(request),"0");
        assertNotEquals(uDao.login(request2),"1");
        assertNotEquals(uDao.login(request3),"1");
    }catch(SQLException e){e.printStackTrace();}
    }

}
