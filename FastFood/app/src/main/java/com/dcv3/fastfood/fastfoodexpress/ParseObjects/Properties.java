package com.dcv3.fastfood.fastfoodexpress.ParseObjects;

import com.google.android.maps.GeoPoint;
import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by dezereljones on 2/2/16.
 */
@ParseClassName("Properties")

public class Properties extends ParseObject {
    public Properties(){}

    GeoPoint locationPoint;
    String propertyNum;
    String restaurantNum;
}
