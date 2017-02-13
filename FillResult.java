package functionObjects;

/**
 * Created by tyler on 2/13/2017.
 * The result of attempting to fill our database
 */

public class FillResult {
    private int numOfPersons;
    private int numOfEvents;
    private String result;

    public FillResult(int numOfPersons, int numOfEvents) {
        this.numOfPersons = numOfPersons;
        this.numOfEvents = numOfEvents;
        result +="Successfully added " + this.numOfPersons + " people and " + this.numOfEvents + " events.";
    }

    public int getNumOfPersons() {
        return numOfPersons;
    }

    public void setNumOfPersons(int numOfPersons) {
        this.numOfPersons = numOfPersons;
    }

    public int getNumOfEvents() {
        return numOfEvents;
    }

    public void setNumOfEvents(int numOfEvents) {
        this.numOfEvents = numOfEvents;
    }

    public String getResult() {
        return result;
    }
}
