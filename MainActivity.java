package com.tylerbrady34gmail.familyclient;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.MapFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_login);
        // “Create a new fragment transaction, include one add operation in it, and then commit it.”
        if(fragment == null){
            fragment = new LoginFragment();
            fm.beginTransaction().add(R.id.fragment_spot,fragment).commit();
        }


    }

    public void goToMapFrag(){
        Log.d("GoToMapFrag","Going into map Fragment");

        /*// Create new fragment and transaction
        MapFragment newFragment = new MapFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_spot, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();*/
        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
        startActivity(intent);
        finish();
    }
}
