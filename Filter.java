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
    private Map<String,FilterRows> filterRows;
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
    private void setUpEventFilterRows(){
        filterRows = new TreeMap<>();
        for(String eventType : Model.getEventTypes()){
            filterRows.put(eventType,new FilterRows(eventType));
        }
        filterRows.put("Father's Side", new FilterRows("Father's Side"));
        filterRows.put("Mother's Side", new FilterRows("Mother's Side"));
        filterRows.put("Show Males", new FilterRows("Show Males"));
        filterRows.put("Show Females", new FilterRows("Show Females"));
    }

    public Map<String, FilterRows> getFilterRows() {
        return filterRows;
    }
}
