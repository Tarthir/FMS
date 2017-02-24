package infoObjects;

import java.util.ArrayList;

import models.Person;

/**
 * Created by tyler on 2/23/2017.
 * Our result for getting multiple people
 */

public class PeopleResult {
    private ArrayList<Person> people;
    private Exception e;

    public PeopleResult(ArrayList<Person> people) {
        this.people = people;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
}
