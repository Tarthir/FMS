package infoObjects;

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
    /**An exception that may or not have been thrown*/
    private Exception e;
    /**Used when a user is registered and the automatic fill command is called. We need to grab the User's personID*/
    private String userPersonID;
    /**Our error message*/
    private String message;

    public FillResult(int numOfPersons, int numOfEvents) {
        this.numOfPersons = numOfPersons;
        this.numOfEvents = numOfEvents;
        result = "Successfully added " + this.numOfPersons + " people and " + this.numOfEvents + " events.";
    }

    public FillResult(Exception e) {
        this.e = e;
        message = e.getMessage();
    }

    public FillResult() {
    }

    public FillResult(int numOfEvents, int numOfPersons, String userPersonID) {
        this.numOfEvents = numOfEvents;
        this.numOfPersons = numOfPersons;
        this.userPersonID = userPersonID;
        result = "Successfully added " + this.numOfPersons + " people and " + this.numOfEvents + " events.";
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

    public Exception getE() {
        return e;
    }

    public String getUserPersonID() {
        return userPersonID;
    }

    public String getMessage() {
        return message;
    }
}
