package com.tylerbrady34gmail.familyclient.Models;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import models.Event;
import models.Person;

/**
 * Created by tyler on 3/20/2017.
 * A Singleton class for our Model
 */
public class Model {
    /**Our one instance of the model object*/
    private static Model ourInstance;
    /**A map with personIds and keys and People as values*/
    private static Map<String,Person> people;
    /**A map with EventIds as keys and events as values*/
    private static Map<String,Event> events;
    /**A map with People as keys and a List of events as a value*/
    private static Map<Person,List<Event>> persnEvntMap;
    /**Our current settings*/
    private static Settings settings;
    /**Our current Filter*/
    private static Filter filter;
    /**A set of Strings which are are event types*/
    private static Set<String> eventTypes;
    /**A map with type of map as keys and colors as values*/
    private static Map<String,MapColor> colorMap;
    /**Our user's person object*/
    private static Person user;
    /**Our user's paternal Ancestors*/
    private static Set<String> paternalAncestors;
    /**OOur user's maternal Ancestors*/
    private static Set<String> maternalAncestors;
    /**A map with A personID as a key and a Lsit of their children as the value*/
    private static Map<String,List<Person>> personChildren;
    static{
        initialize();
    }

    public Model() {

    }

    private static void initialize() {
        people = new TreeMap<>();
        events = new TreeMap<String, Event>();
        persnEvntMap = new TreeMap<Person, List<Event>>();
        settings = new Settings();
        filter = new Filter();
        eventTypes = new TreeSet<>();
        //colorMap = new MapColor();
        paternalAncestors = new TreeSet<>();
        maternalAncestors = new TreeSet<>();
        personChildren = new TreeMap<>();
        user = new Person();

    }


    public static Model getInstance() {
        if(ourInstance == null){
            ourInstance = new Model();
            return ourInstance;
        }
        return ourInstance;
    }

    public static Map<String, List<Person>> getPersonChildren() {
        return personChildren;
    }

    public static Map<String, Person> getPeople() {
        return people;
    }

    public static Map<String, Event> getEvents() {
        return events;
    }

    public static Map<Person, List<Event>> getPersnEvntMap() {
        return persnEvntMap;
    }

    public static Settings getSettings() {
        return settings;
    }

    public static Filter getFilter() {
        return filter;
    }

    public static Set<String> getEventTypes() {
        return eventTypes;
    }

    public static Map<String, MapColor> getColorMap() {
        return colorMap;
    }

    public static Person getUser() {
        return user;
    }

    public static Set<String> getPaternalAncestors() {
        return paternalAncestors;
    }

    public static Set<String> getMaternalAncestors() {
        return maternalAncestors;
    }
}
