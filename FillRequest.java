package functionObjects;

/**
 * Created by tyler on 2/13/2017.
 * Contains the info needed to fill a database
 */

public class FillRequest {
    private int numOfGenerations;
    private String username;

    public FillRequest(int numOfGenerations, String username) {
        this.numOfGenerations = numOfGenerations;
        this.username = username;
    }

    public int getNumOfGenerations() {
        return numOfGenerations;
    }

    public String getUsername() {
        return username;
    }

}
