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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tylerbrady34gmail.familyclient.Models.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import models.Event;
import models.EventComparator;
import models.Person;

import static java.lang.Double.parseDouble;


/**
 * A map fragment class
 */
public class MapsFrag extends Fragment implements OnMapReadyCallback, OnMarkerClickListener {
    /**
     * Our google map
     */
    private GoogleMap mMap;
    /**
     * Our tetview
     */
    private TextView currTextView;
    /**
     * Our map allowing us to easily access events from map markers
     */
    private Map<String, Event> eventMap = new TreeMap<>();
    /**
     * A list of our polylines on the screen
     */
    List<Polyline> polylines = new ArrayList<>();

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
        }
        if (Model.getFilter().isShowMothersSide()) {
            addMapMarker(Model.getMaternalAncestors());
        }
    }


    /**
     * Puts in all the map markers on the GoogleMap
     *
     * @param people all the people being added to the map
     */
    private void addMapMarker(Set<String> people) {
        //for every person, grab their lat and long of each of their events and add it to the map
        for (String person : people) {
            List<Event> eventList = Model.getPersnEvntMap().get(person);
            //TODO COLOR COORDINATE MARKERS by eventType
            for (int i = 0; i < eventList.size(); i++) {//for each event
                Event event = eventList.get(i);
                double latitude = parseDouble(event.getLatitude());
                double longitude = parseDouble(event.getLongitude());
                String city = event.getCity();
                MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(city);
                eventMap.put(marker.getTitle(), event);
                mMap.addMarker(marker);
            }
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("MapFrag", "Entering onMarkerClick");
        clearLines();
        Event event = eventMap.get(marker.getTitle());//TODO handle markers at the same spot
        String fName = Model.getPeople().get(event.getPersonID()).getfName();
        String lName = Model.getPeople().get(event.getPersonID()).getlName();
        currTextView.setText(fName + " " + lName + "\n" + event.toString());
        doLines(event.getPersonID(), marker);

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
    /**A function which hands out the duties of updating the UI with lines
     * @param personClicked the person clicked on
     * @param marker  the marker clicked on
     * */
    private void doLines(String personClicked, Marker marker) {
        if (Model.getSettings().isShowFamilyLines()) {
            doFamilyLines(personClicked, marker);
        }
        if (Model.getSettings().isShowLifeLines()) {
            doLifeLines(personClicked);
        }
        if (Model.getSettings().isShowSpouseLines()) {
            doSpouseLines(personClicked, marker);
        }
    }

    /**
     * Draws the lines from the clicked to his parents to their parents and so on
     * @param personClicked , the person who was clicked
     * @param marker the marker that was clicked on
     */
    private void doFamilyLines(String personClicked, Marker marker) {

        int lineWidth = 12;
        List<Event> events = Model.getPersnEvntMap().get(personClicked);
        Event clickedOnEvent = null;
        for (Event event : events) {//find which event we clicked on
            if (event.getCity().equals(marker.getTitle())) {
                LatLng latLng = new LatLng(Double.parseDouble(event.getLatitude()), Double.parseDouble(event.getLongitude()));
                if ( (latLng.latitude == marker.getPosition().latitude) && (latLng.longitude == marker.getPosition().longitude) ) {
                    clickedOnEvent = event;
                }
            }
        }
        String father = Model.getPeople().get(personClicked).getFather();//the clicked persons dad
        linesHelperPerson(personClicked, father, lineWidth,clickedOnEvent);

        String mother = Model.getPeople().get(personClicked).getMother();
        linesHelperPerson(personClicked, mother, lineWidth,clickedOnEvent);
    }

    /**
     * A recursive function to draw family lines
     *
     * @param person        , The person of the current generation
     * @param nextGenPerson the mother/father of 'person'
     * @param lineWidth     the desired linewidth
     * @param clickedOnEvent if this is our first pass through this function, this param will hold the described event,else null
     */
    private void linesHelperPerson(String person, String nextGenPerson, int lineWidth,Event clickedOnEvent) {
        Event currEvent = clickedOnEvent, nextGenBirth = null;
        try {
            if(currEvent == null) {//checking to see if this value has already been fiiled by the clicked on event
                currEvent = Model.getPersnEvntMap().get(person).get(0);
            }
        } catch (Exception e) {
            Log.d("LineDrawing", "No events for ID: \'" + person + "\'");
            Log.d("LineDrawing", e.getMessage());
        }
        try {
            nextGenBirth = Model.getPersnEvntMap().get(nextGenPerson).get(0);
        } catch (Exception e) {
            Log.d("LineDrawing", "No events for ID:\'" + nextGenPerson + "\'");
            Log.d("LineDrawing", e.getMessage());
        }

        if (currEvent != null && nextGenBirth != null) {
            Log.d("LineDrawing", "Got events to draw!");
            LatLng pos = new LatLng(parseDouble(currEvent.getLatitude()), parseDouble(currEvent.getLongitude()));
            LatLng pos2 = new LatLng(parseDouble(nextGenBirth.getLatitude()), parseDouble(nextGenBirth.getLongitude()));
            drawLines(pos, pos2, lineWidth);
        }
        //Get the next generation
        if (!nextGenPerson.equals("")) {
            lineWidth -= 3;
            if (lineWidth == 0) {
                lineWidth = 1;
            }
            getNextGen(nextGenPerson, lineWidth);
        }

    }

    /**
     * Gets the next generation ready for line drawing
     *
     * @param nextGenPerson the person whose parents we need to draw lines to
     * @param lineWidth     the desired line width
     */
    private void getNextGen(String nextGenPerson, int lineWidth) {
        //Use of this function in the recursion is to help make the above function not too big
        Log.d("GetNextGen", "Recursing");
        String newPerson = Model.getPeople().get(nextGenPerson).getPersonID();
        String newNextGenF = Model.getPeople().get(nextGenPerson).getFather();//the next gen father
        String newNextGenM = Model.getPeople().get(nextGenPerson).getMother();//the next gen mother
        if (!newNextGenF.equals("")) {//if we have not hit the end of our recursion
            linesHelperPerson(newPerson, newNextGenF, lineWidth, null);
        }
        if (!newNextGenM.equals("")) {
            linesHelperPerson(newPerson, newNextGenM, lineWidth, null);
        }
    }

    /**
     * Holds the logic for drawing life lines. Meaning the events of a persons life
     *
     * @param personClicked, the personID of the event clicked
     */
    private void doLifeLines(String personClicked) {
        int lineWidth = 6;
        List<Event> events = Model.getPersnEvntMap().get(personClicked);
        for (int i = 0; i < events.size() - 1; i++) {//go through all the events chronologically
            Event event = events.get(i);
            Event event2 = events.get(i + 1);
            LatLng pos = new LatLng(parseDouble(event.getLatitude()), parseDouble(event.getLongitude()));
            LatLng pos2 = new LatLng(parseDouble(event2.getLatitude()), parseDouble(event2.getLongitude()));
            drawLines(pos, pos2, lineWidth);
        }
    }

    /**
     * Holds all the logic for doing Spouse lines, meaning a line i drawn from the clicked on to the spouses birth palce
     *
     * @param personClicked, the personID of the event clicked
     * @param marker         The marker object that was clicked on
     */
    private void doSpouseLines(String personClicked, Marker marker) {
        int lineWidth = 6;
        String spouse = Model.getPeople().get(personClicked).getSpouse();
        if (spouse.equals("")) {
            return;
        }//if there is no spouse
        List<Event> events = Model.getPersnEvntMap().get(spouse);
        Event firstEvent = null;

        if (events.size() > 0) {
            firstEvent = events.get(0);
        }//grab the first event

        for (int i = 0; i < events.size(); i++) {//will only draw lines if the spouse has events
            Event event = events.get(i);
            if (Integer.parseInt(event.getYear()) < Integer.parseInt(firstEvent.getYear())) {//update the earliest event
                firstEvent = event;
            }
            if (event.getEventType().toLowerCase().equals("birth")) {//
                LatLng pos = new LatLng(parseDouble(event.getLatitude()), parseDouble(event.getLongitude()));
                drawLines(pos, marker.getPosition(), lineWidth);
            } else if (i == events.size() - 1) {
                LatLng pos = new LatLng(parseDouble(firstEvent.getLatitude()), parseDouble(firstEvent.getLongitude()));
                drawLines(pos, marker.getPosition(), lineWidth);
            }
        }
    }

    /**
     * Clears the lines from the map when we are done with them
     */

    private void clearLines() {
        if (polylines.size() > 0) {
            for (Polyline line : polylines) {
                line.remove();
            }
        }
    }

    /**
     * Draws the lines on the map connecting people
     *
     * @param pos,      a Latlng Position
     * @param pos2      a Latlng position
     * @param lineWidth
     */
    private void drawLines(LatLng pos, LatLng pos2, int lineWidth) {
        PolylineOptions line = new PolylineOptions().add(pos, pos2).width(lineWidth).color(Color.MAGENTA);
        polylines.add(mMap.addPolyline(line));


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
            //TODO filter activity
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
