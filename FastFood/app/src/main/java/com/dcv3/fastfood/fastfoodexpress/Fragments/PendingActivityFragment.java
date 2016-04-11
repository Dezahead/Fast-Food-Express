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
 * Created by dezereljones on 11/28/15.
 */
public class PendingActivityFragment extends Fragment{
    ParseQueryAdapter<ParseObject> mainAdapter;
    ListView listView;
    public PendingActivityFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pending, container, false);

        // Initialize main ParseQueryAdapter
        mainAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), "Orders");
        mainAdapter.setTextKey("restaurantName");


        listView = (ListView) v.findViewById(R.id.list);
        listView.setAdapter(mainAdapter);
        mainAdapter.loadObjects();

        return v;
    }


}
