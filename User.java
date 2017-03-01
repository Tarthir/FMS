package models;

/**
 * Created by tyler on 2/10/2017.
 * This class holds the information for a User
 */

public class User {
    /**The users ID*/
    private String ID;
    /**The user name of this user*/
    private String userName;
    /**Password of this user*/
    private String passWord;
    /**THe user's email address*/
    private String email;
    /**The users first name*/
    private String fName;
    /**The users last name*/
    private String lName;
    /**The users gender*/
    private String gender;


    public User(String ID, String userName, String passWord, String email, String fName, String lName, String gender) {
        this.ID = ID;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
    }

    public User() {
    }

    /**@RETRUN the userID of this user*/
    public String getID() {
        return ID;
    }
    /**@RETRUN the userName of this user*/
    public String getUserName() {
        return userName;
    }
    /**@RETRUN the passWord of this user*/
    public String getPassWord() {
        return passWord;
    }
    /**@RETRUN the email address of this user*/
    public String getEmail() {
        return email;
    }
    /**@RETRUN the first name of this user*/
    public String getfName() {
        return fName;
    }
    /**@RETRUN the last name of this user*/
    public String getlName() {
        return lName;
    }
    /**@RETRUN the gender of this user*/
    public String getGender() {
        return gender;
    }

}
