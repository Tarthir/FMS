package dataAccess;

import java.io.File;
import java.sql.*;

/**
 * Created by tyler on 2/13/2017.
 * This will just make sure you get the right Database linked up with our Dao functions
 */

//Prepared statement, resultset, connection classes to create database

public class DataBase {
    static {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //private Connection connection;
    /*private AuthTokenDao authDao;
    private EventDao eventDao;
    private MultiDao multDao;
    private PersonDao pDao;
    private UserDao uDao;*/

    public DataBase() {
    }

    /**
     * Opens a connection to the database
     * */
    public Connection openConnection(){
        File file = new File("db");
        Connection connection = null;

        try {
            //seperator makes sure it works on linux and windows
            String db ="db" + file.separator + "database:sqlite";
            String url = "jdbc:sqlite" + db;
            connection = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * Closes the connection with the database
     * @return void
     * */
    public void closeConnection(boolean commit,Connection connection){
        try {
            if (commit) {
                connection.commit();
            }
            else {
                connection.rollback();
            }
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates tables based on input. Wipes the database clean first and then starts creating
     * @return void
     * */
    public void createTables(Connection connection){
        try {
            Statement stmt = null;
            try {
                stmt = connection.createStatement();
               //stmt.execute();
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //public Connection getConnection() {
       // return connection;
    //}

/*    public AuthTokenDao getAuthDao() {
        return authDao;
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public MultiDao getMultDao() {
        return multDao;
    }

    public PersonDao getpDao() {
        return pDao;
    }

    public UserDao getuDao() {
        return uDao;
    }*/
}
