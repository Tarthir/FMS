package infoObjects;

import models.Location;

/**
 * Created by tyler on 2/13/2017.
 * Contains the info needed to fill a database
 */

public class FillRequest {
    /**The num of generations to generate*/
    private int numOfGenerations;
    /**The username of the user whose family tree we are going to fill*/
    private String username;
    /**Array of possible locations*/
    private Location[] locations;
    /**Array of possible female names*/
    private String[] fNames;
    /**Array of possible last names*/
    private String[] lNames;
    /**Array of possible male names*/
    private String[] mNames;

    public FillRequest(int numOfGenerations, String username, Location[] locations, String[] fNames, String[] lNames, String[] mNames) {
        this.numOfGenerations = numOfGenerations;
        this.username = username;
        this.locations = locations;
        this.fNames = fNames;
        this.lNames = lNames;
        this.mNames = mNames;
    }

    /**@RETURN the num of generations to generate*/
    public int getNumOfGenerations() {
        return numOfGenerations;
    }
    /**@RETURN the num of generations to generate*/
    public String getUsername() {
        return username;
    }

    public Location[] getLocations() {
        return locations;
    }

    public String[] getfNames() {
        return fNames;
    }

    public String[] getlNames() {
        return lNames;
    }

    public String[] getmNames() {
        return mNames;
    }
}
