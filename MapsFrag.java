package com.tylerbrady34gmail.familyclient.ui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tylerbrady34gmail.familyclient.Models.Filter;
import com.tylerbrady34gmail.familyclient.Models.Model;
import com.tylerbrady34gmail.familyclient.Models.Utils;
import com.tylerbrady34gmail.familyclient.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import models.Event;
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
     * Our tetxview
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
    /**Our gender image view*/
    private ImageView genderImage;

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
        currTextView.setText(R.string.show_begining_text);
        genderImage = (ImageView) view.findViewById(R.id.imageView);
        mapFragment.getMapAsync(this);


        return view;
    }

    /**
     * Called when the map is ready.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        final String fathersSide = "Father's Side";
        final String mothersSide = "Mother's Side";
        mMap = map;
        mMap.setOnMarkerClickListener(this);
        if (Model.getFilter().getFilterRows().get(fathersSide).isOn()) {
            addMapMarker(Model.getPaternalAncestors());
        }
        if (Model.getFilter().getFilterRows().get(mothersSide).isOn()) {
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
        //TODO Check to see if we are too add males or females, or both
        for (String person : people) {
            List<Event> eventList = Model.getPersnEvntMap().get(person);
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
        final Event event = eventMap.get(marker.getTitle());//TODO handle markers at the same spot
        Person personClicked = Model.getPeople().get(event.getPersonID());
        String fName = personClicked.getfName();
        String lName = personClicked.getlName();

        currTextView.setText(fName + " " + lName + "\n" + event.toString());
        genderImage.setImageDrawable(Utils.getGenderIcon(this.getContext(),personClicked.getGender()));

        doLines(event.getPersonID(), marker);
        //For when the textview associated with this marker is clicked
        currTextView.setOnClickListener(new View.OnClickListener() {//set the click listener
            private Event currEvent = event;//grab the current event
            @Override
            public void onClick(View v) {
                Log.d("MapsActvity","TextField has been clicked");
                Intent intent = new Intent(getActivity(),PersonActivity.class);
                Bundle b = new Bundle();
                b.putString("person_key",currEvent.getPersonID()); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                getActivity().startActivity(intent);
            }
        });
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
            drawLines(pos, pos2, lineWidth,nextGenBirth.getEventType());
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
            drawLines(pos, pos2, lineWidth,event2.getEventType());
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

        if (events.size() != 0) {//grab the first event
            firstEvent = events.get(0);
            LatLng pos = new LatLng(parseDouble(firstEvent.getLatitude()), parseDouble(firstEvent.getLongitude()));
            drawLines(marker.getPosition(),pos, lineWidth,firstEvent.getEventType());
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
     * @param eventType the type of event being drawn
     */
    private void drawLines(LatLng pos, LatLng pos2, int lineWidth,String eventType) {
        int color = Model.getColorMap().get(eventType).getColor();//grab the appropiate color
        PolylineOptions line = new PolylineOptions().add(pos, pos2).width(lineWidth).color(color);
        polylines.add(mMap.addPolyline(line));
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.my_menu, menu);
        IconDrawable draw =  new IconDrawable(getActivity(), Iconify.IconValue.fa_filter).colorRes(R.color.white).sizeDp(40);
        menu.getItem(1).setIcon(draw);//sets filter item to have the right icon
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
            case R.id.filter:
                intent = new Intent(getActivity(), FilterActivity.class);
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
