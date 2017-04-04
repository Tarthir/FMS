package com.tylerbrady34gmail.familyclient.Models;

import android.graphics.Color;

/**
 * Created by tyler on 3/20/2017.
 * Our color values
 */

public enum myColor {
    MAGENTA(Color.MAGENTA),
    RED(Color.RED),
    BLUE(Color.BLUE),
    CYAN(Color.CYAN),
    GREEN(Color.GREEN),
    GRAY(Color.GRAY),
    YELLOW(Color.YELLOW),
    DARKGREY(Color.DKGRAY),
    LIGHTGRAY(Color.LTGRAY),
    BLACK(Color.BLACK);

    private int color;

    myColor(int c) {
        color = c;
    }


    public int getColor() {
        return color;
    }
}
