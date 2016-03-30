//The MainActivity file manages the switching between all fragments-DJ


package com.dcv3.fastfood.fastfoodexpress;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.dcv3.fastfood.fastfoodexpress.Fragments.ConfirmationFragment;
import com.dcv3.fastfood.fastfoodexpress.Fragments.CustomizationFragment;
import com.dcv3.fastfood.fastfoodexpress.Fragments.ForgotPasswordFragment;
import com.dcv3.fastfood.fastfoodexpress.Fragments.LoginActivityFragment;
import com.dcv3.fastfood.fastfoodexpress.Fragments.OrderSummaryFragment;
import com.dcv3.fastfood.fastfoodexpress.Fragments.PendingActivityFragment;
import com.dcv3.fastfood.fastfoodexpress.Fragments.PendorStartActivityFragment;
import com.dcv3.fastfood.fastfoodexpress.Fragments.RestaurantMenuFragment;
import com.dcv3.fastfood.fastfoodexpress.Fragments.SelectRestaurantFragment;
import com.dcv3.fastfood.fastfoodexpress.ParseObjects.Order;
import com.dcv3.fastfood.fastfoodexpress.ParseObjects.Restaurants;
import com.dcv3.fastfood.fastfoodexpress.ParseObjects.Tracker;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    FragmentManager fm;
    FragmentTransaction ft;
    String userId;
    String[] menuItems;
    String[] customization;
    String propertyNo;
    Number price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        //changes the fragment view to the login screen-DJ
        switchFragment(new LoginActivityFragment());
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //function used to switch the fragments-DJ
    public void switchFragment(Fragment fr){
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fr);
        ft.commit();
    }

    public void setUserId(String id){
        userId = id;
    }


    public void selectRestaurant(View view){

        switchFragment(new SelectRestaurantFragment());
    }

    public void viewMenu(View view){
        switchFragment(new RestaurantMenuFragment());
    }

    public void customizeFood(View view){
        switchFragment(new CustomizationFragment());
    }

    public void orderSummary(View view){
        switchFragment(new OrderSummaryFragment());
    }

    public void confirmation(View view){
        new Order(userId, menuItems, customization, propertyNo, price);
        switchFragment(new ConfirmationFragment());
    }

    public void forgotPassword(View view){
        switchFragment(new ForgotPasswordFragment());
    }

    public void logIn(View view){
        switchFragment(new LoginActivityFragment());
    }

    public void pending(View view){
        switchFragment(new PendingActivityFragment());
    }

    public void pendorstart(View view){
        switchFragment(new PendorStartActivityFragment());
    }

    public void onTheWay()
    {
        //fix this....you can't get the location this way...you have to get the
        //location based on the one they chose
        Restaurants thisRest = new Restaurants();
        ParseGeoPoint location = thisRest.getLocation();
        new Tracker(location);
        //add a function so it returns home after the tracker starts
    }
}

