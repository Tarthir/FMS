package com.tylerbrady34gmail.familyclient.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.tylerbrady34gmail.familyclient.R;

/**
 * Created by tyler on 3/23/2017.
 * Our settings activity. This handles all the settings we can toggle
 */

public class SettingsActivity extends AppCompatActivity {
    private final String TAG = "SettingsAct";
    private TextView mLifeStoryTextView;
    private TextView mFamilyTreeTextView;
    private TextView mSpouseTextView;
    private TextView mMapTypeTextView;
    private TextView mResynctextView;
    private TextView mLogoutTextView;
    private Spinner mLifeSpinner;
    private Spinner mFamilySpinner;
    private Spinner mSpouseSpinner;
    private Spinner mMapSpinner;
    private Switch mLifeSwitch;
    private Switch mFamilySwitch;
    private Switch mSpouseSwitch;
    //TODO get this all working
    public SettingsActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"Creating Settings Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
