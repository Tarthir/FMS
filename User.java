package models;

/**
 * Created by tyler on 2/10/2017.
 */

public class User {
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
    /**The users ID*/
    private String ID;

    public User(String ID, String userName, String passWord, String email, String fName, String lName, String gender) {
        this.ID = ID;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
