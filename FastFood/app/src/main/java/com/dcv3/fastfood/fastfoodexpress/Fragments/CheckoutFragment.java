package com.dcv3.fastfood.fastfoodexpress.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dcv3.fastfood.fastfoodexpress.MainActivity;
import com.dcv3.fastfood.fastfoodexpress.ParseObjects.Orders;
import com.dcv3.fastfood.fastfoodexpress.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by dezereljones on 4/10/16.
 */
public class CheckoutFragment extends Fragment{
    FragmentManager fm;
    FragmentTransaction ft;
    EditText firstName;
    EditText lastName;
    EditText creditCard;
    EditText expirationDate;
    TextView totalTextView;
    Button payNow;
    String userId;
    String restaurantId;
    String restName;
    String orderNum;
    boolean validationError = false;
    double total;
    double tax;
    ArrayList<String> menuItems;
    ArrayList<String> customizeItems;
    DecimalFormat df = new DecimalFormat("#.##");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_checkout, container, false);
        firstName = (EditText)rootView.findViewById(R.id.fname);
        lastName = (EditText)rootView.findViewById(R.id.lname);
        creditCard = (EditText)rootView.findViewById(R.id.ccnum);
        expirationDate = (EditText)rootView.findViewById(R.id.exp);
        payNow = (Button)rootView.findViewById(R.id.pay);
        //Retrieving items and order total before tax
        menuItems =getArguments().getStringArrayList("items");
        userId = getArguments().getString("id");
        restaurantId = getArguments().getString("restId");
        restName = getArguments().getString("restName");
        total = getArguments().getDouble("total");
        customizeItems = getArguments().getStringArrayList("custom");

        //Computing order total after tax
        tax = total * .08;
        total += tax;

        //Formatting total to 2 decimal places
        String priceString = df.format(total);
        total = Double.parseDouble(priceString);

        totalTextView = (TextView)rootView.findViewById(R.id.totalText);

        totalTextView.setText("Total:       $" + total);

        payNow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                payment();
            }
        });

        return rootView;
    }

    public void payment(){
        // Validate the log in data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder("Invalid Information");
        if (firstName.length() == 0) {
            validationError = true;
        }
        if (lastName.length() == 0) {
            validationError = true;
        }
        if (creditCard.length() != 16) {
            validationError = true;
        }
        if (expirationDate.length() != 7) {
            validationError = true;
        }
        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }
        confirmation();

    }

    public void confirmation(){
        Orders newOrder = new Orders();
        newOrder.setdetails(userId, menuItems, restaurantId, total, restName, customizeItems);
        orderNum = newOrder.getObjectId();
        newOrder.saveInBackground();

        switchFragment(new ConfirmationFragment());
    }

    //switch the fragments-DJ
    public void switchFragment(Fragment fr){
        Bundle bundle = new Bundle();
        bundle.putString("id", restaurantId);
        bundle.putString("name", restName);
        fr.setArguments(bundle);
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fr);
        ft.commit();
    }


}


