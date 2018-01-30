package com.example.ej.employeeleavesystem.emp_leave_tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.ej.employeeleavesystem.R;
import com.example.ej.employeeleavesystem.emp_leave_login;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by ej on 12/14/2017.
 */

public class emp_leave_home_old extends Fragment {
    private final String TAG = "OLD REQUEST OF MINE";
    private String getInfoURL = "http://10.0.2.2/isuru_leave/leave_get_own_leave.php";

    private Context context;
    private SharedPreferences myPreferences;
    private View view;
    private String USER_NAME;
    private GridView gridView;
    public View view1;

    public emp_leave_home_old(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emp_leave_home_old, container, false);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myPreferences = this.getActivity().getSharedPreferences(emp_leave_login.myPREF,0);
        USER_NAME = myPreferences.getString(emp_leave_login.PREF_USERNAME,"");


        context = this.getContext();
        loadGridView(view);
        this.view = view;

    }

    private void loadGridView(View view) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        Log.e(TAG,"Running....");
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
            ArrayList<GridItem_myOldLeave> items = new ArrayList<>();


            Server_JSONParser jsonParser = new Server_JSONParser();
            JSONArray all_list = jsonParser.getJSONFromUrl(getInfoURL);

            try {
                for (int i = 0; i < all_list.length(); i++) {
                    org.json.JSONObject list_item = all_list.getJSONObject(i);
                    String listFullName = list_item.getString("empID");
                    if(USER_NAME.equals(listFullName)){
                        String approvedBy = list_item.getString("approvedBy");
                            String listReason = list_item.getString("reason");
                            String listType = list_item.getString("leaveType");
                            String listStartDate = list_item.getString("startDate");
                            String listEndDate = list_item.getString("endDate");
                            items.add(new GridItem_myOldLeave(listReason, listType, listStartDate, listEndDate, approvedBy));

                    }
                    // showMessg(listName);
                }
                //  Toast.makeText(this, listName,Toast.LENGTH_LONG);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            gridView = (GridView) view.findViewById(R.id.gridviewOldLeave);
            gridView.setAdapter(new ImageAdapter_oldRequest(this.getActivity(), items));


        }
    }

}
