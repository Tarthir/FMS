package models;

/**
 * Created by tyler on 2/10/2017.
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
