package com.tylerbrady34gmail.familyclient.Models;

import com.tylerbrady34gmail.familyclient.FilterRecycler.FilterRows;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tyler on 3/20/2017.
 * Our class which holds our filters
 */

public class Filter {
    /**Map of eventTypes and other filter settings and whether they are on or off*/
    private ArrayList<FilterRows> filterRows;
    /**Our Filter instance*/
    private static Filter filter;


    private Filter() {
    }

    public static Filter getInstance() {
        if(filter == null){
            filter = new Filter();
            return filter;
        }
        return filter;
    }
    /**Sets up the Filter Rows associated with events*/
    void setUpEventFilterRows(){
        filterRows = new ArrayList<>();
        for(String eventType : Model.getEventTypes()){
            filterRows.add(new FilterRows(eventType));
        }
        filterRows.add( new FilterRows("Father's Side"));
        filterRows.add( new FilterRows("Mother's Side"));
        filterRows.add( new FilterRows("Show Males"));
        filterRows.add( new FilterRows("Show Females"));
    }

    /**Used to find the FilterRow needed and return whether it is on or off
     * @param filterName  the name of the filter
     * @return boolean
     * */
    public boolean findFilterRow(String filterName){
        for(FilterRows filter : filterRows){
            if(filter.getmType().equals(filterName)){
                return filter.isOn();
            }
        }
        return false;
    }

    /**Used to toggle the FilterRow specified to on or off
     * @param filterName  the name of the filter
     * @return void
     * */
    public void toggleFilterRow(String filterName){
        for(FilterRows filter : filterRows){
            if(filter.getmType().equals(filterName)){
                filter.toggleFilter();
                break;
            }
        }
    }

    public ArrayList<FilterRows> getFilterRows() {
        return filterRows;
    }
}
