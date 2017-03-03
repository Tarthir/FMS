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
    private String userID;
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
    private String fatherID;
    /**
     * The mother(Person obj) of this person. May be null
     * */
    private String motherID;
    /**
     * The spouse(Person obj) of this person. May be null
     * */
    private String spouseID;

    public Person(String ID, String userID, String fName, String lName, String gender, String fatherID, String motherID, String spouseID) {
        this.ID = ID;
        this.userID = userID;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }
    public Person(String ID, String userID, String fName, String lName, String gender) {
        this.ID = ID;
        this.userID = userID;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
        this.fatherID = "";
        this.motherID = "";
        this.spouseID = "";
    }

    public Person() {
    }


    public String getID() {
        return ID;
    }

    public String getUserID() {
        return userID;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
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
    /**
     * Compares the contents of person objects to see if they are equal
     * @RETURN true if they are equal, false otherwise
     * */
    @Override
    public boolean equals(Object o) {
        if(this.getClass() != o.getClass()){return false;}
        Person other = (Person)o;
        if(!other.getUserID().equals(this.getUserID())){return false;}
        if(!other.getID().equals(this.getID())){return false;}
        if(!other.getGender().equals(this.getGender())){return false;}
        if(!other.getMotherID().equals(this.getMotherID())){return false;}
        if(!other.getfName().equals(this.getfName())){return false;}
        if(!other.getlName().equals(this.getlName())){return false;}
        if(!other.getSpouseID().equals(this.getSpouseID())){return false;}
        if(!other.getFatherID().equals(this.getFatherID())){return false;}
        return true;

    }
}
