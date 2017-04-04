package com.tylerbrady34gmail.familyclient.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.tylerbrady34gmail.familyclient.ExpandEventView.LifeEvents;
import com.tylerbrady34gmail.familyclient.ExpandEventView.LifeEventsAdapter;
import com.tylerbrady34gmail.familyclient.ExpandFamilyView.Family;
import com.tylerbrady34gmail.familyclient.ExpandFamilyView.FamilyAdapter;
import com.tylerbrady34gmail.familyclient.Models.Model;
import com.tylerbrady34gmail.familyclient.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import models.Event;
import models.Person;


/**
 * Created by tyler on 3/28/2017.
 * Supports our Person Activity
 */

public class PersonActivity extends AppCompatActivity {
    private final String TAG = "PersonActivity";
    private final String KEY = "person_key";
    /**Recycler view for the family*/
    private RecyclerView mFamilyRecycleView;
    /**Recycler view for the events*/
    private RecyclerView mEventRecycleView;
    /**The name of the person*/
    private TextView mName;
    /**THe gender of the person*/
    private TextView mGender;
    /**The Person who was clicked on*/
    public static Person clickedOn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        Log.d(TAG,"Entering PersonActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initialize();

        //get the extras
        String clickedOnID = getIntent().getExtras().getString(KEY);
        clickedOn = Model.getPeople().get(clickedOnID);
        List<LifeEvents> lifeEvents = setUpLifeEvents();
        List<Family> family = setupFamily();
        updateTextViews();

        //Get the RecyclerViews and Adapters ready
        LifeEventsAdapter lifeEventsAdapter = new LifeEventsAdapter(this,lifeEvents);
        mEventRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mEventRecycleView.setAdapter(lifeEventsAdapter);

        FamilyAdapter familyAdapter = new FamilyAdapter(this, family);
        mFamilyRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mFamilyRecycleView.setAdapter(familyAdapter);
    }
    /**
     * Updates the UI at the top of the  screen with the textviews
     * */
    private void updateTextViews() {
        mName.setText(clickedOn.getfName() + " " + clickedOn.getlName());//set the text at the top of the screen
        if(clickedOn.getGender().equals("f")){
            mGender.setText(R.string.female_string);
        }
        else{
            mGender.setText(R.string.male_string);
        }
    }


    /**Intializes our private data members*/
    private void initialize() {
        mFamilyRecycleView = (RecyclerView) findViewById(R.id.person_recycler_view);
        mEventRecycleView = (RecyclerView) findViewById(R.id.event_recycler_view);
        mName = (TextView) findViewById(R.id.textView_name);
        mName = (TextView) findViewById(R.id.textView_name);
        mGender = (TextView) findViewById(R.id.textView_gender);
    }

    /**Handles the grabbing of the family to send to the recycler view
     * @return LinkedList<Family> object
     * */
    private LinkedList<Family> setupFamily() {
        Person father = Model.getPeople().get(clickedOn.getFather());
        Person mother = Model.getPeople().get(clickedOn.getMother());
        List<Person> people = Arrays.asList(father,mother);//grab the family
        return new LinkedList<>(Collections.singletonList(new Family("FAMILY",people)));//return the family with a title for the Widget
    }
    /**
     * Sets up the life events List object to send to the recyclerView
     * @return LinkedList<Event> object
     * */
    private List<LifeEvents> setUpLifeEvents() {
        List<Event> events =  Model.getPersnEvntMap().get(clickedOn.getPersonID());
        return new LinkedList<>(Collections.singletonList(new LifeEvents("LIFE EVENTS",events)));
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
    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.my_menu, menu);
    }*/
}
