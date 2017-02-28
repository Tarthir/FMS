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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DataBase() {
    }

    /**
     * Opens a connection to the database
     */
    public Connection openConnection() {
        File file = new File("db");
        Connection connection = null;
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            //seperator makes sure it works on linux and windows
            String db = "db" + file.separator + "database.sqlite";
            String url = "jdbc:sqlite:" + db;
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Closes the connection with the database
     *
     * @return void
     * @PARAM commit, Whether we want to commit request or not
     * @PARAM connection, our database connection
     */
    public void closeConnection(boolean commit, Connection connection) {
        try {
            if (commit) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows for the safe close of a prepared statement object
     *
     * @PARAM statement, The statement we want to close
     * @RETURN void
     */
    public static void safeClose(PreparedStatement stmt) {
        if (stmt != null) {

            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Allows for the safe close of a statement object*
     *
     * @PARAM statement, The statement we want to close
     * @RETURN void
     */
    public static void safeClose(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Allows for the safe close of a resultset object*
     *
     * @PARAM statement, The statement we want to close
     * @RETURN void
     */
    public static void safeClose(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates tables based on input. Wipes the database clean first and then starts creating. Closes your connection for you
     *
     * @return void
     * @PARAM connect, our database connection
     */
    public void createTables(Connection connection) {
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

            stmt.executeUpdate("drop table if exists person");
            stmt.executeUpdate("create table person ( personID text not null primary key,\n" +
                    "    userID text not null,\n" +
                    "    firstName text not null,\n" +
                    "    lastName text not null,\n" +
                    "    gender text not null,\n" +
                    "    fatherID text,\n" +
                    "    motherID text,\n" +
                    "    spouseID text )");

            stmt.executeUpdate("drop table if exists events");
            stmt.executeUpdate("create table events ( eventID text not null primary key,\n" +
                    "    userID text not null,\n" +
                    "    personID text not null,\n" +
                    "    year text not null,\n" +
                    "    eventType text not null,\n" +
                    "    city text not null,\n" +
                    "    latitude text not null,\n" +
                    "    longitude text not null,\n" +
                    "    country text not null)");

            stmt.executeUpdate("drop table if exists authToken");
            stmt.executeUpdate("create table authToken ( authToken text not null primary key,\n" +
                    "    userID text not null,\n" +
                    "    timeStamp REAL not null)");
            closeConnection(true, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection(false, connection);
        } finally {
            DataBase.safeClose(stmt);
        }


    }

    /**
     * Drops your tables, also closes the connection for you
     *
     * @PARAM connection, your database connection
     * @RETURN void
     */
    public void dropTables(Connection connection) throws SQLException {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("drop table if exists user");
            stmt.executeUpdate("drop table if exists person");
            // stmt.executeUpdate("drop table if exists location");
            //stmt.executeUpdate("drop table if exists tookPlaceAt");
            stmt.executeUpdate("drop table if exists events");
            // stmt.executeUpdate("drop table if exists userEvent");
            stmt.executeUpdate("drop table if exists authToken");
            closeConnection(true, connection);
        } catch (SQLException e) {
            //e.printStackTrace();
            closeConnection(false, connection);
            throw e;
        } finally {
            DataBase.safeClose(stmt);
        }
    }

}
