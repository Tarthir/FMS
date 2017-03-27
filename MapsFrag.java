package com.tylerbrady34gmail.familyclient;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tylerbrady34gmail.familyclient.Models.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import models.Event;


/**
 * A map fragment class
 */
public class MapsFrag extends Fragment implements OnMapReadyCallback, OnMarkerClickListener {
    /**Our google map*/
    private GoogleMap mMap;
    /**Our tetview*/
    private TextView currTextView;
    /**Our map allowing us to easily access events from map markers*/
    private Map<String,Event> eventMap = new TreeMap<>();

    public MapsFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MapsFrags", "Entering onCreate");
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapsfrag);
        currTextView = (TextView) view.findViewById(R.id.person_info);
        mapFragment.getMapAsync(this);


        return view;
    }

    /**
     * Called when the map is ready.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setOnMarkerClickListener(this);
        if (Model.getFilter().isShowFathersSide()) {
            addMapMarker(Model.getPaternalAncestors());
           // doLines(Model.getPaternalAncestors());
        }
        if (Model.getFilter().isShowMothersSide()) {
            addMapMarker(Model.getMaternalAncestors());
           // doLines(Model.getMaternalAncestors());
        }
    }

  /*  private void doLines(Set<String> sideOfAncestors) {
        for(String id : sideOfAncestors){

        }
        if(Model.getSettings().isShowFamilyLines()){

        }
        if(Model.getSettings().isShowLifeLines()){

        }
        if(Model.getSettings().isShowSpouseLines()){

        }

        PolylineOptions line=
                new PolylineOptions().add(new LatLng())
                        .width(5).color(Color.RED);

        mMap.addPolyline(line);
    }*/

    /**
     * Puts in all the map markers on the GoogleMap
     * @param people all the people being added to the map
     * */
    private void addMapMarker(Set<String> people) {
        //for every person, grab their lat and long of each of their events and add it to the map
        for (String person : people) {
            List<Event> eventList = Model.getPersnEvntMap().get(person);
            for(int i = 0; i < eventList.size(); i++) {//for each event
                Event event = eventList.get(i);
                double latitude = Double.parseDouble(event.getLatitude());
                double longitude = Double.parseDouble(event.getLongitude());
                String city = event.getCity();
                MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(city);
                eventMap.put(marker.getTitle(),event);
                mMap.addMarker(marker);
            }
        }
    }



    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("MapFrag","Entering onMarkerClick");
        Event event = eventMap.get(marker.getTitle());
        String fName = Model.getPeople().get(event.getPersonID()).getfName();
        String lName = Model.getPeople().get(event.getPersonID()).getlName();
        currTextView.setText(fName +" " + lName + "\n" + event.toString() );


        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.my_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.settings:
                intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //updateUI();
    }


}
