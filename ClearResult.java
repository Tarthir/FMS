package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * Clears all data from the database
 */

public class ClearResult {
    /**Whether the database cleared successfuly or not*/
   private boolean clearedSuccessfully;
    /**The exception thrown object*/
    private Exception e;
    /**The success message*/
    private String message;

    public ClearResult(boolean clearedSuccessfully) {
        this.clearedSuccessfully = clearedSuccessfully;
        if(this.clearedSuccessfully) {
            message = "Clear Succeeded";
        }
    }

    public ClearResult() {
    }

    /**
     * Whether the database cleared successfuly or not
     * @RETURN Whether the database cleared successfuly or not
     * */
    public boolean isClearedSuccessfully() {
        return clearedSuccessfully;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public String getMessage() {
        return message;
    }
}
