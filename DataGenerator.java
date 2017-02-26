package service;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import dataAccess.PeopleCreator;
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
    private String userID;
    private int TOTAL_GENERATIONS;
    public DataGenerator(String userID){
        this.userID = userID;
    }
    /**Gets fresh data for a user
     * @PARAM FillRequest object
     * @RETURN whether the generation process was successful or not
     * */
    public FillResult genData(FillRequest request) {
        TOTAL_GENERATIONS = request.getNumOfGenerations();
        int currGeneration = TOTAL_GENERATIONS;
        //boolean filled = true;
        ArrayList<Person> people = new ArrayList<>();//one arraylist per geneartion
        int numOfPeople = (int) Math.pow(2, TOTAL_GENERATIONS);
        int adderIndex = 0;//allows us to set mother/father indicies for children
        for (int j = currGeneration; j > 0; j--) {//create all the generations starting from the top
            for (int i = 0; i < numOfPeople; i++) {//create all people of this generation with all their connections
                if (i % 2 == 0) {//male
                    people.add(getMale(request.getmNames(), request.getlNames()));
                }
                else {//female
                    people.add(getFemale(request.getfNames(), request.getlNames()));
                    //add spouses together
                    people.get(i - 1).setSpouseID(people.get(i).getID());
                    people.get(i).setSpouseID(people.get(i - 1).getID());
                    if (currGeneration < TOTAL_GENERATIONS) {
                        people.get(i - 1).setFatherID(people.get(adderIndex).getID());
                        adderIndex++;
                        people.get(i - 1).setMotherID(people.get(adderIndex).getID());
                        adderIndex++;
                        people.get(i).setFatherID(people.get(adderIndex).getID());
                        adderIndex++;
                        people.get(i).setMotherID(people.get(adderIndex).getID());
                        adderIndex++;
                    }

                }
            }
        }
        return genEvents(people,request.getLocations());
    }

    /**Gets female persons
     * @PARAM fNames, the array of possible male first names
     * @PARAM lNames, the array of possible last names
     * @RETURN whether the generation process was successful or not
     * */
    private Person getFemale(String[] fNames, String[] lNames){
        UUID uuid = UUID.randomUUID();
        Random lNameRand = new Random();
        Random fNameRand = new Random();
        return new Person(uuid.toString(), userID, fNames[fNameRand.nextInt(fNames.length)],
                lNames[lNameRand.nextInt(lNames.length)],"m", null, null, null);
    }

    /**Gets male persons
     * @PARAM lNames, the array of possible last names
     * @PARAM mNames, the array of possible male first names
     * @RETURN whether the generation process was successful or not
     * */
    private Person getMale(String[] mNames,String[] lNames){
        Random mNameRand = new Random();
        Random lNameRand = new Random();
        UUID uuid = UUID.randomUUID();
        return new Person(uuid.toString(), userID, mNames[mNameRand.nextInt(mNames.length)],
                lNames[lNameRand.nextInt(lNames.length)], "f", null, null, null);
    }

    /**Generates the events of the people generated. Called after the data has been generated
     * @PARAM people, the array of people that has been generated
     * @PARAM locations, the array of locations to choose from
     * @RETURN void
     */
    private FillResult  genEvents(ArrayList<Person> people,Location[] locations){
        String[] eventType = {"Birth","Baptism","Death"};
        ArrayList<Event> events = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        Random locRand = new Random();
        for(int i = 0; i < people.size(); i++){//for all the people
            int year = 0;
            for(String eventT: eventType) {//for every kind of event
                int location = locRand.nextInt(locations.length);
                //year = getYear(i,eventT,people,year);
                //NEED TO HAVE MARRIAGES AT SAME LOCATION/YEAR
                //events.add(new Event(uuid.toString(), user, people[i], locations[location],eventT, Integer.toString(year)));
            }
        }
        for(int i = 1; i < people.size(); i+=2){//for all the people, get marriage dates. makes sure spouses share the same marraige event
            int year = getMarriageDates(i);
            int location = locRand.nextInt(locations.length);
          //  events.add(new Event(uuid.toString(), user, people[i], locations[location],"Marriage", Integer.toString(year)));
          //  events.add(new Event(uuid.toString(), user, people[i-1], locations[location],"Marriage", Integer.toString(year)));
        }
        return insertData(null,null);
    }
    /**
     *Gets an appropriate year to use for our new event
     * @PARAM index, where along the generation tree we are
     * @PARAM eventType, the type of event we are gettinga year for
     * @PARAM people, the person array
     * @PARAM perviousYear, the previous year used for this person in an event
     * @RETURN year, the year of our new event
     */
    private int getYear(int index,String eventType,Person[] people, int previousYear){
        Random yearRand = new Random();
        switch (eventType) {
            case "Birth":
                return getBirthDates(index,yearRand);
            case "Baptism":
                return getBaptismDates(index, yearRand, previousYear);
            default: //death
                return getDeathDates(index, yearRand, previousYear);
        }


    }
    /**
     * Gets the dates for births
     * @PARAM index, where along the generation tree we are
     * @PARAM yearRand, the random number generator
     * @RETURN the year for said event
     * */
    private int getBirthDates(int index,Random yearRand){
        int gen2= 2, gen3 = 6, gen4 = 14;//where the new generations begin
        if(index < gen2){
            return yearRand.nextInt(1960) + 1950;
        }
        else if(index < gen3){
            return yearRand.nextInt(1860) + 1850;
        }
        else if(index < gen4){
            return yearRand.nextInt(1760) + 1750;
        }
        else{
            return yearRand.nextInt(1660) + 1650;
        }
    }
    /**
     * Gets the dates for baptisms
     * @PARAM index, where along the generation tree we are
     * @PARAM yearRand, the random number generator
     * @RETURN the year for said event
     * */
    private int getBaptismDates(int index,Random yearRand,int previousYear){
        int gen2= 2, gen3 = 6, gen4 = 14;//where the new generations begin
        if(index < gen2){
            return yearRand.nextInt(1965) + previousYear;
        }
        else if(index < gen3){
            return yearRand.nextInt(1865) + previousYear;
        }
        else if(index < gen4){
            return yearRand.nextInt(1765) + previousYear;
        }
        else{
            return yearRand.nextInt(1665) + previousYear;
        }
    }
    /**
     * Gets the dates for marriages
     * @PARAM yearRand, the random number generator
     * @RETURN the year for said event
     * */
    private int getMarriageDates(int index){
        Random yearRand = new Random();
        int gen2= 2, gen3 = 6, gen4 = 14;//where the new generations begin
        if (index < gen2) {
            return yearRand.nextInt(1990) + 1978;
        } else if (index < gen3) {
            return yearRand.nextInt(1890) + 1878;
        } else if (index < gen4) {
            return yearRand.nextInt(1790) + 1775;
        } else {
            return yearRand.nextInt(1690) + 1675;
        }
    }
    /**
     * Gets the dates for deaths
     * @PARAM yearRand, the random number generator
     * @RETURN the year for said event
     * */
    private int getDeathDates(int index,Random yearRand,int previousYear){
        int gen2= 2, gen3 = 6, gen4 = 14;//where the new generations begin
        if(index < gen2){
            return yearRand.nextInt(2017) + previousYear;
        }
        else if(index < gen3){
            return yearRand.nextInt(1960) + previousYear;
        }
        else if(index < gen4){
            return yearRand.nextInt(1860) + previousYear;
        }
        else{
            return yearRand.nextInt(1760) + previousYear;
        }
    }

    /**Inserts the events and people generated
     * @PARAM people, the array of people that has been generated
     * @PARAM events, the array of events that the people took part in
     * @RETURN void
     */
    private FillResult insertData(Person[] people, ArrayList<Event> events){
        //USE MULTIDAO
        return null;
    }
}
