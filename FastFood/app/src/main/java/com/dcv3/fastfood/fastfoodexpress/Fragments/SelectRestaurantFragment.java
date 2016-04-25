package com.dcv3.fastfood.fastfoodexpress.Fragments;

import android.app.Activity;
import android.app.Fragment;


import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dcv3.fastfood.fastfoodexpress.R;
import com.google.android.maps.GeoPoint;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.List;


/**
 * Created by dezereljones on 11/17/15.
 */
public class SelectRestaurantFragment extends Fragment{

    ListView listView;
    ParseQueryAdapter<ParseObject> mainAdapter;
    OnRestSelectedListener mCallback;
    String id;
    String name;

    //this creates an interface to communicate with the mainactivity
    public interface OnRestSelectedListener {
        void onRestaurantSelected(String id, String name);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnRestSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selectrestaurant, container, false);

        // Initialize main ParseQueryAdapter
        mainAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), "Restaurants");
        mainAdapter.setTextKey("restaurantName");


        listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(mainAdapter);
        mainAdapter.loadObjects();

        //this listener sends the object id of the restaurant to the main activity
        //to save for later
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                ParseObject rest = mainAdapter.getItem(position);
                //getting restaurant object id
                id = rest.getObjectId();
                name = rest.getString("restaurantName");

                // Send the event to the host activity
                mCallback.onRestaurantSelected(id, name);
            }
        });

        return rootView;
    }
}
