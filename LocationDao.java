package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Location;

/**
 * Created by tyler on 2/20/2017.
 * Accesses our location table in the database
 */

public class LocationDao {
    /**The database*/
    private DataBase db;
    /**The get location SQL string*/
    private String GET_LOCATION = "SELECT locationID,city,latitude,longitude,country FROM location WHERE locationID = ?";
    /**The insert location SQL string*/
    private String INSERT_LOCATION = "INSERT into location (locationID,city,latitude,longitude,country) values(?,?,?,?,?)";

    public LocationDao(){
        db = new DataBase();
    }
    /**
     * Gets a row from the location table
     * @PARAM the locationID of a particular location we want
     * @RETURN the location object associated with said locationID
     * @EXCEPTION throws SQLException
     * */
    public Location getLocation(String locationID) throws SQLException{
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        LocationCreator creator = new LocationCreator();
        ArrayList<Object> output = new ArrayList<>();
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(GET_LOCATION);
            stmt.setString(1,locationID);
            rs = stmt.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            if(rs.next()){//execute the statement
                for(int i = 1; i <= columnCount; i++){
                    output.add(rs.getObject(i));
                }
            }
            if(output.size() == 0){//nothing was added
                db.closeConnection(false, conn);
            }
            else{
                db.closeConnection(true, conn);
            }
        }
        catch(SQLException e){
            //e.printStackTrace();
            db.closeConnection(false, conn);
            throw e;
        }
        finally {
            DataBase.safeClose(rs);
            DataBase.safeClose(stmt);

        }
        return creator.createLocation(output);
    }
    /**
     * Gets a row from the location table
     * @PARAM a location object that needs to be inserted
     * @RETURN whether the isnertion was successful or not
     * @EXCEPTION throws SQLException
     * */
    public boolean insertLocation(Location location)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement(INSERT_LOCATION);
            stmt.setString(1,location.getLocationID());
            stmt.setString(2, location.getCity());
            stmt.setDouble(3, location.getLatitude());
            stmt.setDouble(4, location.getLongitude());
            stmt.setString(5, location.getCountry());
            if(stmt.executeUpdate() == 1){//execute the statement
                db.closeConnection(true, conn);
                return true;
            }
            if(!conn.isClosed()){db.closeConnection(false, conn);}
        }
        catch(SQLException e){
            //e.printStackTrace();
            db.closeConnection(false, conn);
            throw e;
        }
        finally {
            DataBase.safeClose(stmt);
        }
        return false;
    }

    /**
     * Deletes all row from the location table that belong to a particular ID
     * @PARAM the ID of location that needs to be deleted
     * @RETURN whether the deletion was successful or not
     * @EXCEPTION throws SQLException
     * */
   /* public boolean deleteLocation(String locationID)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = db.openConnection();
            stmt = conn.prepareStatement("DELETE FROM location WHERE locationID = ?");
            stmt.setString(1,locationID);
            if(stmt.executeUpdate() >= 1){//execute the statement
                db.closeConnection(true, conn);
                return true;
            }
            if(!conn.isClosed()){db.closeConnection(false, conn);}
        }
        catch(SQLException e){
            //e.printStackTrace();
            db.closeConnection(false, conn);
            throw e;
        }
        finally {
            DataBase.safeClose(stmt);
        }
        return false;
    }*/
}
