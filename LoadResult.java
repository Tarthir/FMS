package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * The result of attempting to load up our database
 */

public class LoadResult {
    /**The result of the attempt to load data*/
    private String resultMessage;
    /**The exception thrown object*/
    private Exception e;

    public LoadResult(int numUsers, int numPersons, int numEvents) {
       resultMessage = "Successfully added " + numUsers + " users, " + numPersons
                + " persons, and " + numEvents + " events to the database";
    }

    public LoadResult(Exception e) {
        this.e = e;
    }

    public LoadResult() {
    }

    /**@RETURN The result of the attempt to load data*/
    public String getResultMessage() {
        return resultMessage;
    }

    public Exception getE() {
        return e;
    }
}
