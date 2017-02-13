package functionObjects;

/**
 * Created by tyler on 2/13/2017.
 * The info needed to register a new user
 */

public class RegisterRequest {
    private String userName; // Non-empty string
    private String passWord; // Non-empty string
    private String email;  // Non-empty string
    private String fName;  // Non-empty string
    private String lName;  // Non-empty string
    private String gender; // “f” or “m”

    public RegisterRequest(String userName, String passWord, String email, String fName, String lName, String gender) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
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
