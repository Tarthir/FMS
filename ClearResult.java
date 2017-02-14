package functionObjects;

/**
 * Created by tyler on 2/13/2017.
 * Clears all data from the database
 */

public class ClearResult {
    /**Whether the database cleared successfuly or not*/
   private boolean clearedSuccessfully;

    public ClearResult(boolean clearedSuccessfully) {
        this.clearedSuccessfully = clearedSuccessfully;
    }
    /**@RETURN Whether the database cleared successfuly or not*/
    public boolean isClearedSuccessfully() {
        return clearedSuccessfully;
    }
}
