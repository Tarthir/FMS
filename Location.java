package models;

/**
 * Created by tyler on 2/10/2017.
 * This holds the information of locations in the world related to events of a users ancestors
 */

public class Location {
    /**The city of this location*/
    private String city;
    /**The country of this location*/
    private String country;
    /**The latitude this event took place at*/
    private Double latitude;
    /**The longitude this event occured at*/
    private Double longitude;

    public Location(Double longitude, String city, String country, Double latitude) {
        this.longitude = longitude;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
    }
    /**@RETURN the city related to this location*/
    public String getCity() {
        return city;
    }
    /**@RETURN the country related to this location*/
    public String getCountry() {
        return country;
    }
    /**@RETURN the latitude related to this location*/
    public Double getLatitude() {
        return latitude;
    }
    /**@RETURN the longitude related to this location*/
    public Double getLongitude() {
        return longitude;
    }
}
