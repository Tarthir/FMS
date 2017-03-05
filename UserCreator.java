package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import infoObjects.RegisterRequest;
import infoObjects.RegisterResult;
import models.Person;
import models.User;

/**
 * Created by tyler on 2/10/2017.
 * Creates users for the database. Adds them in
 */

public class UserCreator {
    public UserCreator() {
    }

    /**
     * Creates one person from input
     *
     * @PARAM Arraylist of of Strings holding user data
     * @RETURN A person object
     */
    public User createUser(ArrayList<String> userData) {
        if (userData.size() < 6) {//7 is the number of parameters we should have
            return null;
        } else {//return a new person
            return new User(userData.get(0), userData.get(1), userData.get(2), userData.get(3),
                    userData.get(4), userData.get(5));
        }
    }
}
