package functionObjects;

/**
 * Created by tyler on 2/14/2017.
 * The request object for a particular person from the database for a user
 */

public class PersonRequest {
    /**The ID of this personRequest*/
    private String personID;

    public PersonRequest(String personID) {
        this.personID = personID;
    }
    /**
     * @RETURN Gets the personID
     * */
    public String getPersonID() {
        return personID;
    }
}
