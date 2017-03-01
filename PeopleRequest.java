package infoObjects;

import models.AuthToken;

/**
 * Created by tyler on 2/14/2017.
 * The request object for all people from the database belonging to one user
 */

public class PeopleRequest {
    private String authToken;

    public PeopleRequest(String authToken) {
        this.authToken = authToken;
    }

    public PeopleRequest() {
    }

    public String getAuthToken() {
        return authToken;
    }

}
