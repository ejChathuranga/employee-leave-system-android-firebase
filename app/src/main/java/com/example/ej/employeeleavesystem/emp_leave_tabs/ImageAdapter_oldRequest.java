package com.example.ej.employeeleavesystem.emp_leave_tabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ej.employeeleavesystem.R;

import java.util.ArrayList;

/**
 * Created by ej on 1/24/2018.
 */

public class ImageAdapter_oldRequest extends BaseAdapter {
    private static final String TAG = "adadpter_old request";
    private Context context;
    private final ArrayList<GridItem_myOldLeave> gridItem;

    public ImageAdapter_oldRequest(Context context, ArrayList<GridItem_myOldLeave> gridItem) {
        this.context = context;
        this.gridItem = gridItem;

    }
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.layout_griedview_old_request_my, null);

            TextView textView1 = (TextView)gridView.findViewById(R.id.tvReason);
            textView1.setText(gridItem.get(position).listReason);

            TextView textView2 = gridView.findViewById(R.id.tvLeaveType);
            textView2.setText(gridItem.get(position).listType);

            TextView textView3 = gridView.findViewById(R.id.tvStartDate);
            textView3.setText(gridItem.get(position).listStartDate);

            TextView textView4 = gridView.findViewById(R.id.tvEndDate);
            textView4.setText(gridItem.get(position).listEndDate);

            TextView textView5 = gridView.findViewById(R.id.tvApprovedBy);
            String isApproved = gridItem.get(position).listApprove;
            String approvedBy = gridItem.get(position).listApprovedBy;
            Log.e(TAG,isApproved+"\n");

            if(isApproved.equals("NOT")){
//                Log.e(TAG,isApproved+"in the not approved section\n");
                textView5.setBackgroundResource(R.color.colorRed);
                textView5.setTextColor(R.color.colorWhite);
                textView5.setText("NOT APPROVED");
            }else if(isApproved.equals("REJECT")){
//                Log.e(TAG,isApproved+"in the not reject section\n");
                textView5.setBackgroundResource(R.color.colorReject);
                textView5.setTextColor(R.color.colorWhite);
                textView5.setText("REJECTED");
            }
            else{
//                Log.e(TAG,isApproved+" in the approved section\n");
                textView5.setBackgroundResource(R.color.colorGreen);
                textView5.setTextColor(R.color.colorWhite);
                textView5.setText("APPROVED BY: "+approvedBy);

            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
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
}
