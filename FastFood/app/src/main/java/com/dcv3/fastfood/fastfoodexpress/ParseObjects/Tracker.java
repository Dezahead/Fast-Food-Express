//This class tracks the users distance to the restaurant and prints a ticket when the user is within 5 meters of the restaurant

package com.dcv3.fastfood.fastfoodexpress.ParseObjects;


import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by dezereljones on 2/2/16.
 */
@ParseClassName("Tracker")
public class Tracker extends ParseObject implements LocationListener{
    Context context;
    public boolean arrived = false;
    public double restLatitude;
    public double restLongitude;
    public Location restLocation;
    private LocationManager locationManager;

    //constructor
    public Tracker(ParseGeoPoint location){
        Tracker t = new Tracker(location);
        t.updateLocation(context);
        convertLocation(location);
    }

    //method requests the users location every 2 seconds until they arrive at the restaurant
    public void updateLocation(Context c) {
        while(!arrived){
            locationManager = (LocationManager)c.getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    2000,
                    5, this);
        }

    }

    //this method checks the distance between the restaurant and the user if the location of the
    //user changes
    @Override
    public void onLocationChanged(Location location){
        double dist = location.distanceTo(restLocation);
        if (dist < 10){
            arrived = true;
            /////this is where we print
        }
    }

    //converts the ParseGeoPoint to Location
    public void convertLocation(ParseGeoPoint restaurantLoc){
        restLatitude = (restaurantLoc.getLatitude()*1E6);
        restLongitude = (restaurantLoc.getLongitude()*1E6);

        restLocation.setLatitude(restLatitude);
        restLocation.setLongitude(restLongitude);
    }



    @Override
    public void onProviderDisabled(String provider){}

    @Override
    public void onProviderEnabled(String provider){}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){}

}
