package com.dcv3.fastfood.fastfood.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcv3.fastfood.fastfood.R;

/**
 * Created by dezereljones on 11/17/15.
 */
public class OrderSummaryFragment extends Fragment {
    public OrderSummaryFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ordersummary, container, false);
        return v;
    }
}
