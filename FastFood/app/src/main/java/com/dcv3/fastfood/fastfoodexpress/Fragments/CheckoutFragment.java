package com.dcv3.fastfood.fastfoodexpress.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dcv3.fastfood.fastfoodexpress.R;

/**
 * Created by dezereljones on 4/10/16.
 */
public class CheckoutFragment extends Fragment{
    TextView totalTextView;
    double total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_checkout, container, false);

        totalTextView = (TextView)rootView.findViewById(R.id.totalText);

        //getTotal();

        totalTextView.setText("Total:       $" + total);
        return rootView;
    }

    


}
