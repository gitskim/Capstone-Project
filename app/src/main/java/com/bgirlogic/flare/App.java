package com.bgirlogic.flare;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.firebase.client.Firebase;

/**
 * Created by kimsuh on 4/25/16.
 */
public class App extends MultiDexApplication {

    public static final String TAG = App.class.getSimpleName();

    public static int[] myarr = new int[4];

    /**
     * A flag to distinguish if the current activity is visible
     */
    private static boolean currentActivityVisible = true;

    /**
     * A flag to distinguish if the previous activity is visible
     */
    private static boolean previousActivityVisible;

    //TODO: figure out what are the benefits of having the static instance of app
    private static App sInstance;

    public App() {
        //TODO: figure out the purpose of having the line below "super()"
        super();
        sInstance = this;
    }

    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

    public static boolean isActivityFullLaunch() {
        return currentActivityVisible;
    }

    public static void activityResumed() {
        currentActivityVisible = true;
    }

    public static void activityPaused() {
        currentActivityVisible = false;
        int myarrSize = myarr.length;
        int myarrMid = myarrSize/2;
        int numberToSearch = 3;
        if (myarrMid < numberToSearch) {

        }
    }


}
