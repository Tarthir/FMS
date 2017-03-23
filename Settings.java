package com.tylerbrady34gmail.familyclient.Models;

/**
 * Created by tyler on 3/20/2017.
 * Our settings for the family map
 */

public class Settings {
    /**If we should show life lines*/
    private boolean showLifeLines = true;
    /**If we should show family tree lines*/
    private boolean showFamilyLines = true;
    /**If we should show spouse lines*/
    private boolean showSpouseLines = true;
    public Settings() {
    }

    public boolean isShowLifeLines() {
        return showLifeLines;
    }

    public void toggleShowLifeLines() {
        this.showLifeLines = !this.showLifeLines;
    }

    public boolean isShowFamilyLines() {
        return showFamilyLines;
    }

    public void toggleShowFamilyLines() {
        this.showFamilyLines = !this.showFamilyLines;
    }

    public boolean isShowSpouseLines() {
        return showSpouseLines;
    }

    public void toggleShowSpouseLines() {
        this.showSpouseLines = !this.showSpouseLines;
    }
}
