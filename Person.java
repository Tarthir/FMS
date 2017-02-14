package models;
/**
 * Created by tyler on 2/10/2017.
 * This class holds the information for a person
 */

public class Person {
    /**
     * None empty string containing a ID to reference this person
     * */
    private String ID;
    /**
     * The user object of the user to which this person belongs
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
     * The gender of the person, "m" or "f"
     * */
    private String gender;
    /**
     * This persons Father(Person obj). May be null
     * */
    Person father;
    /**
     * The mother(Person obj) of this person. May be null
     * */
    Person mother;
    /**
     * The spouse(Person obj) of this person. May be null
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
    /**@RETRUN the personID */
    public String getID() {
        return ID;
    }
    /**@RETRUN the user that is related to this person*/
    public User getDescendant() {
        return descendant;
    }
    /**@RETRUN the first name of this person*/
    public String getfName() {
        return fName;
    }
    /**@RETRUN the last name of this person*/
    public String getlName() {
        return lName;
    }
    /**@RETRUN the gender of this person*/
    public String getGender() {
        return gender;
    }
    /**@RETRUN the father of this person*/
    public Person getFather() {
        return father;
    }
    /**@RETRUN the mother of this person*/
    public Person getMother() {
        return mother;
    }
    /**@RETRUN the spouse of this person*/
    public Person getSpouse() {
        return spouse;
    }
}
