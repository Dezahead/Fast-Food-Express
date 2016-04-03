package com.dcv3.fastfood.fastfoodexpress.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.dcv3.fastfood.fastfoodexpress.MainActivity;
import com.dcv3.fastfood.fastfoodexpress.ParseObjects.Menu;
import com.dcv3.fastfood.fastfoodexpress.R;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.text.ParseException;
import java.util.List;

/**
 * Created by dezereljones on 11/17/15.
 */
public class RestaurantMenuFragment extends Fragment {
    ListView listView;
    ParseQueryAdapter<ParseObject> mainAdapter;
    String restId;

    public RestaurantMenuFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        restId =getArguments().getString("id");

        //Toast.makeText(getActivity(), "clicked " + restId, Toast.LENGTH_LONG).show();

        // Initialize main ParseQueryAdapter
        mainAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
                // Here we can configure a ParseQuery to our heart's desire.
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Menu");
                ParseObject obj = ParseObject.createWithoutData("Restaurants", restId);
                query.whereEqualTo("restaurantNo", obj);

                return query;
            }
        });

        mainAdapter.setTextKey("menuItems");

        listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(mainAdapter);
        mainAdapter.loadObjects();
        //When I run this it is only returning one menu item
        return rootView;
    }
}
