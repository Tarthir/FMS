package service;

import java.sql.SQLException;

import dataAccess.DataBase;
import dataAccess.MultiDao;
import infoObjects.ClearResult;

/**
 * Created by tyler on 2/14/2017.
 * Called/Created By ClearHandler to access the Appropriate DAO class and clear our database
 */

public class ClearService {
    public ClearService() {
    }

    /***
     * Gets the result of the attempt to clear the database
     * @RETURN the result of a successful or not attempt to clear the database
     */
    public ClearResult clear(){
        MultiDao mDao = new MultiDao();
        ClearResult result;
        //System.out.println("entering");
        try {
            mDao.doClear();
           // System.out.println("cleared");
            result = new ClearResult(true);//if worked!
        } catch (SQLException e) {
            result = new ClearResult(false);//Exception was thrown
           // System.out.println("Not cleared");
            result.setE(e);
            //e.printStackTrace();
        }
        System.out.print(result.getMessage());
        return result;
    }
}
