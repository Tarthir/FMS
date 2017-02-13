package functionObjects;

/**
 * Created by tyler on 2/13/2017.
 * Clears all data from the database
 */

public class ClearResult {
   private boolean clearedSuccessfully;

    public ClearResult(boolean clearedSuccessfully) {
        this.clearedSuccessfully = clearedSuccessfully;
    }

    public boolean isClearedSuccessfully() {
        return clearedSuccessfully;
    }
}
