//This class inflates the confirmation fragment.  The confirmation fragment shows the user
// a confirmation screen after they order and pay for their food -DJ

package com.dcv3.fastfood.fastfoodexpress.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dcv3.fastfood.fastfoodexpress.ParseObjects.Orders;
import com.dcv3.fastfood.fastfoodexpress.ParseObjects.Tracker;
import com.dcv3.fastfood.fastfoodexpress.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by dezereljones on 11/17/15.
 */
public class ConfirmationFragment extends Fragment {
    FragmentManager fm;
    FragmentTransaction ft;
    Button onMyWay;
    String restId;
    String restName;
    ParseObject rest;
    public ConfirmationFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirmation, container, false);
        Bundle bundle = this.getArguments();
        restId = bundle.getString("id");
        restName = bundle.getString("name");
        onMyWay = (Button)v.findViewById(R.id.button2);

        onMyWay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Restaurants");
                query.whereEqualTo("restaurantName", restName);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> restaurant, ParseException e) {
                        if (e == null) {
                            ParseGeoPoint restLoc = (restaurant.get(0)).getParseGeoPoint("Location");
                            startTracker(restLoc);
                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });

            }
        });

        return v;
    }

    public void startTracker(ParseGeoPoint restLoc){
        Tracker gps = new Tracker(getActivity(), restLoc);
        if(gps.canGetLocation()){

        }

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Orders");
        query2.whereEqualTo("restaurantName", restName);
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> restaurant, ParseException e) {
                if (e == null) {
                    rest = restaurant.get(0);
                    rest.put("isTracking", true);
                    rest.saveInBackground();
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        switchFragment(new PendorStartActivityFragment());

    }

    //switch the fragments-DJ
    public void switchFragment(Fragment fr){
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fr);
        ft.commit();
    }
}
