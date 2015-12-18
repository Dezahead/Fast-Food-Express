package com.dcv3.fastfood.fastfood.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcv3.fastfood.fastfood.R;

/**
 * Created by dezereljones on 11/28/15.
 */
public class PendingActivityFragment extends Fragment{
    public PendingActivityFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pending, container, false);
        return v;
    }


}
