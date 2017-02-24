package infoObjects;

import models.AuthToken;

/**
 * Created by tyler on 2/14/2017.
 * The request object for all people from the database belonging to one user
 */

public class PeopleRequest {
    private AuthToken auth;

    public PeopleRequest(AuthToken auth) {
        this.auth = auth;
    }

    public AuthToken getAuth() {
        return auth;
    }

    /*public boolean getAllPeople(){
        return true;
    }*/
}
