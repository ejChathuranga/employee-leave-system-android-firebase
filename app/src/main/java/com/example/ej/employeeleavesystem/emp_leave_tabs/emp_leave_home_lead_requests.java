package com.example.ej.employeeleavesystem.emp_leave_tabs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ej.employeeleavesystem.R;
import com.example.ej.employeeleavesystem.emp_leave_login;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class emp_leave_home_lead_requests extends Fragment {

    private String sendURL = "http://10.0.2.2/isuru_leave/leave_request_list.php";

    public SharedPreferences myPreferences;
    private GridView gridView;
    private Context context;

    public emp_leave_home_lead_requests(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emp_leave_home_lead_requests, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myPreferences = this.getActivity().getSharedPreferences(emp_leave_login.myPREF,0);

        context = this.getContext();
        loadGridView(view);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(context);
                dialog.setTitle(R.string.alert_title_not_approved);
                dialog.setMessage(R.string.alert_msg_not_approved);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "UPDATED", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    private void loadGridView(View view) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
            ArrayList<GridItem> items = new ArrayList<>();


            Server_JSONParser jsonParser = new Server_JSONParser();
            JSONArray all_list = jsonParser.getJSONFromUrl(sendURL);

            try {
                for (int i = 0; i < all_list.length(); i++) {
                    org.json.JSONObject list_item = all_list.getJSONObject(i);
                    String listFullName = list_item.getString("empID");
                    String listReason = list_item.getString("reason");
                    String listType = list_item.getString("leaveType");
                    String listStartDate = list_item.getString("startDate");
                    String listEndDate = list_item.getString("endDate");
                    items.add(new GridItem(listFullName, listReason, listType, listStartDate, listEndDate));
                    // showMessg(listName);
                }
                //  Toast.makeText(this, listName,Toast.LENGTH_LONG);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            gridView = (GridView) view.findViewById(R.id.gridViewLeaveRequest);
            gridView.setAdapter(new ImageAdapter(this.getActivity(), items));


        }
    }
}
