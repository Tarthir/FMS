package test;

import org.junit.*;
import static org.junit.Assert.* ;

import java.io.*;
import java.sql.Connection;

import dataAccess.DataBase;
import dataAccess.UserDao;
import models.User;

/**
 * Created by tyler on 2/20/2017.
 */

public class UserDaoTest {
    private UserDao uDao;
    private UserDao uDao2;
    private DataBase db;
    private Connection connection;

    @Before
    public void setUp() throws IOException {
        uDao = new UserDao();
        uDao2 = new UserDao();
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
        User user = new User("5","name","password","email","first","last","m");
        User user2 = new User("1","name2","password2","email2","first2","last2","f");;
        assertTrue(uDao.register(user2));
        assertEquals(uDao.getUserIDWithNames("first2","last2"),"1");
        assertNotEquals(uDao.getUserIDWithNames("first2","last2"),"0");
        assertNotEquals(uDao.getUserIDWithNames("first","last"),"1");

        assertTrue(uDao.register(user));
        assertEquals(uDao.getUserIDWithNames("first","last"),"5");
        assertNotEquals(uDao.getUserIDWithNames("first2","last2"),"0");
    }

   // @Test
   // public void testLogin() {
       // assertFalse(uDao2.login(""));
       // assertFalse(uDao2.login("google"));
        //assertFalse(uDao2.login("gonculator"));
       // assertFalse(uDao2.login("webmaster"));
   // }
}
