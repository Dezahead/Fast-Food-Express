/*
Bug occurred after I tried to initialize the Parse session and register a subclass (I added .Application name to manifest)
 */

package com.dcv3.fastfood.fastfoodexpress;

import com.dcv3.fastfood.fastfoodexpress.ParseObjects.User;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;

/**
 * Created by dezereljones on 1/16/16.
 */
public class Application extends android.app.Application {
    public Application(){
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //ParseObject.registerSubclass(User.class);
        Parse.initialize(this,"UI3XVsyUqhIWyDLNjwPq7zILvGUCxZ6qNcvEvsoa",
           "kTEeNTMpNNW9jujDpZfgq6PGbio5avN9kEIVyT4D");
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
