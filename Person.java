package models;
/**
 * Created by tyler on 2/10/2017.
 * This class holds the information for a person
 */

public class Person {
    /**
     * The userName of the user to which this person belongs
     */
    private String descendant;
    /**
     * The personID of this person
     */
    private String personID;
    /**
     * The first name of the person
     * */
    private String firstname;
    /**
     * The last name of the person
     * */
    private String lastname;
    /**
     * The gender of the person, "m" or "f"
     * */
    private String gender;
    /**
     * This persons Father(Person obj). May be null
     * */
    private String fatherID;
    /**
     * The mother(Person obj) of this person. May be null
     * */
    private String motherID;
    /**
     * The spouse(Person obj) of this person. May be null
     * */
    private String spouseID;

    public Person(String personID,String descendant, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.descendant = descendant;
        this.personID = personID;
        this.firstname = firstName;
        this.lastname = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }
    public Person(String personID,String descendant, String firstname, String lastname, String gender) {
        this.descendant = descendant;
        this.personID = personID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.fatherID = "";
        this.motherID = "";
        this.spouseID = "";
    }

    public Person() {
    }

    public String getPersonID() {
        return personID;
    }

    public String getfName() {
        return firstname;
    }

    public String getlName() {
        return lastname;
    }

    public String getGender() {
        return gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    public String getDescendant() {
        return descendant;
    }
    /**
     * Compares the contents of person objects to see if they are equal
     * @RETURN true if they are equal, false otherwise
     * */
    @Override
    public boolean equals(Object o) {
        if(this.getClass() != o.getClass()){return false;}
        Person other = (Person)o;
        if(!other.getDescendant().equals(this.getDescendant())){return false;}
        if(!other.getPersonID().equals(this.getPersonID())){return false;}
        if(!other.getGender().equals(this.getGender())){return false;}
        if(!other.getMotherID().equals(this.getMotherID())){return false;}
        if(!other.getfName().equals(this.getfName())){return false;}
        if(!other.getlName().equals(this.getlName())){return false;}
        if(!other.getSpouseID().equals(this.getSpouseID())){return false;}
        if(!other.getFatherID().equals(this.getFatherID())){return false;}
        return true;

    }


    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }
}
