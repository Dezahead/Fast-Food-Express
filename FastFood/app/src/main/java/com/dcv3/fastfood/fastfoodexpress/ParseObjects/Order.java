package com.dcv3.fastfood.fastfoodexpress.ParseObjects;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by dezereljones on 1/16/16.
 */
@ParseClassName("Orders")
public class Order extends ParseObject {
    ParseObject order = new ParseObject("order");
    String orderNum;
    String userId;
    String[] menuItems;
    String[] customization;
    String propertyNo;
    Number price;

    public Order(String id, String[] items, String[] custom, String property, Number cost){
        userId = id;
        menuItems = items;
        customization = custom;
        propertyNo = property;
        orderNum = getString("OrderNo");
        price = cost;

        setPropertyNum();
        setCustomization();
        setMenuItems();
        setUserId();
        setPrice();
    }

    public void setPropertyNum() {
        order.put("propertyNo", propertyNo);
    }

    public void setUserId() {
        order.put("userId", userId);
    }

    public void setMenuItems() {
        order.put("menuItems", menuItems);
    }

    public void setCustomization() {
        order.put("Customization", customization);
    }

    public void setPrice() {
        order.put("Price", price);
    }

    public String getOrderNum(){
        return orderNum;
    }

    public Number getPrice() {
        return price;
    }
}
