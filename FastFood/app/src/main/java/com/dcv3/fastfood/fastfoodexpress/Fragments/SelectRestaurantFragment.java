package com.dcv3.fastfood.fastfoodexpress.Fragments;

import android.app.Fragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dcv3.fastfood.fastfoodexpress.R;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;


/**
 * Created by dezereljones on 11/17/15.
 */
public class SelectRestaurantFragment extends Fragment{

    ListView listView;
    ParseQueryAdapter<ParseObject> mainAdapter;

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

        return rootView;
    }


}
