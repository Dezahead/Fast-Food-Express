package com.dcv3.fastfood.fastfood;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by dezereljones on 12/17/15.
 */
public class Tracker implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "Tracker";
    LocationRequest myLocationRequest;
    private static final long INTERVAL = 1000 * 5; //5 seconds converted to milliseconds
    private static final long FASTEST_INTERVAL = 1000 * 5;// 5 seconds converted to milliseconds
    GoogleApiClient myGoogleApiClient;
    Location myCurrentLocation;
    Location restaurantLocation;

    //this function requests the users location every 5 seconds
    protected void createLocationRequest() {
        myLocationRequest = new LocationRequest();
        myLocationRequest.setInterval(INTERVAL);
        myLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        myLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);//requests a highly accurate location
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onProviderDisabled (String provider){
    }


    @Override
    public void onStatusChanged (String provider, int status, Bundle extras){
    }

    @Override
    public void onProviderEnabled (String provider){
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: " + connectionResult.toString());

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected - isConnected ...............: " + myGoogleApiClient.isConnected());
        startLocationUpdates();
    }



    public Tracker(Location restaurantLoc){

        restaurantLocation = restaurantLoc;
        //show error dialog if GooglePlayServices not available
        Log.d(TAG, "onCreate ...............................");
        if (!isGooglePlayServicesAvailable()) {
        }

        createLocationRequest();
        myGoogleApiClient.connect();
        myGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        Log.d(TAG, "onStart fired ..............");

    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    protected void startLocationUpdates() {
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                myGoogleApiClient, myLocationRequest, this);
        Log.d(TAG, "Location update started ..............: ");
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                myGoogleApiClient, this);
        Log.d(TAG, "Location update stopped .......................");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged..............................................");
        myCurrentLocation = location;
        if (myCurrentLocation == restaurantLocation){
            stopLocationUpdates();
            myGoogleApiClient.disconnect();
        }
    }
}
