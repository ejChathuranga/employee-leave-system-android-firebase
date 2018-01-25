package com.example.ej.employeeleavesystem.emp_leave_tabs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ej.employeeleavesystem.R;
import com.example.ej.employeeleavesystem.emp_leave_login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class emp_leave_home_lead_requests extends Fragment {

    private String getInfoURL = "http://10.0.2.2/isuru_leave/leave_request_list.php";
    private String sendURL = "http://10.0.2.2/isuru_leave/leave_add_approve.php";

    public SharedPreferences myPreferences;
    private GridView gridView;
    private Context context;

    private String requestID;
    private static String USER_NAME;
    private View view;

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
        USER_NAME = myPreferences.getString(emp_leave_login.PREF_USERNAME,"");

        context = this.getContext();
        loadGridView(view);
        this.view = view;

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                requestID =((TextView) view.findViewById(R.id.tvRequestID)).getText().toString();

                AlertDialog.Builder dialog= new AlertDialog.Builder(context);
                dialog.setTitle(R.string.alert_title_not_approved);
                dialog.setMessage(R.string.alert_msg_not_approved);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new addApproveForRequests().execute();
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
            JSONArray all_list = jsonParser.getJSONFromUrl(getInfoURL);

            try {
                for (int i = 0; i < all_list.length(); i++) {
                    org.json.JSONObject list_item = all_list.getJSONObject(i);
                    String listFullName = list_item.getString("empID");
                    if (!USER_NAME.equals(listFullName)) {
                        String listReason = list_item.getString("reason");
                        String listType = list_item.getString("leaveType");
                        String listStartDate = list_item.getString("startDate");
                        String listEndDate = list_item.getString("endDate");
                        String listRequestID = list_item.getString("id");
                        items.add(new GridItem(listFullName, listReason, listType, listStartDate, listEndDate, listRequestID));

                    }
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

    class addApproveForRequests extends AsyncTask<String, String, String> {


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Object obj = JSONValue.parse(s);
            JSONObject jsonObject = (JSONObject) obj;

            if((Boolean) jsonObject.get("success")) {
                Toast.makeText(context, "Successfully Approved", Toast.LENGTH_SHORT).show();
                loadGridView(view);
            }

        }

        @Override
        protected String doInBackground(String... strings) {

            String response = "";
            BufferedReader bufferedReader=null;
            URL url = null;

            // set data to send into the server
            String dataJson = null;
            try {
                dataJson = URLEncoder.encode("adminName", "UTF-8") + "="+ URLEncoder.encode(USER_NAME, "UTF-8");
                dataJson += "&" + URLEncoder.encode("id", "UTF-8") + "="+ URLEncoder.encode(requestID, "UTF-8");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // define URL where we send those data

            try {
                url = new URL(sendURL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                // Send POST data request
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
//                OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                Log.e("Send JSON data", dataJson);

                bufferedWriter.write( dataJson );
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                // Get the server response
                InputStream inputStream = httpURLConnection.getInputStream();

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line = "";

                // Read Server Response
                while((line = bufferedReader.readLine()) != null)
                {
                    // Append server response in string
                    response+=line+"\n";
                }

                inputStream.close();
                httpURLConnection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.e("JSONParseHTTP ",response);

            return response;
        }
    }
}
