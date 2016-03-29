package com.dcv3.fastfood.fastfoodexpress.ParseObjects;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by dezereljones on 1/3/16.
 */
@ParseClassName("User")
public class User extends ParseObject {


    public String password;

    public User(){
    }

    //i got confused here.....
    public void getPassword(String emailAddress) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("password");

        query.whereEqualTo("email", emailAddress);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> passwordList, ParseException e) {
                if (e == null) {
                    Log.d("password", "Retrieved " + passwordList.size() + " scores");
                    for (ParseObject passObject : passwordList) {
                        password = passObject.get("password").toString();
                    }
                } else {
                    Log.d("email", "Error: " + e.getMessage());
                }
            }
        });
    }
}

