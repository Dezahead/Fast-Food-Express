//This class requests the restaurant information from the database
package com.dcv3.fastfood.fastfoodexpress.ParseObjects;



import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;


/**
 * Created by dezereljones on 1/16/16.
 */

@ParseClassName("Restaurants")
public class Restaurants extends ParseObject{
    private String restaurantName;
    public Restaurants(){
    }


    public String getName(){
        return getString("restaurantName");
    }

    public void setName(String restName){
        restaurantName = restName;
    }

    public String getNum(){
        return getString("restaurantNo");
    }

    public ParseGeoPoint getLocation(){return getParseGeoPoint("LocationPoint");}


}
