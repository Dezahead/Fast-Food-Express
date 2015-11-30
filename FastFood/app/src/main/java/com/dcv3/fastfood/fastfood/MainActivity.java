//The MainActivity file manages the switching between all fragments-DJ

package com.dcv3.fastfood.fastfood;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dcv3.fastfood.fastfood.Fragments.ConfirmationFragment;
import com.dcv3.fastfood.fastfood.Fragments.CreateAccountFragment;
import com.dcv3.fastfood.fastfood.Fragments.CustomizationFragment;
import com.dcv3.fastfood.fastfood.Fragments.ForgotPasswordFragment;
import com.dcv3.fastfood.fastfood.Fragments.LoginActivityFragment;
import com.dcv3.fastfood.fastfood.Fragments.OrderSummaryFragment;
import com.dcv3.fastfood.fastfood.Fragments.PendingActivityFragment;
import com.dcv3.fastfood.fastfood.Fragments.PendorStartActivityFragment;
import com.dcv3.fastfood.fastfood.Fragments.RestaurantMenuFragment;
import com.dcv3.fastfood.fastfood.Fragments.SelectRestaurantFragment;


public class MainActivity extends ActionBarActivity {
    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        switchFragment(new ConfirmationFragment());
    }

    public void createAccount(View view){
        switchFragment(new CreateAccountFragment());
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
}

