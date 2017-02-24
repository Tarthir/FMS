package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

import dataAccess.DataBase;
import dataAccess.PersonDao;
import dataAccess.UserDao;
import infoObjects.LoginRequest;
import infoObjects.PersonRequest;
import models.Person;
import models.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 2/22/2017.
 * Tests our PersonDao class
 */

public class PersonDaoTest {

    private PersonDao pDao;
    private DataBase db;
    private Connection connection;

    @Before
    public void setUp() throws IOException {
        pDao = new PersonDao();
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
    public void testInsertPerson(){
        Person person1 = new Person("1","userID","fName","lName","m","fatherID","motherID","spouseID");
        Person person2 = new Person("2","userID2","fName2","lName2","f","fatherID2","motherID2","spouseID2");
        assertTrue(pDao.insertPerson(person1));
        assertTrue(pDao.insertPerson(person2));

    }
    @Test
    public void testGetPerson() {
        Person person1 = new Person("1","userID","fName","lName","m","fatherID","motherID","spouseID");
        Person person2 = new Person("2","userID2","fName2","lName2","f","fatherID2","motherID2","spouseID2");
        PersonRequest request = new PersonRequest("1");
        PersonRequest request2 = new PersonRequest("2");
        assertTrue(pDao.insertPerson(person1));
        assertTrue(pDao.insertPerson(person2));
        ArrayList<String> dataExpected = new ArrayList<>(Arrays.asList("1","userID","fName","lName","m","fatherID","motherID","spouseID"));
        assertEquals(dataExpected,pDao.getPerson(request));
        ArrayList<String> dataExpected2 = new ArrayList<>(Arrays.asList("2","userID2","fName2","lName2","f","fatherID2","motherID2","spouseID2"));
        assertEquals(dataExpected2,pDao.getPerson(request2));

    }

    @Test
    public void testGetPeople() {
        Person person1 = new Person("1","userID","fName","lName","m","fatherID","motherID","spouseID");
        Person person2 = new Person("2","userID2","fName2","lName2","f","fatherID2","motherID2","spouseID2");
        Person person3 = new Person("3","userID","fName3","lName3","m","fatherID3","motherID3","spouseID3");
        Person person4 = new Person("4","userID","fName4","lName4","m","fatherID4","motherID4","spouseID4");
        assertTrue(pDao.insertPerson(person1));
        assertTrue(pDao.insertPerson(person2));
        assertTrue(pDao.insertPerson(person3));
        assertTrue(pDao.insertPerson(person4));
        ArrayList<String> expected1 = new ArrayList<>(Arrays.asList("1","fName","lName","m","fatherID","motherID","spouseID"));
        ArrayList<String> expected3 = new ArrayList<>(Arrays.asList("3","fName3","lName3","m","fatherID3","motherID3","spouseID3"));
        ArrayList<String> expected4 = new ArrayList<>(Arrays.asList("4","fName4","lName4","m","fatherID4","motherID4","spouseID4"));
        ArrayList<ArrayList<String>> allPeopleExpected = new ArrayList<>(Arrays.asList(expected1,expected3,expected4));
        assertEquals(allPeopleExpected, pDao.getPeople("userID"));

    }
}