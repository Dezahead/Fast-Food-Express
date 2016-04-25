package com.dcv3.fastfood.fastfoodexpress.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dcv3.fastfood.fastfoodexpress.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

/**
 * Created by dezereljones on 11/17/15.
 */
public class ForgotPasswordFragment extends Fragment {
    EditText emailET;
    Button sendButton;
    String email;

    public ForgotPasswordFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pwreset, container, false);

        emailET = (EditText) v.findViewById(R.id.Emailaddressfield);
        sendButton = (Button) v.findViewById(R.id.sendbutton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                email = emailET.getText().toString();
                resetPassword();
            }
        });

        return v;
    }

    public void resetPassword(){
        ParseUser.requestPasswordResetInBackground(email,
                new RequestPasswordResetCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // An email was successfully sent with reset instructions.
                        } else {
                            // Something went wrong. Look at the ParseException to see what's up.
                        }
                    }
                });
    }
}
