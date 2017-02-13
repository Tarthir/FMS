package dataAccess;

/**
 * Created by tyler on 2/10/2017.
 * This handles cases where we have to get results from more than one table at a time
 */

public class MultiDao {
    private Object[] functionObjects;

    public MultiDao(Object[] functionObjects) {
        this.functionObjects = functionObjects;
    }

    public Object[] getFunctionObjects() {
        return functionObjects;
    }
}
