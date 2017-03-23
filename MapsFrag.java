package com.tylerbrady34gmail.familyclient;


import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tylerbrady34gmail.familyclient.Models.Model;

import java.util.List;
import java.util.Set;

import models.Event;


/**
 * A map fragment class
 */
public class MapsFrag extends Fragment implements OnMapReadyCallback, OnMarkerClickListener {

    private GoogleMap mMap;

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
        mapFragment.getMapAsync(this);

        return view;
    }

    /**
     * Called when the map is ready.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        if (Model.getFilter().isShowFathersSide()) {
            addMapMarker(Model.getPaternalAncestors());
        }
        if (Model.getFilter().isShowMothersSide()) {
            addMapMarker(Model.getMaternalAncestors());
        }
    }

    /**
     * Puts in all the map markers on the GoogleMap
     * @param people all the people being added to the map
     * */
    private void addMapMarker(Set<String> people) {
        //for every person, grab their lat and long of each of their events and add it to the map

        for (String person : people) {
            List<Event> eventList = Model.getPersnEvntMap().get(person);
            for(Event event : eventList) {//for each event
                double latitude = Double.parseDouble(event.getLatitude());
                double longitude = Double.parseDouble(event.getLongitude());
                String city = event.getCity();
                mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(city));
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(getActivity(), marker.getTitle() + " has been clicked.", Toast.LENGTH_SHORT).show();
        }

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
