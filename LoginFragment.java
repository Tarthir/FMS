package com.tylerbrady34gmail.familyclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.tylerbrady34gmail.familyclient.Models.Model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import infoObjects.EventsRequest;
import infoObjects.EventsResult;
import infoObjects.LoginRequest;
import infoObjects.LoginResult;
import infoObjects.PeopleRequest;
import infoObjects.PeopleResult;
import infoObjects.RegisterRequest;
import infoObjects.RegisterResult;


/**
 * Created by tyler on 3/13/2017.
 * Our loginFragment
 */

public class LoginFragment extends Fragment {
    private EditText mPort_input, mHost_input, mUsername_input, mPassword_input, mEmail_input, mFName_input, mLName_input;
    private RadioButton mCheckMale, mCheckFemale;
    private Button mSignIn, mRegister;
    private ProgressBar progressBar;
    //private TextView totalSizeTextView;
    private static final String TAG = "onCreateView";
    private static final String TAG2 = "register";
    private static final String TAG3 = "login";
    private static final String GET_DATA = "httpTaskGetData";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    /*If the sign-in / registration operation succeeds, the Login
    Fragment synchronizes the application with data from the Family Map Server. Synchronization
    pulls the data from the Family Map Server and stores it so that the application can access it
    later. If sign-in / registration or data synchronization fails, the Login Fragment displays an error
    message (i.e., Android “toast”), and allows the user to retry the operation*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Entering OnCreateView");
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        setupPrivateVars(v);//links up our variables with their views

        //NEED TO TURN OFF SIGN IN BUTTON, SHOULD I CHECK EACH TEXT FIELD TO SEE IF IT HAS TEXT?
        mRegister.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Log.d(TAG, "Register Clicked");
                        register();
                    }
                });

        mSignIn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Log.d(TAG, "Login Clicked");
                        login();
                    }
                });
        Log.d(TAG, "Returing View and exiting the method");
        return v;
    }


    /**
     * Handles registering a user
     *
     * @return RegisterResult
     */
    void register() {
        Log.d(TAG2, "Entering register()");
        final String FEMALE = "f", MALE = "m";
        RegisterRequest request = null;
        if (!abletoRegister()) {
            Log.d("Register()", "Invalid info for register");
            return;
        }
        try {
            //resetViews();

            httpTask task = new httpTask();

            //if female is the only option checked
            if (mCheckFemale.isChecked() && !mCheckMale.isChecked()) {

                Log.d(TAG2, "They checked female");
                request = new RegisterRequest(mUsername_input.getText().toString(), mPassword_input.getText().toString(),
                        mEmail_input.getText().toString(), mFName_input.getText().toString(),
                        mLName_input.getText().toString(), FEMALE);

            }
            //if male is the only option checked
            else if (mCheckMale.isChecked() && !mCheckFemale.isChecked()) {

                Log.d(TAG2, "They checked male");
                request = new RegisterRequest(mUsername_input.getText().toString(), mPassword_input.getText().toString(),
                        mEmail_input.getText().toString(), mFName_input.getText().toString(),
                        mLName_input.getText().toString(), MALE);

            }
            //if we have a valid request
            if (request != null && request.isValidRequest()) {

                Log.d(TAG2, "Valid request, call the proxy");
                task.start(new URL("http://" + mHost_input.getText().toString() + ":"
                        + mPort_input.getText().toString() + "/user/register"), request);

            } else {
                Log.d(TAG2, "User checked male and female/Invalid request");
            }
        } catch (MalformedURLException e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
    }

    /**
     * Handles logging in a user
     *
     * @return LoginResult
     */
    void login() {
        if (ableToSignIn()) {
            try {
                //resetViews();
                httpTask task = new httpTask();
                Log.d(TAG3, "Entering login()");
                LoginRequest request = new LoginRequest(mUsername_input.getText().toString(), mPassword_input.getText().toString());

                if (request.isValidRequest()) {//if we are given a valid request, go forward

                    Log.d(TAG3, "Valid request");

                    task.start(new URL("http://" + mHost_input.getText().toString() + ":"
                            + mPort_input.getText().toString() + "/user/login"), request);
                } else {
                    Log.d(TAG3, "Invalid request");
                }

            } catch (MalformedURLException e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
        }
    }

    /**
     * Task which does a register or login request. Calls the proxy server which calls the server
     * */
    public class httpTask extends AsyncTask<URL, Integer, Object> {//URL im sending off
        Object request;
        Object reqResult;

        void start(URL url, Object req) {
            if (req instanceof RegisterRequest) {
                request = req;
                Log.d("start", "Do a regRequest");
                reqResult = execute(url);
            } else {
                Log.d("start", "Do a loginRequest");
                request = req;
                reqResult = execute(url);
            }
        }

        protected Object doInBackground(URL... urls) {//... says treat it like an array even tho it isnt
            Log.d("DoInBackGround", "Entering DoInBackGround");
            FamilyMapServerProxy proxy = new FamilyMapServerProxy();

            if (request instanceof LoginRequest) {
                return proxy.login(urls[0], (LoginRequest) request);
            } else {
                return proxy.register(urls[0], (RegisterRequest) request);
            }
        }

        @Override
        protected void onPostExecute(Object result) {//gets us back on the main thread
            Log.d("onPostExecute", "Entering onPostExecute");
            super.onPostExecute(result);
            checkResult(result);
        }

        /**
         * Checks the result of the AsyncTask to see if an error occurred
         * and if it succeeds to grab the data of the user
         *
         * @param reqResult An Register or Login Result
         * @return void
         */
        void checkResult(Object reqResult) {
            httpTaskGetData dataGetter = new httpTaskGetData();
            if (reqResult instanceof RegisterResult) {
                Log.d("checkResult", "Is a RegisterResult");
                RegisterResult result = (RegisterResult) reqResult;

                if (result.getMessage() == null) {//the message will only be null if there is not an error

                    Log.d("checkResult", "No error");
                    dataGetter.start(result.getAuthToken());

                } else {
                    Log.d("checkResult", "Error: " + result.getMessage());
                    Toast.makeText(getActivity(), R.string.error_toast, Toast.LENGTH_LONG).show();
                }
            } else if (reqResult instanceof LoginResult) {
                Log.d("checkResult", "Is a LoginResult");
                LoginResult result = (LoginResult) reqResult;

                if (result.getMessage() == null) {//the message will only be null if there is not an error

                    Log.d("checkResult", "No error");
                    dataGetter.start(result.getAuthToken());

                } else {//otherwise we log the error message and make an error Toast
                    Log.d("checkResult", "Error: " + result.getMessage());
                    Toast.makeText(getActivity(), R.string.error_toast, Toast.LENGTH_LONG).show();
                }
            } else {//Something went horribly wrong
                Log.d("checkResult", "Got an object that was null or not of the right type");
            }
        }
    }

    /**
     * An ASync task to grab the user data from the database
     */
    public class httpTaskGetData extends AsyncTask<URL, Integer, Object> {//URL im sending off
        String authToken;

        void start(String authToken) {
            Log.d(GET_DATA, "Do a regRequest");
            this.authToken = authToken;
            try {
                execute(new URL("http://" + mHost_input.getText().toString() + ":" + mPort_input.getText().toString() + "/person"),
                        new URL("http://" + mHost_input.getText().toString() + ":" + mPort_input.getText().toString() + "/event"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        protected Object doInBackground(URL... urls) {//... says treat it like an array even tho it isnt
            Log.d(GET_DATA, "Entering DoInBackGround");
            FamilyMapServerProxy proxy = new FamilyMapServerProxy();
            ArrayList<Object> result = new ArrayList<>();
            PeopleResult pResult = proxy.getPeople(urls[0], new PeopleRequest(authToken));
            EventsResult eResult = proxy.getEvents(urls[1], new EventsRequest(authToken));
            if (eResult.getMessage() == null || pResult.getMessage() == null) {
                Log.d(GET_DATA, "Data gathering succeeded");
                result.add(pResult);
                result.add(eResult);
                return result;
            }
            return null;

        }

        @Override
        protected void onPostExecute(Object o) {
            Log.d(GET_DATA, "Entering onPostExecute");
            ArrayList<Object> result;

           try {
               result = (ArrayList<Object>) o;
               Model.setValues(result);
               super.onPostExecute(result);
           } catch(Exception e){
                Log.d(GET_DATA, "Wrong type given...exiting...exception thrown");
                Toast.makeText(getActivity(), R.string.error_toast + ". Getting Data Failed", Toast.LENGTH_LONG).show();
                return;
            }
            Log.d(GET_DATA, "Successful data get");
            //TODO Need to get the name from the data in the Async task. Needed for sign on
            String success_toast = "Hello " + mFName_input.getText().toString() + " " + mLName_input.getText().toString() + "!";
            Toast.makeText(getActivity(), success_toast, Toast.LENGTH_LONG).show();
           // getActivity().startActivity(new Intent(getActivity(),MapsActivity.class));
            goToMap();
        }
        /**Function which goes to the MapFragment after successfully grabbing the User's data*/
        private void goToMap() {
            Log.d(GET_DATA,"Going into map Fragment");
            // Create new fragment and transaction
            Fragment newFragment = new MapsFrag();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.fragment_spot, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }

    }


    /**
     * Simply setups the private member variables
     *
     * @param v, a View object
     */
    void setupPrivateVars(View v) {
        mHost_input = (EditText) v.findViewById(R.id.server_host_input);
        mPort_input = (EditText) v.findViewById(R.id.server_port_input);
        mUsername_input = (EditText) v.findViewById(R.id.user_name_input);
        mPassword_input = (EditText) v.findViewById(R.id.password_input);
        mEmail_input = (EditText) v.findViewById(R.id.email_input);
        mFName_input = (EditText) v.findViewById(R.id.first_name_input);
        mLName_input = (EditText) v.findViewById(R.id.last_name_input);
        mCheckMale = (RadioButton) v.findViewById(R.id.male_input);
        mCheckFemale = (RadioButton) v.findViewById(R.id.female_input);
        mSignIn = (Button) v.findViewById(R.id.login_button);
        mRegister = (Button) v.findViewById(R.id.register_button);
    }

    @Override
    public void onStart() {
        Log.d("OnStart", "Starting");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("OnResume", "Resuming");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("OnPause", "Pausing");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("OnStop", "Stopping");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d("OnDestroy", "Destroying");
        super.onDestroy();
    }

    boolean ableToSignIn() {
        return !(mHost_input.getText().toString().isEmpty()
                && mPort_input.getText().toString().isEmpty()
                && mUsername_input.getText().toString().isEmpty()
                && mPassword_input.getText().toString().isEmpty());
    }

    boolean abletoRegister() {
        return ableToSignIn()
                && (mCheckMale.isChecked()
                || mCheckFemale.isChecked())
                && !(mEmail_input.getText().toString().isEmpty()
                && mFName_input.getText().toString().isEmpty()
                && mLName_input.getText().toString().isEmpty());
    }


}
