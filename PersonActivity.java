package com.tylerbrady34gmail.familyclient.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.tylerbrady34gmail.familyclient.MyRecyclerViewAdapter;
import com.tylerbrady34gmail.familyclient.R;

import static android.support.v7.app.AlertController.*;

/**
 * Created by tyler on 3/28/2017.
 * Supports our Person Activity
 */

public class PersonActivity extends AppCompatActivity {
    private final String TAG = "PersonActivity";
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mEventRecyclerView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView = (RecyclerView) findViewById(R.id.event_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        Log.d(TAG,"starting");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"Stopping");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"Destroying");
        super.onDestroy();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.my_menu, menu);
    }
}
