package com.example.ej.employeeleavesystem.emp_leave_tabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ej.employeeleavesystem.R;

import java.util.ArrayList;

/**
 * Created by ej on 1/25/2018.
 */

public class ImageAdapter_approvedBy extends BaseAdapter {

    private Context context;
    private final ArrayList<GridItem> gridItem;

    public ImageAdapter_approvedBy(Context context, ArrayList<GridItem> gridItem) {
        this.context = context;
        this.gridItem = gridItem;
    }

    @Override
    public int getCount() {
        int count=gridItem.size(); //counts the total number of elements from the arrayList
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.layout_gridview_approved_by_me, null);

            TextView textView = gridView.findViewById(R.id.tvUsername);
            textView.setText(gridItem.get(position).listFullName);

            TextView textView1 = (TextView)gridView.findViewById(R.id.tvReason);
            textView1.setText(gridItem.get(position).listReason);

            TextView textView2 = gridView.findViewById(R.id.tvLeaveType);
            textView2.setText(gridItem.get(position).listType);

            TextView textView3 = gridView.findViewById(R.id.tvStartDate);
            textView3.setText(gridItem.get(position).listStartDate);

            TextView textView4 = gridView.findViewById(R.id.tvEndDate);
            textView4.setText(gridItem.get(position).listEndDate);


//            // set value into textview
//            TextView textView1 = (TextView) gridView.findViewById(R.id.tvMissionName);
//            textView1.setText(gridItem.get(position).listName);
//
//            TextView textView2 = (TextView) gridView.findViewById(R.id.tvUnit);
//            textView2.setText(gridItem.get(position).listUnit);


        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
