//This class inflates the create account screen.  The screen allows first time
//users to create an account when they first download the app -DJ

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
public class CreateAccountFragment extends Fragment {
    public CreateAccountFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_createacct, container, false);
        return v;
    }
}