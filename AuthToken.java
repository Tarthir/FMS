package models;

/**
 * Created by tyler on 2/13/2017.
 * An object which holds our AuthTokens
 */

public class AuthToken {
    private String authToken;

    public AuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}
