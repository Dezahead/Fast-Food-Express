package com.dcv3.fastfood.fastfoodexpress.ParseObjects;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by dezereljones on 2/2/16.
 */
@ParseClassName("Menu")
public class Menu extends ParseObject {
    public Menu(String num){
    }

    String menuId;
    String menuItem;
    String restaurantNo;

}
