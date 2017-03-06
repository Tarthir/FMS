package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * Clears all data from the database
 */

public class ClearResult {
    /**The exception thrown object*/
    private Exception e;
    /**The success message*/
    private String message;

    public ClearResult() {
        message = "Clear Succeeded";
    }

    public ClearResult(Exception e) {
        this.e = e;
        message = e.getMessage();
    }


    public Exception getE() {
        return e;
    }

    public String getMessage() {
        return message;
    }
}
