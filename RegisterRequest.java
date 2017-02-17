package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * The info needed to register a new user
 */

public class RegisterRequest {
    /** Non-empty string*/
    private String userName;
    /** Non-empty string*/
    private String passWord;
    /** Non-empty string*/
    private String email;
    /** Non-empty string*/
    private String fName;
    /** Non-empty string*/
    private String lName;
    /** f or m*/
    private String gender;

    public RegisterRequest(String userName, String passWord, String email, String fName, String lName, String gender) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
    }
    /**@RETURN Gets the username of this registerRequest*/
    public String getUserName() {
        return userName;
    }
    /**@RETURN  Gets the passWord of this registerRequest*/
    public String getPassWord() {
        return passWord;
    }
    /**@RETURN  Gets the email of this registerRequest*/
    public String getEmail() {
        return email;
    }
    /**@RETURN  Gets the fName of this registerRequest*/
    public String getfName() {
        return fName;
    }
    /**@RETURN  Gets the lName of this registerRequest*/
    public String getlName() {
        return lName;
    }
    /**@RETURN  Gets the gender of this registerRequest*/
    public String getGender() {
        return gender;
    }
}
