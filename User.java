package models;

/**
 * Created by tyler on 2/10/2017.
 * This class holds the information for a User
 */

public class User {
    /**The user name of this user*/
    private String username;
    /**Password of this user*/
    private String password;
    /**THe user's email address*/
    private String email;
    /**The users first name*/
    private String firstname;
    /**The users last name*/
    private String lastname;
    /**The users gender*/
    private String gender;


    public User(String username, String password, String email, String firstname, String lastname, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }

    public User() {
    }
    /**@RETRUN the userName of this user*/
    public String getUserName() {
        return username;
    }
    /**@RETRUN the passWord of this user*/
    public String getPassWord() {
        return password;
    }
    /**@RETRUN the email address of this user*/
    public String getEmail() {
        return email;
    }
    /**@RETRUN the first name of this user*/
    public String getfName() {
        return firstname;
    }
    /**@RETRUN the last name of this user*/
    public String getlName() {
        return lastname;
    }
    /**@RETRUN the gender of this user*/
    public String getGender() {
        return gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
