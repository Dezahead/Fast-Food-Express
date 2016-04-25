package com.dcv3.fastfood.fastfoodexpress.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dcv3.fastfood.fastfoodexpress.MainActivity;
import com.dcv3.fastfood.fastfoodexpress.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dezereljones on 11/17/15.
 */
public class OrderSummaryFragment extends Fragment {

    ArrayList<String> menuItems;

    public OrderSummaryFragment(){

    }

    /*
        ....the summary page is in scroll view
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ordersummary, container, false);

        menuItems =getArguments().getStringArrayList("items");

        //Toast.makeText(getActivity(), menuItems.size().t, Toast.LENGTH_LONG).show();

        final ListView listview = (ListView)rootView.findViewById(R.id.list);

        //instantiate custom adapter
        MyCustomAdapter adapter = new MyCustomAdapter(menuItems, getActivity());

        listview.setAdapter(adapter);


        return rootView;
    }

    public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
        private ArrayList<String> list = new ArrayList<String>();
        private Context context;



        public MyCustomAdapter(ArrayList<String> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int pos) {
            return list.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return 0;
            //just return 0 if your list items do not have an Id variable.
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.summarylist_layout, null);
            }

            //Handle TextView and display string from your list
            TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
            listItemText.setText(list.get(position));

            //Handle buttons and add onClickListeners
            Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

            deleteBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //do something
                    ((MainActivity)getActivity()).deleteItems(position);
                    notifyDataSetChanged();
                }
            });
            return view;
        }
    }
}
