package functionObjects;

/**
 * Created by tyler on 2/13/2017.
 * The result of attempting to fill our database
 */

public class FillResult {
    /**The Num of persons to fill our database with*/
    private int numOfPersons;
    /**The num of events to fill our database with*/
    private int numOfEvents;
    /**The result of filling the database*/
    private String result;

    public FillResult(int numOfPersons, int numOfEvents) {
        this.numOfPersons = numOfPersons;
        this.numOfEvents = numOfEvents;
        result +="Successfully added " + this.numOfPersons + " people and " + this.numOfEvents + " events.";
    }
    /**@RETURN The Num of persons to fill our database with*/
    public int getNumOfPersons() {
        return numOfPersons;
    }
    /**@RETURN The num of events to fill our database with*/
    public int getNumOfEvents() {
        return numOfEvents;
    }
    /**@RETURN The result of filling the database*/
    public String getResult() {
        return result;
    }
}
