package com.dcv3.fastfood.fastfoodexpress.Fragments;

import android.app.Fragment;


import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.dcv3.fastfood.fastfoodexpress.ListViewAdapter;
import com.dcv3.fastfood.fastfoodexpress.ParseObjects.Restaurants;
import com.dcv3.fastfood.fastfoodexpress.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dezereljones on 11/17/15.
 */
public class SelectRestaurantFragment extends Fragment{
    ListView listview;
    List<ParseObject> ob;
    ListViewAdapter adapter;
    private List<Restaurants> restaurantList = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selectrestaurant, container, false);

        doInBackground();

        // Locate the listview in fragment_selectrestaurant
        listview = (ListView)rootView.findViewById(R.id.list);
        // Pass the results into ListViewAdapter.java
        adapter = new ListViewAdapter(getActivity(),


                restaurantList);
        // Binds the Adapter to the ListView
        listview.setAdapter(adapter);

        return rootView;
    }

    protected Void doInBackground(Void... params) {
        // Create the array
        restaurantList = new ArrayList<>();
        try {
            // Locate the class table named "Restaurants" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Restaurants");
            // Locate the column named "restaurantName" in Parse.com and order list
            // by ascending
            query.orderByAscending("restaurantName");
            ob = query.find();
            for (ParseObject restaurants : ob) {
                Restaurants choice = new Restaurants();
                choice.setName((String) restaurants.get("restaurantName"));
                restaurantList.add(choice);
            }
        }
        catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
