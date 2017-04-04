package com.tylerbrady34gmail.familyclient.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tylerbrady34gmail.familyclient.FilterRecycler.FilterAdapter;
import com.tylerbrady34gmail.familyclient.Models.Filter;
import com.tylerbrady34gmail.familyclient.R;

/**
 * Created by tyler on 4/3/2017.
 * Our filter activity
 */

public class FilterActivity extends AppCompatActivity {
    private final String TAG = "FilterActivity";
    private RecyclerView mFilterRecycler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"Entering FilterActivity");
        super.onCreate(savedInstanceState);

        FilterRows[] filters;
        mFilterRecycler = (RecyclerView) findViewById(R.id.filter_recycler);
        FilterAdapter lifeEventsAdapter = new FilterAdapter(this,filters);
        mFilterRecycler.setLayoutManager(new LinearLayoutManager(this));
        mFilterRecycler.setAdapter(lifeEventsAdapter);

    }

    @Override
    protected void onStart() {
        Log.d(TAG,"Starting FilterActivity");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"Stopping FilterActivity");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"Destroying FilterActivity");
        super.onDestroy();
    }
}
