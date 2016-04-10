package com.dcv3.fastfood.fastfoodexpress.ParseObjects;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by dezereljones on 1/16/16.
 */
@ParseClassName("Orders")
public class Orders extends ParseObject {

    String orderNum;
    String userId;
    String[] menuItems;
    String[] customization;
    String restNo;
    Number price;

    public Orders(){
    }

    public Orders(String id, ArrayList<String> items, String property, double cost){
        userId = id;
        menuItems = new String[(items.size())];
        items.toArray(menuItems);
        restNo = property;
        orderNum = getString("OrderNo");
        price = cost;

        ParseObject newOrder = ParseObject.create("Orders");
        newOrder.put("restaurantNo", restNo);
        newOrder.put("userID", userId);
        newOrder.put("Total", price);
        for (int i = 0; i < menuItems.length; i++)
            newOrder.add("menuItems", menuItems[i]);

        newOrder.saveInBackground();
    }
}
