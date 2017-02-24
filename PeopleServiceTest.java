package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;

import dataAccess.DataBase;
import dataAccess.PersonDao;
import infoObjects.PersonRequest;
import models.Person;
import service.PeopleService;
import service.PersonService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 2/22/2017.
 */

public class PeopleServiceTest {
    private PeopleService pService;
    private PersonDao pDao;
    private DataBase db;
    private Connection connection;

    @Before
    public void setUp() throws IOException {
        pDao = new PersonDao();
        pService = new PeopleService();
        db = new DataBase();
        connection = db.openConnection();
        db.createTables(connection);
        Person person1 = new Person("1","userID","fName","lName","m","fatherID","motherID","spouseID");
        Person person2 = new Person("2","userID2","fName2","lName2","f","fatherID2","motherID2","spouseID2");
        assertTrue(pDao.insertPerson(person1));
        assertTrue(pDao.insertPerson(person2));
    }

    @After
    public void tearDown() {
        connection = db.openConnection();
        db.dropTables(connection);
        return;
    }
    @Test
    public void getPeople(){
        //PersonRequest request = new PersonRequest("2");
        //Person personExpected = new Person("2","userID2","fName2","lName2","f","fatherID2","motherID2","spouseID2");
        //pService = new PersonService();
       // Person personResult = pService.getPerson(request);
        //assertEquals(personResult,personExpected);
    }
}
