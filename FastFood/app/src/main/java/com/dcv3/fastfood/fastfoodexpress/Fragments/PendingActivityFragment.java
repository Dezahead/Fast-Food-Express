package com.dcv3.fastfood.fastfoodexpress.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.dcv3.fastfood.fastfoodexpress.ParseObjects.Tracker;
import com.dcv3.fastfood.fastfoodexpress.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.ParseException;

import java.util.List;

/**
 * Created by dezereljones on 11/28/15.
 */
public class PendingActivityFragment extends Fragment{
    Boolean clicked = false;
    FragmentManager fm;
    FragmentTransaction ft;
    ParseQueryAdapter<ParseObject> mainAdapter;
    ListView listView;
    String userId;
    String restName;
    ParseGeoPoint restLoc;
    Button onMyWay;
    ParseObject rest;
    public PendingActivityFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pending, container, false);
        onMyWay = (Button)v.findViewById(R.id.omwbutton);

        userId =getArguments().getString("id");

        // query pulls only the current users orders
        // Initialize main ParseQueryAdapter
        mainAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
                // query to get orders for selected user
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Orders");
                ParseObject obj = ParseObject.createWithoutData(ParseUser.class, userId);
                //Toast.makeText(getActivity(), "clicked " + restLoc, Toast.LENGTH_LONG).show();
                query.whereEqualTo("userID", obj);

                return query;
            }
        });

        mainAdapter.setTextKey("restaurantName");


        listView = (ListView) v.findViewById(R.id.list);
        listView.setAdapter(mainAdapter);
        mainAdapter.loadObjects();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                clicked = true;
                arg0.getChildAt(position).setBackgroundColor(Color.GRAY);
                rest = mainAdapter.getItem(position);
                //getting restaurant object id
                restName = rest.getString("restaurantName");
                //Toast.makeText(getActivity(), restName, Toast.LENGTH_LONG).show();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Restaurants");
                query.whereEqualTo("restaurantName", restName);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> restaurant, ParseException e) {
                        if (e == null) {
                            restLoc = (restaurant.get(0)).getParseGeoPoint("Location");
                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });
            }
        });

        onMyWay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!clicked)
                    Toast.makeText(getActivity(), "Please Select an Order", Toast.LENGTH_LONG)
                            .show();
                else {
                    rest.put("isTracking", true);
                    rest.saveInBackground();
                    startTracker();
                }
            }
        });

        return v;
    }

    public void startTracker(){
        Tracker gps = new Tracker(getActivity(), restLoc);
        if(gps.canGetLocation()){

        }
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
