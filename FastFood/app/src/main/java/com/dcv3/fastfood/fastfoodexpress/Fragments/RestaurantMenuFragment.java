package com.dcv3.fastfood.fastfoodexpress.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dcv3.fastfood.fastfoodexpress.MainActivity;
import com.dcv3.fastfood.fastfoodexpress.R;
import com.parse.FindCallback;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


import com.parse.ParseException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dezereljones on 11/17/15.
 */
public class RestaurantMenuFragment extends Fragment{
    FragmentManager fm;
    FragmentTransaction ft;
    ListView listView;
    ParseQueryAdapter<ParseObject> mainAdapter;
    String restId;
    double price;
    OnFoodItemSelectedListener mCallback;
    OnCustomAddedListener mCallback2;
    Number amount;
    DecimalFormat df = new DecimalFormat("#.##");
    ParseGeoPoint restLoc;
    Boolean clicked = false;
    String customize;



    public RestaurantMenuFragment(){

    }

    //this creates an interface to communicate with the mainactivity
    public interface OnFoodItemSelectedListener {
        void OnFoodItemSelected(String item, double price, ParseGeoPoint restLocation);
    }

    //this creates an interface to communicate with the mainactivity
    public interface OnCustomAddedListener {
        void OnCustomAdded(String custom);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnFoodItemSelectedListener) activity;
            mCallback2 = (OnCustomAddedListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFoodItemSelected");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        restId =getArguments().getString("id");

        //Toast.makeText(getActivity(), "clicked " + restId, Toast.LENGTH_LONG).show();

        // Initialize main ParseQueryAdapter
        mainAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
                // query to get menuitems for selected restaurant
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Menu");
                ParseObject obj = ParseObject.createWithoutData("Restaurants", restId);
                restLoc = obj.getParseGeoPoint("Location");
                //Toast.makeText(getActivity(), "clicked " + restLoc, Toast.LENGTH_LONG).show();
                query.whereEqualTo("restaurantNo", obj);

                return query;
            }
        });

        mainAdapter.setTextKey("menuItems");

        listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(mainAdapter);
        mainAdapter.loadObjects();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                clicked = true;
                //Getting the name of each item selected
                ParseObject obj = mainAdapter.getItem(position);
                String foodItem = obj.getString("menuItems");
                //Getting the price for each item selected
                amount = obj.getNumber("Price");
                String priceString = df.format(amount.doubleValue());
                price = Double.parseDouble(priceString);

                showPopup(rootView);

                mCallback.OnFoodItemSelected(foodItem, price, restLoc);
            }
        });


        return rootView;
    }

    public void showPopup(final View anchorView) {


        View popupView = getActivity().getLayoutInflater().inflate(R.layout.popup_layout, null);

        final PopupWindow popupWindow = new PopupWindow(popupView,
                RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);

        Button yesButton = (Button) popupView.findViewById(R.id.yesButton);
        Button noButton = (Button) popupView.findViewById(R.id.noButton);

        noButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View popupView) {
                popupWindow.dismiss();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View popupView) {
                popupWindow.dismiss();
                showPopup2(anchorView);
            }
        });


        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        popupWindow.setWidth(500);
        popupWindow.setHeight(300);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);

    }

    public void showPopup2(View anchorView) {
        View popupView = getActivity().getLayoutInflater().inflate(R.layout.popup_layout2, null);

        final PopupWindow popupWindow = new PopupWindow(popupView,
                RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);

        Button submitButton = (Button) popupView.findViewById(R.id.submitButton);
        Button cancelButton = (Button) popupView.findViewById(R.id.cancelButton);
        final EditText custom = (EditText) popupView.findViewById(R.id.customEditText);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View popupView) {
                popupWindow.dismiss();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View popupView) {
                customize = custom.getText().toString();

                if(customize.length() == 0){
                    Toast.makeText(getActivity(), "Field Required", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(getActivity(), customize, Toast.LENGTH_LONG).show();
                    popupWindow.dismiss();
                    mCallback2.OnCustomAdded(customize);
                    ((MainActivity)getActivity()).orderSummary();
                }
            }
        });
        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        popupWindow.setWidth(500);
        popupWindow.setHeight(300);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
    }

    //function used to switch the fragments-DJ
    public void switchFragment(Fragment fr){
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fr);
        ft.commit();
    }
}
