package models;
/**
 * Created by tyler on 2/10/2017.
 */

public class Person {
    /**
     * None empty string containing a ID to reference this person
     * */
    private String ID;
    /**
     * The user to which this person belongs
     */
    private User descendant;
    /**
     * The first name of the person
     * */
    private String fName;
    /**
     * The last name of the person
     * */
    private String lName;
    /**
     * The gender of the person, "male" or "female"
     * */
    private String gender;
    /**
     * This persons Father. May be null
     * */
    Person father;
    /**
     * The mother of this person. May be null
     * */
    Person mother;
    /**
     * The spouse of this person. May be null
     * */
    Person spouse;

    public Person(String ID, User descendant, String fName, String lName,
                  String gender, Person father, Person mother, Person spouse) {
        this.ID = ID;
        this.descendant = descendant;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public User getDescendant() {
        return descendant;
    }

    public void setDescendant(User descendant) {
        this.descendant = descendant;
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

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        mother = mother;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        spouse = spouse;
    }
}
