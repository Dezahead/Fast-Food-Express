/*
Bug occurred after I tried to initialize the Parse session and register a subclass (I added .Application name to manifest)
 */

package com.dcv3.fastfood.fastfood;

import com.parse.Parse;
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

        ParseObject.registerSubclass(Order.class);
        Parse.initialize(this,"Gokvsi0NGFg7I87lMaRgLjz6XILpfR1AofzzxKOF",
           "cmVUZO1VJRrGSRBbc3LmdYluCjxisZKv5nEIx1fl");
    }
}
