package dataAccess;

/**
 * Created by tyler on 2/10/2017.
 * This handles cases where we have to get results from more than one table at a time
 */

public class MultiDao {
    /**The obejcts returns by a multiDao*/
    private Object[] functionObjects;

    public MultiDao() {
    }

    public Object[] getFunctionObjects() {
        return functionObjects;
    }
}
