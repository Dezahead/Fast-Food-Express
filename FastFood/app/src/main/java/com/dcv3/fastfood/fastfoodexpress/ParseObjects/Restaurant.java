//This class requests the restaurant information from the database
package com.dcv3.fastfood.fastfoodexpress.ParseObjects;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by dezereljones on 1/16/16.
 */

@ParseClassName("Restaurant")
public class Restaurant extends ParseObject{
    public Restaurant(){
    }

    public String getName(){
        return getString("Name");
    }

    public String getItem(){
        return getString("item");
    }

    public double getLong(){
        return getDouble("longitude");
    }

    public double getLat(){
        return getDouble("latitude");
    }

    public String getPrinter(){
        return getString("printer");
    }
}
