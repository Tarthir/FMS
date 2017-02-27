package dataAccess;

import java.util.ArrayList;

import models.Location;

/**
 * Created by tyler on 2/24/2017.
 * Creates location objects
 */

public class LocationCreator {
    public LocationCreator() {
    }

    /**
     * Creates multiple people from input
     *
     * @PARAM Arraylist location data
     * @RETURN An arraylist of event objects
     */
    public Location createLocation(ArrayList<String> locData) {
        Location loc = null;
        if (locData.size() == 0) {
            return loc;
        }
        else{
            loc = new Location(locData.get(0),locData.get(1),locData.get(2),locData.get(3));
        }
        return loc;
    }
}