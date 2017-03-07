package infoObjects;

/**
 * Created by tyler on 2/13/2017.
 * The info needed to register a new user
 */

public class RegisterRequest {
    /** Non-empty string*/
    private String userName;
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

    public RegisterRequest(String username, String password, String email, String firstname, String lastname, String gender) {
        this.userName = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }

    public RegisterRequest() {
    }

    public boolean isValidRequest(){
        return  (!userName.isEmpty()) && (!password.isEmpty()) && (!email.isEmpty()) &&
                (!email.isEmpty()) && (!firstname.isEmpty()) && (!lastname.isEmpty()) &&
                (!gender.isEmpty()) &&(gender.toLowerCase().equals("f") || gender.toLowerCase().equals("m"));
    }
    /**@RETURN Gets the userName of this registerRequest*/
    public String getUserName() {
        return userName;
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

}
