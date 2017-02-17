package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * The result of attempting to load up our database
 */

public class LoadResult {
    /**The result of the attempt to load data*/
    private String resultMessage;

    public LoadResult(String resultMessage) {
        resultMessage = this.resultMessage;
    }
    /**@RETURN The result of the attempt to load data*/
    public String getResultMessage() {
        return resultMessage;
    }
}
