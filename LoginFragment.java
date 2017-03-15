package com.tylerbrady34gmail.familyclient;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

import infoObjects.LoginRequest;
import infoObjects.LoginResult;
import infoObjects.RegisterRequest;
import infoObjects.RegisterResult;


/**
 * Created by tyler on 3/13/2017.
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*If the sign-in / registration operation succeeds, the Login
    Fragment synchronizes the application with data from the Family Map Server. Synchronization
    pulls the data from the Family Map Server and stores it so that the application can access it
    later. If sign-in / registration or data synchronization fails, the Login Fragment displays an error
    message (i.e., Android “toast”), and allows the user to retry the operation
    @Override*/
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


        /*protected void onProgressUpdate(Integer... progress) {//probably dont need
            progressBar.setProgress(progress[0]);
        }*/
        @Override
        protected void onPostExecute(Object result) {//gets us back on the main thread
            Log.d("onPostExecute", "Entering onPostExecute");
            super.onPostExecute(result);
            /*create a second AsyncTask that uses HttpURLConnection to
            retrieve the logged-in user’s family data from the server.*/
            //TODO:change to map fragment
            checkResult(result);
        }

        public void successMsg(String msg) {
            final String str = msg;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity().getApplicationContext(), R.string.success_toast, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void errorMsg(String msg) {
            final String str = msg;
            LoginFragment.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity().getApplicationContext(), R.string.error_toast, Toast.LENGTH_SHORT).show();
                }
            });
        }

        /**
         * Checks the result of the AsyncTask to see if an error occurred
         *
         * @param reqResult An Register or Login Result
         * @return boolean
         */
        void checkResult(Object reqResult) {
            if (reqResult instanceof RegisterResult) {
                Log.d("checkResult", "Is a RegisterResult");
                if (!(((RegisterResult) reqResult).getMessage().isEmpty())) {
                    Log.d("checkResult", "No error");

                } else {
                    Log.d("checkResult", "Error: " + ((RegisterResult) reqResult).getMessage());
                }
            } else if (reqResult instanceof LoginResult) {
                Log.d("checkResult", "Is a LoginResult");
                if (!(((LoginResult) reqResult).getMessage().isEmpty())) {
                    Log.d("checkResult", "No error");
                    //Toast.makeText(getActivity().getApplicationContext(), R.string.success_toast, Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("checkResult", "Error: " + ((LoginResult) reqResult).getMessage());
                    //Toast.makeText(getActivity().getApplicationContext(), R.string.error_toast, Toast.LENGTH_SHORT).show();
                }
            } else {//Something went horribly wrong
                Log.d("checkResult", "Got an object that was null or not of the right type");
                //return null;
            }
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
       /* mSignIn.setEnabled(false);
        mRegister.setEnabled(false);*/
    }

    @Override
    public void onStart() {
        Log.i("OnStart", "Starting");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i("OnResume", "Resuming");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("OnPause", "Pausing");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("OnStop", "Stopping");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i("OnDestroy", "Destroying");
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

