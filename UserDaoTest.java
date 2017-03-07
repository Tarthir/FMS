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
    public void setUp() throws IOException, SQLException {
        uDao = new UserDao();
        db = new DataBase();
        connection = db.openConnection();
        db.createTables(connection);
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
    public void testRegister() {
        try{
        User user = new User("name","password","email","first","last","m","peep");
        assertTrue(uDao.register(user));
        assertTrue(uDao.checkUserName("name"));
            assertFalse(uDao.checkUserName("name2"));
    }catch(SQLException e){e.printStackTrace();}
    }

    @Test
    public void testLogin() {
        try{
        User user = new User("name","password","email","first","last","m","peep");
        assertTrue(uDao.register(user));
        LoginRequest request = new LoginRequest("name","password");
        LoginRequest request2 = new LoginRequest("name2","password2");
        LoginRequest request3 = new LoginRequest("name3","password3");
        assertEquals(uDao.login(request),"peep");
        assertNotEquals(uDao.login(request2),"peep");
        assertNotEquals(uDao.login(request3),"peep");
    }catch(SQLException e){e.printStackTrace();}
    }

}
