package com.dcv3.fastfood.fastfood.Fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dcv3.fastfood.fastfood.R;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivityFragment extends Fragment {
    FragmentManager fm;
    FragmentTransaction ft;
    private EditText emailAddressET;
    private EditText passWordET;
    private Button loginButton;
    private Button createAcctButton;


    //DEZA
    //YOU CAN ALSO HAVE USERS LOGIN USING FACEBOOK VIA PARSE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        emailAddressET = (EditText)v.findViewById(R.id.Emailaddressfield);
        passWordET = (EditText)v.findViewById(R.id.passwordField);
        loginButton = (Button)v.findViewById(R.id.sendbutton);
        createAcctButton = (Button)v.findViewById(R.id.createacctbutton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                login();
            }
        });

        createAcctButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                createAcct();
            }
        });
        return v;
    }

    private void createAcct(){
        switchFragment(new CreateAccountFragment());
    }

    private void login(){
        String username = emailAddressET.getText().toString().trim();
        String password = passWordET.getText().toString().trim();

        // Validate the log in data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_email));
        }
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_password));
        }
        validationErrorMessage.append(getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage(getString(R.string.progress_login));
        dialog.show();
        // Call the Parse login method
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    switchFragment(new PendorStartActivityFragment());
                }
            }
        });
    }

    //switch the fragments-DJ
    public void switchFragment(Fragment fr){
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fr);
        ft.commit();
    }
}



