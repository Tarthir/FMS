package service;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import models.Event;
import models.Location;
import models.Person;
import models.User;

/**
 * Created by tyler on 2/20/2017.
 * Generates data when a user is registered
 */

public class DataGenerator {
    User user;
    public DataGenerator(User user){
        this.user = user;
    }
    /**Gets fresh data for a user
     * @PARAM locations, the array of possible locations for events
     * @PARAM fNames, the array of possible female first names
     * @PARAM lNames, the array of possible last names
     * @PARAM mNames, the array of possible male first names
     * @RETURN whether the generation process was successful or not
     * */
    //SHOULD BE ABLE TO TAKE ANY NON NEGATIVE NUMBER AS THE NUMBER OF GERNEATIONS TO FILL.
    //MAKE THE CLASS BUILD THE GENERATIONS of 2^N from bottem up, ONE N LEVEL AT A TIME
    public boolean genData(Location[] locations,String[] fNames, String[] lNames, String[] mNames){
        String[] maleOrFemale = {"m","f"};
        Random genderRand = new Random();
        Random fNameRand = new Random();
        Random lNameRand = new Random();
        Random mNameRand = new Random();
        int MAX = 29;
        Person[] people = new Person[30];
        for(int i = 0; i <= MAX; i++){//create all the generations
            if(i % 2 == 1 ){//female
                //NEED TO SET BY IDS!!!
                //people[i] = getFemale(fNames, lNames,maleOrFemale,genderRand,fNameRand,lNameRand);
                //people[i].setSpouseID(people[i-1]);//set spouses; odd indices are females.
               // people[i-1].setSpouseID(people[i]);
            }
            else{//male(evens,including zero)
                people[i] = getMale(mNames, lNames,maleOrFemale,genderRand,mNameRand,lNameRand);
            }
        }
        setMothersAndFathers(people);
        ArrayList<Event> events = genEvents(people,locations);
        insertData(people,events);//insert the data into the database
        return true;
    }

    /**Gets female persons
     * @PARAM fNames, the array of possible male first names
     * @PARAM lNames, the array of possible last names
     * @PARAM maleOrFemale, either 'f' or 'm'
     * @PARAM genderRand, random generator
     * @PARAM fNameRand, random generator
     * @PARAM lNameRand, random generator
     * @RETURN whether the generation process was successful or not
     * */
    private Person getFemale(String[] fNames, String[] lNames,String[] maleOrFemale,Random genderRand,Random fNameRand,Random lNameRand){
        UUID uuid = UUID.randomUUID();
        //return new Person(uuid.toString(), user, fNames[fNameRand.nextInt(fNames.length)],
       //         lNames[lNameRand.nextInt(lNames.length)], maleOrFemale[genderRand.nextInt(2)], null, null, null);
        return null;
    }

    /**Gets male persons
     * @PARAM lNames, the array of possible last names
     * @PARAM mNames, the array of possible male first names
     * @PARAM maleOrFemale, either 'f' or 'm'
     * @PARAM genderRand, random generator
     * @PARAM mNameRand, random generator
     * @PARAM lNameRand, random generator
     * @RETURN whether the generation process was successful or not
     * */
    private Person getMale(String[] lNames, String[] mNames,String[] maleOrFemale,Random genderRand,Random mNameRand,Random lNameRand){
        UUID uuid = UUID.randomUUID();
        //return new Person(uuid.toString(), user, mNames[mNameRand.nextInt(mNames.length)],
          //      lNames[lNameRand.nextInt(lNames.length)], maleOrFemale[genderRand.nextInt(2)], null, null, null);
        return null;
    }

    /**Sets the mothers and fathers of the people generated
     * @PARAM people, the array of people that has been generated
     * @RETURN void
     */
    private void setMothersAndFathers(Person[] people){
        int index = 2;
        int needParents = 14;//only positions 0-13 needs parents
        for(int i = 0; i < needParents; i++) {
           // people[i].setFather(people[index]);
           // people[i].setMother(people[index+1]);
            index +=2;
        }
    }

    /**Generates the events of the people generated. Called after the data has been generated
     * @PARAM people, the array of people that has been generated
     * @PARAM locations, the array of locations to choose from
     * @RETURN void
     */
    private ArrayList<Event> genEvents(Person[] people,Location[] locations){
        String[] eventType = {"Birth","Baptism","Death"};
        ArrayList<Event> events = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        Random locRand = new Random();
        for(int i = 0; i < people.length; i++){//for all the people
            int year = 0;
            for(String eventT: eventType) {//for every kind of event
                int location = locRand.nextInt(locations.length);
                year = getYear(i,eventT,people,year);
                //NEED TO HAVE MARRIAGES AT SAME LOCATION/YEAR
                //events.add(new Event(uuid.toString(), user, people[i], locations[location],eventT, Integer.toString(year)));
            }
        }
        for(int i = 1; i < people.length; i+=2){//for all the people, get marriage dates. makes sure spouses share the same marraige event
            int year = getMarriageDates(i);
            int location = locRand.nextInt(locations.length);
          //  events.add(new Event(uuid.toString(), user, people[i], locations[location],"Marriage", Integer.toString(year)));
          //  events.add(new Event(uuid.toString(), user, people[i-1], locations[location],"Marriage", Integer.toString(year)));
        }
        return events;
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
    private void insertData(Person[] people, ArrayList<Event> events){
        //USE MULTIDAO
    }
}
