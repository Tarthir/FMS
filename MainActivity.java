package com.tylerbrady34gmail.familyclient;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
            fm.beginTransaction().add(R.id.fragment_login,fragment).commit();
        }

    }
}
