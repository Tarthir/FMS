package dataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by tyler on 2/24/2017.
 * Used when we need to access the Database from multiple Daos in a particular order or at the same time
 */

public class MultiDao {
    private PersonDao pDao;
    private EventDao eDao;
    //private LocationDao lDao;
    private AuthTokenDao aDao;

    public MultiDao(){
        pDao = new PersonDao();
        eDao = new EventDao();
        //lDao = new LocationDao();
        aDao = new AuthTokenDao();
    }
    /**
     * Takes a userID and deletes all data related to it
     * @PARAM A String userID
     * @RETURN boolean
     * @EXCEPTION SQLException
     */
    public boolean deleteFromDataBase(String userName) {//throws SQLException{
        boolean delete = true;//If we ever return false, then this failed
        UserDao uDao = new UserDao();
        String userID = null;
        try {
            userID = uDao.getUserIDWithUserName(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(delete){
            try {
                delete = eDao.deleteEvents(userID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(delete){
            try {
                delete = pDao.deletePerson(userID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return delete;
    }

    /**
     * Clears the database
     * @RETURN VOID
     * @EXCEPTION throws SQLException
     * */
    public void doClear()throws SQLException{
        DataBase db = new DataBase();
        Connection conn = null;
        try {
            conn = db.openConnection();
            db.dropTables(conn);//connection is closed in the method. drops the tables
            conn = db.openConnection();//creats new empty tables
            db.createTables(conn);
        }catch(SQLException e){
            db.closeConnection(false,conn);
            throw e;
        }

    }

}
