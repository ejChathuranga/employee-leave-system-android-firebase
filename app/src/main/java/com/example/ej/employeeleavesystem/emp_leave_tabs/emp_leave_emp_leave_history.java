package com.example.ej.employeeleavesystem.emp_leave_tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.ej.employeeleavesystem.R;
import com.example.ej.employeeleavesystem.emp_leave_login;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class emp_leave_emp_leave_history extends AppCompatActivity {

    private static final String TAG = "emp_leave_history" ;
    private String getInfoURL = "http://10.0.2.2/isuru_leave/leave_request_list.php";

    private SharedPreferences myPreferences;
    private static String USER_NAME;
    private Context context;
    private View view;
    private GridView gridView;
    private Bundle extra;
    private String empID;
    private TextView tvEmpID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_leave_emp_leave_history);

        myPreferences = this.getSharedPreferences(emp_leave_login.myPREF,0);
        USER_NAME = myPreferences.getString(emp_leave_login.PREF_USERNAME,"");
        extra = getIntent().getExtras();
        tvEmpID = findViewById(R.id.tvEmpHistory);

        context = getBaseContext();
        loadGridView();
    }

    private void loadGridView() {
        empID = extra.getString("empID");
        tvEmpID.setText("History of "+empID);
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
                    if(empID.equals(listFullName)){
                        String approvedBy = list_item.getString("approvedBy");
                        Log.e(TAG, approvedBy);
                        String listReason = list_item.getString("reason");
                        String listType = list_item.getString("leaveType");
                        String listStartDate = list_item.getString("startDate");
                        String listEndDate = list_item.getString("endDate");
                        String approve = list_item.getString("approve");
                        items.add(new GridItem_myOldLeave(listReason, listType, listStartDate, listEndDate, approve,  approvedBy));

                    }
                    // showMessg(listName);
                }
                //  Toast.makeText(this, listName,Toast.LENGTH_LONG);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            gridView = (GridView) findViewById(R.id.gridViewEmpHistory);
            gridView.setAdapter(new ImageAdapter_oldRequest(this, items));


        }
    }
}
