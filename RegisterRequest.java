package infoObjects;

import models.Location;

/**
 * Created by tyler on 2/13/2017.
 * The info needed to register a new user
 */

public class RegisterRequest {
    /** Non-empty string*/
    private String username;
    /** Non-empty string*/
    private String password;
    /** Non-empty string*/
    private String email;
    /** Non-empty string*/
    private String firstname;
    /** Non-empty string*/
    private String lastname;
    /** f or m*/
    private String gender;
    /**Array of possible locations*/
    private Location[] locations;
    /**Array of possible female names*/
    private String[] fNames;
    /**Array of possible last names*/
    private String[] lNames;
    /**Array of possible male names*/
    private String[] mNames;

    public RegisterRequest(String username, String password, String email, String firstname, String lastname, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }

    public RegisterRequest() {
    }

    public boolean isValidRequest(){
        return  (!username.isEmpty()) && (!password.isEmpty()) && (!email.isEmpty()) &&
                (!email.isEmpty()) && (!firstname.isEmpty()) && (!lastname.isEmpty()) &&
                (!gender.isEmpty()) &&(gender.toLowerCase().equals("f") || gender.toLowerCase().equals("m"))&&
                (locations != null) && (fNames != null) && (lNames != null) && (mNames != null);
    }
    /**@RETURN Gets the username of this registerRequest*/
    public String getUserName() {
        return username;
    }
    /**@RETURN  Gets the passWord of this registerRequest*/
    public String getPassWord() {
        return password;
    }
    /**@RETURN  Gets the email of this registerRequest*/
    public String getEmail() {
        return email;
    }
    /**@RETURN  Gets the fName of this registerRequest*/
    public String getfName() {
        return firstname;
    }
    /**@RETURN  Gets the lName of this registerRequest*/
    public String getlName() {
        return lastname;
    }
    /**@RETURN  Gets the gender of this registerRequest*/
    public String getGender() {
        return gender;
    }

    public Location[] getLocations() {
        return locations;
    }

    public void setLocations(Location[] locations) {
        this.locations = locations;
    }

    public String[] getfNames() {
        return fNames;
    }

    public void setfNames(String[] fNames) {
        this.fNames = fNames;
    }//female names

    public String[] getlNames() {
        return lNames;
    }

    public void setlNames(String[] lNames) {
        this.lNames = lNames;
    }

    public String[] getmNames() {
        return mNames;
    }

    public void setmNames(String[] mNames) {
        this.mNames = mNames;
    }
}
