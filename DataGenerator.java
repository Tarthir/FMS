package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import dataAccess.EventDao;
import dataAccess.PeopleCreator;
import dataAccess.PersonDao;
import dataAccess.UserCreator;
import dataAccess.UserDao;
import infoObjects.FillRequest;
import infoObjects.FillResult;
import models.Event;
import models.Location;
import models.Person;
import models.User;

/**
 * Created by tyler on 2/20/2017.
 * Generates data when a user is registered
 */

public class DataGenerator {
    /**
     * THe userID for the user we are genning data for
     */
    private String userID;
    /**
     * Total amount of generations we are making
     */
    private int TOTAL_GENERATIONS;
    /**
     * The people we are generating
     */
    private ArrayList<Person> people;
    /**
     * The last Names we will use in our generated data
     */
    private String[] lNames;
    /**
     * The male Names we will use in our generated data
     */
    private String[] mNames;
    /**
     * The female Names we will use in our generated data
     */
    private String[] fNames;
    /**
     * The loactions we will use in our generated data
     */
    private Location[] locations;

    public DataGenerator(String userID) {
        this.userID = userID;
        events = new ArrayList<>();
        people = new ArrayList<>();//one arraylist per generation
    }

    private ArrayList<Event> events;

    /**
     * Gets fresh data for a user
     *
     * @PARAM FillRequest object
     * @RETURN whether the generation process was successful or not
     * @EXCEPTION SQLException, IllegalArgumentException, Exception
     */
    public FillResult genData(FillRequest request) throws SQLException, IllegalArgumentException, Exception {
        User user = createUserPerson(request.getUsername());
        if (user == null) {
            throw new IllegalArgumentException();
        }
        TOTAL_GENERATIONS = request.getNumOfGenerations();
        int currGeneration = TOTAL_GENERATIONS;
        int adderIndex = 0;//allows us to set mother/father indicies for children

        for (int j = currGeneration; j > 0; j--) {//create all the generations starting from the top
            adderIndex = genPeople(j, request, adderIndex);
        }
        //put the user in the table
        UUID uuid = UUID.randomUUID();
        String father = people.get(people.size() - 2).getID();
        String mother = people.get(people.size() - 1).getID();
        people.add(new Person(uuid.toString(), user.getID(), user.getfName(), user.getlName(), user.getGender(), father, mother, ""));
        genEvents(people.get(people.size() - 1), request.getLocations(), 0);
        return insertData();
    }

    /**
     * Creates a user object using other classes
     *
     * @PARAM FString, the user's username
     * @RETURN A User object
     * @EXCEPTION SQLException, Exception
     */
    private User createUserPerson(String userName) throws SQLException, Exception {
        UserDao uDao = new UserDao();
        UserCreator creator = new UserCreator();
        return creator.createUser(uDao.selectAllFromUser(userName));
    }

    /**
     * Generates a generation of people
     *
     * @PARAM int current generation
     * @PARAM FillRequest, the request to fill
     * @PARAM int adderindex
     * @RETURN adderindex
     * @EXCEPTION Exception
     */
    private int genPeople(int currGeneration, FillRequest request, int adderIndex) throws Exception {
        int numOfPeople = (int) Math.pow(2, currGeneration);
        for (int i = 0; i < numOfPeople; i++) {//create all people of this generation with all their connections
            if (i % 2 == 0) {//male
                people.add(getMale(request.getmNames(), request.getlNames()));
                genEvents(people.get(people.size() - 1), request.getLocations(), currGeneration);
            } else {//female
                people.add(getFemale(request.getfNames(), request.getlNames()));
                genEvents(people.get(people.size() - 1), request.getLocations(), currGeneration);
                //add spouses together
                if (currGeneration == TOTAL_GENERATIONS) {
                    people.get(i - 1).setSpouseID(people.get(i).getID());
                    people.get(i).setSpouseID(people.get(i - 1).getID());
                }
                createMarriage(currGeneration, people.get(i - 1), people.get(i), request.getLocations());//marry this couple

                if (currGeneration < TOTAL_GENERATIONS) {//once we are past the oldest generation
                    setMothersAndFathers(adderIndex);
                }
            }//else
        }//for
        return adderIndex;
    }

    /**
     * Sets the values of mothers and Fathers
     * @PARAM Int, adder index to keep track of where we are at in the arraylist
     * @RETURN int, the adderindex so its parent can keep it updated
     * */
    private int setMothersAndFathers(int adderIndex){
        people.get(people.size() - 2).setSpouseID(people.get(people.size() - 1).getID());
        people.get(people.size() - 1).setSpouseID(people.get(people.size() - 2).getID());
        people.get(people.size() - 2).setFatherID(people.get(adderIndex).getID());
        adderIndex++;
        people.get(people.size() - 2).setMotherID(people.get(adderIndex).getID());
        adderIndex++;
        people.get(people.size() - 1).setFatherID(people.get(adderIndex).getID());
        adderIndex++;
        people.get(people.size() - 1).setMotherID(people.get(adderIndex).getID());
        adderIndex++;
        return adderIndex;
    }
    /**
     * Gets female persons
     *
     * @PARAM fNames, the array of possible male first names
     * @PARAM lNames, the array of possible last names
     * @RETURN whether the generation process was successful or not
     * @EXCEPTION Exception
     */
    private Person getFemale(String[] fNames, String[] lNames) throws Exception {
        UUID uuid = UUID.randomUUID();
        Random lNameRand = new Random();
        Random fNameRand = new Random();
        return new Person(uuid.toString(), userID, fNames[fNameRand.nextInt(fNames.length)],
                lNames[lNameRand.nextInt(lNames.length)], "m", null, null, null);
    }

    /**
     * Gets male persons
     *
     * @PARAM lNames, the array of possible last names
     * @PARAM mNames, the array of possible male first names
     * @RETURN whether the generation process was successful or not
     * @EXCEPTION Exception
     */
    private Person getMale(String[] mNames, String[] lNames) throws Exception {
        Random mNameRand = new Random();
        Random lNameRand = new Random();
        UUID uuid = UUID.randomUUID();
        return new Person(uuid.toString(), userID, mNames[mNameRand.nextInt(mNames.length)],
                lNames[lNameRand.nextInt(lNames.length)], "f", null, null, null);
    }

    /**
     * Generates the events of the people generated. Called after the data has been generated
     *
     * @PARAM people, the array of people that has been generated
     * @PARAM locations, the array of locations to choose from
     * @RETURN void
     * @EXCEPTION Exception
     */
    private void genEvents(Person person, Location[] locations, int currGeneration) throws Exception {
        int startYear = 1970 - (currGeneration * 50);
        String[] eventType = {"Birth", "Baptism", "Death"};
        Random locRand = new Random();
        for (String eventT : eventType) {//for every kind of event
            UUID uuid = UUID.randomUUID();
            int location = locRand.nextInt(locations.length - 1);
            int year = getYear(eventT, startYear);
            //NEED TO HAVE MARRIAGES AT SAME LOCATION/YEAR
            events.add(new Event(uuid.toString(), userID, person.getID(), Integer.toString(year), eventT, locations[location]));
        }
    }

    /**
     * Gets an appropriate year to use for our new event
     *
     * @PARAM index, where along the generation tree we are
     * @PARAM eventType, the type of event we are gettinga year for
     * @PARAM perviousYear, the previous year used for this person in an event
     * @RETURN year, the year of our new event
     * @EXCEPTION Exception
     */
    private int getYear(String eventType, int startYear) throws Exception {
        switch (eventType) {
            case "Birth":
                return getBirthDates(startYear);
            case "Baptism":
                return getBaptismDates(startYear);
            default: //death
                return getDeathDates(startYear);
        }


    }

    /**
     * Gets the dates for births
     *
     * @PARAM startYear, where along the generation tree we are year wise
     * @RETURN the year for said event
     * @EXCEPTION Exception
     */
    private int getBirthDates(int startYear) throws Exception {
        Random yearRand = new Random();
        return (yearRand.nextInt((startYear + 5) - startYear) + startYear);//born btwn 0-5 years after "start" date for this generation
    }

    /**
     * Gets the dates for baptisms
     *
     * @PARAM startYear, where along the generation tree we are year wise
     * @RETURN the year for said event
     * @EXCEPTION Exception
     */
    private int getBaptismDates(int startYear) throws Exception {
        Random yearRand = new Random();
        return (yearRand.nextInt((startYear + 10) - (startYear + 5)) + (startYear + 5));//baptized btwn 5-10
    }

    /**
     * Gets the dates for marriages
     *
     * @PARAM yearRand, the random number generator
     * @RETURN the year for said event
     * @EXCEPTION Exception
     */
    private void createMarriage(int currGeneration, Person father, Person mother, Location[] location) throws Exception {
        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        Random locRand = new Random();
        int loc = locRand.nextInt(location.length);
        int startYear = 2017 - (currGeneration * 50);
        Random yearRand = new Random();
        int marriageYear = (yearRand.nextInt((startYear + 35) - (startYear + 18)) + (startYear + 18));//getting married between 18-35
        events.add(new Event(uuid.toString(), userID, father.getID(), Integer.toString(marriageYear), "Marriage", location[loc]));
        events.add(new Event(uuid2.toString(), userID, father.getID(), Integer.toString(marriageYear), "Marriage", location[loc]));
    }

    /**
     * Gets the dates for deaths
     *
     * @PARAM startYear, the year we start with in this generation/person
     * @RETURN the year for said event
     * @EXCEPTION Exception
     */
    private int getDeathDates(int startYear) throws Exception {
        Random yearRand = new Random();
        int year = (yearRand.nextInt((startYear + 90) - (startYear + 50)) + (startYear + 50));//die btwn 50-80ish years after birth
        if (year > 2017) {
            return 2016;
        }
        return year;
    }

    /**
     * Inserts the events and people generated
     *
     * @RETURN void
     * @EXCEPTION Exception,SQLException
     */
    private FillResult insertData() throws SQLException, Exception {
        PersonDao pDao = new PersonDao();
        EventDao eDao = new EventDao();
        String userPersonID = "";
        for (int i = 0; i < people.size(); i++) {
            pDao.insertPerson(people.get(i));
            if(i == people.size()-1){//if the last person(Which is the user)
                userPersonID = people.get(i).getID();
            }
        }
        for (Event e : events) {
            eDao.insertEvent(e);
        }
        return new FillResult( events.size(),people.size(),userPersonID);
    }

}
