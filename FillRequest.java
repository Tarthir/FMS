package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * Contains the info needed to fill a database
 */

public class FillRequest {
    /**The num of generations to generate*/
    private int numOfGenerations;
    /**The username of the user whose family tree we are going to fill*/
    private String username;

    public FillRequest(int numOfGenerations, String username) {
        this.numOfGenerations = numOfGenerations;
        this.username = username;
    }
    /**@RETURN the num of generations to generate*/
    public int getNumOfGenerations() {
        return numOfGenerations;
    }
    /**@RETURN the num of generations to generate*/
    public String getUsername() {
        return username;
    }

}
