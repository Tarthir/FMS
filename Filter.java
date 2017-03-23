package com.tylerbrady34gmail.familyclient.Models;

/**
 * Created by tyler on 3/20/2017.
 * Our class which holds our filters
 */

public class Filter {
    /**If we are going to show the fathers side*/
    private boolean showFathersSide = true;
    /**If we are going to show the mothers side*/
    private boolean showMothersSide = true;
    /**If we are going to show males*/
    private boolean showMales = true;
    /**If we are going to show females*/
    private boolean showFemales = true;

    //TODO handle dynamic eventTypes!

    public Filter() {
    }

    public boolean isShowFathersSide() {
        return showFathersSide;
    }

    public void toggleShowFathersSide() {
        this.showFathersSide = !this.showFathersSide;
    }

    public boolean isShowMothersSide() {
        return showMothersSide;
    }

    public void toggleShowMothersSide() {
        this.showMothersSide = !this.showMothersSide;
    }

    public boolean isShowMales() {
        return showMales;
    }

    public void toggleShowMales() {
        this.showMales = !this.showMales;
    }

    public boolean isShowFemales() {
        return showFemales;
    }

    public void toggleShowFemales() {
        this.showFemales = !this.showFemales;
    }
}
