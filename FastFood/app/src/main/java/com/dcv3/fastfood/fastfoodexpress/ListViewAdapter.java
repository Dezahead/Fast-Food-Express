package com.dcv3.fastfood.fastfoodexpress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dcv3.fastfood.fastfoodexpress.ParseObjects.Restaurants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dezereljones on 3/22/16.
 */
public class ListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    private List<Restaurants> restaurantList = null;
    private ArrayList<Restaurants> arraylist;

    public ListViewAdapter(Context context,
                           List<Restaurants> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<Restaurants>();
        this.arraylist.addAll(restaurantList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.fragment_selectrestaurantitem, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(restaurantList.get(position).getName());



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),restaurantList.get(position).getName(), Toast.LENGTH_LONG ).show();
            }
        });
        return view;
    }


}
