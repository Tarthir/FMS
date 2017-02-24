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
    public Location createLocation(ArrayList<Object> locData) {
        Location loc = null;
        if (locData.size() == 0) {
            return loc;
        }
        else{
            loc = new Location((String)locData.get(0),(String)locData.get(1),(Double)locData.get(2),(Double)locData.get(3),(String)locData.get(4));
        }
        return loc;
    }
}
