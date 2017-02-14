package models;

/**
 * Created by tyler on 2/13/2017.
 * An object which holds our AuthTokens
 */

public class AuthToken {
    /**The authorization token allowing us to access the app*/
    private String authToken;

    public AuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}
