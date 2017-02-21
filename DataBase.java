package dataAccess;

import java.io.File;
import java.sql.*;
import java.util.concurrent.locks.StampedLock;

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
     * @PARAM commit, Whether we want to commit request or not
     * @PARAM connection, our database connection
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
    /**Allows for the safe close of a prepared statement object
     * @PARAM statement, The statement we want to close
     * @RETURN void
     */
    public static void safeClose(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**Allows for the safe close of a statement object*
     * @PARAM statement, The statement we want to close
     * @RETURN void
     */
    public static void safeClose(Statement stmt){
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates tables based on input. Wipes the database clean first and then starts creating
     * @PARAM connect, our database connection
     * @return void
     * */
    public void createTables(Connection connection){
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("drop table if exists user");
            stmt.executeUpdate("create table user ( userID text not null primary key,\n" +
                    "    userName text not null,\n" +
                    "    password text not null,\n" +
                    "    email text not null,\n" +
                    "    firstName text not null,\n" +
                    "    lastName text not null,\n" +
                    "    gender text not null )");

            stmt.executeUpdate("drop table if exists belongsTo");
            stmt.executeUpdate("create table belongsTo ( userID text not null,\n" + "personID text not null )");

            stmt.executeUpdate("drop table if exists person");
            stmt.executeUpdate("create table person ( personID text not null primary key,\n" +
                    "    firstName text not null,\n" +
                    "    lastName text not null,\n" +
                    "    father text,\n" +
                    "    mother text,\n" +
                    "    spouse text )");

            stmt.executeUpdate("drop table if exists location");
            stmt.executeUpdate("create table location ( locationID text not null primary key,\n" +
                    "    city text not null,\n" +
                    "    latitude REAL not null,\n" +
                    "    longitude REAL not null,\n" +
                    "    country text not null )");

            stmt.executeUpdate("drop table if exists tookPlaceAt");
            stmt.executeUpdate("create table tookPlaceAt ( eventID text not null primary key,\n" +
                    "    locationID text not null)");

            stmt.executeUpdate("drop table if exists events");
            stmt.executeUpdate("create table events ( eventID text not null primary key,\n" +
                    "    personID text not null,\n" +
                    "    year text not null,\n" +
                    "    eventType text not null)");

            stmt.executeUpdate("drop table if exists userEvent");
            stmt.executeUpdate("create table userEvent ( userID text not null primary key,\n" + " eventID text not null )");

            stmt.executeUpdate("drop table if exists authToken");
            stmt.executeUpdate("create table authToken ( userID text not null primary key\n" +
                    " authToken text not null\n +" +
                    "timeStamp REAL not null )");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataBase.safeClose(stmt);
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
