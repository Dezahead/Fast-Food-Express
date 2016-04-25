package com.dcv3.fastfood.fastfoodexpress.ParseObjects;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dezereljones on 1/16/16.
 */
@ParseClassName("Orders")
public class Orders extends ParseObject {


    public Orders(){
        super();
    }

    //Method saves the order to the database
    public void setdetails(String id, ArrayList<String> items, String property, double cost, String name, ArrayList<String> custom){
        String[] menuItems = new String[(items.size())];
        String[] customs = new String[custom.size()];
        Random rand = new Random();
        int randomNumber = rand.nextInt(1000) + 1;
        String num = Integer.toString(randomNumber);
        items.toArray(menuItems);
        custom.toArray(customs);

        put("userID", ParseObject.createWithoutData(ParseUser.class, id));
        put("restaurantNo", ParseObject.createWithoutData("Restaurants", property));
        put("restNo", property);
        put("Total", cost);
        put("restaurantName", name);
        put("isTracking", false);
        put("orderNo", num);

        for (int i = 0; i < menuItems.length; i++)
            add("menuItems", menuItems[i]);

        for (int i = 0; i < customs.length; i++)
            add("Customization", customs[i]);

    }

}
