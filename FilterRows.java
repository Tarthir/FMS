package com.tylerbrady34gmail.familyclient.FilterRecycler;

/**
 * Created by tyler on 4/3/2017.
 */

public class FilterRows {
    private String mType;
    private boolean isOn = true;

    public FilterRows(String typeOfFilter){
        mType = typeOfFilter;
    }

    public void toggleFilter(){
        this.isOn = !this.isOn;
    }

    public String getmType() {
        return mType;
    }

    public boolean isOn() {
        return isOn;
    }
}
