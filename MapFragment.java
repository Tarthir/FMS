package com.tylerbrady34gmail.familyclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tyler on 3/20/2017.
 */

public class MapFragment extends Fragment{
    private final String CREATE = "OnCreate/View";
    private final String onFunc = "\'OnFunction\'";
    public MapFragment() {
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        Log.d(CREATE,"Entering onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(CREATE,"Entering onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(onFunc,"Entering onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(onFunc,"Entering onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(onFunc,"Entering onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(onFunc,"Entering onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(onFunc,"Entering onDestroy");
        super.onDestroy();
    }
}
