package com.example.ej.employeeleavesystem.emp_leave_tabs;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.ej.employeeleavesystem.R;
import com.example.ej.employeeleavesystem.emp_leave_login;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


/**
 * Created by ej on 12/14/2017.
 */

public class emp_leave_home_approve extends Fragment {
    private String getInfoURL = "http://10.0.2.2/isuru_leave/leave_get_own_leave.php";
    private String sendURL = "http://10.0.2.2/isuru_leave/leave_add_approve.php";
    private static final String TAG = "emp_leave_home_approve";

    public SharedPreferences myPreferences;
    private GridView gridView;
    private Context context;

    private String requestID;
    private static String USER_NAME;
    private View view;
    private TextView refresh;

    public emp_leave_home_approve(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emp_leave_home_approved, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myPreferences = this.getActivity().getSharedPreferences(emp_leave_login.myPREF,0);
        USER_NAME = myPreferences.getString(emp_leave_login.PREF_USERNAME,"");
        gridView = view.findViewById(R.id.gridviewApprovedByme);
        refresh = view.findViewById(R.id.tvRefersh);

        context = this.getContext();
        loadGridView(view);
        this.view = view;

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView.invalidateViews();
                loadGridView(v);
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
            JSONArray all_list = jsonParser.getJSONFromUrl(getInfoURL);

            try {
                for (int i = 0; i < all_list.length(); i++) {
                    org.json.JSONObject list_item = all_list.getJSONObject(i);
                    String listIsApproved = list_item.getString("approve");
                    String listApprovedBy = list_item.getString("approvedBy");

                    if(USER_NAME.equals(listApprovedBy)){
                        String listFullName = list_item.getString("empID");
                            String listReason = list_item.getString("reason");
                            String listType = list_item.getString("leaveType");
                            String listStartDate = list_item.getString("startDate");
                            String listEndDate = list_item.getString("endDate");

                            items.add(new GridItem(listFullName, listReason, listType, listStartDate, listEndDate, listIsApproved));

                    }
                    // showMessg(listName);
                }
                //  Toast.makeText(this, listName,Toast.LENGTH_LONG);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            gridView.setAdapter(new ImageAdapter_approvedBy(this.getActivity(), items));


        }
    }
}
