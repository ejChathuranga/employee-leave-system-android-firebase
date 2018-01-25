package com.example.ej.employeeleavesystem;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ej.employeeleavesystem.emp_leave_tabs.Server_JSONParser;

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

import static android.widget.AdapterView.*;


public class emp_leave_emp_regiter extends AppCompatActivity implements View.OnClickListener {

    private Button btnReg;
    private EditText etUsername, etPass, etFullname, etNic, etEemail, etAddress, etMobile, etTeam;
    private Spinner spinnerRole;

    private static final String NEW_ROLE="Manager";

    private String sendURL = "http://10.0.2.2/isuru_leave/leave_create_user.php";
    private String getInfoURL = "http://10.0.2.2/isuru_leave/leave_create_user_get_role.php";

    private String username ,pass ,fullname ,role, nic, email ,address, mobile, team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_leave_emp_regiter);
        setTitle("Employee Registration");

        btnReg = findViewById(R.id.btn_reg);
        etUsername = findViewById(R.id.et_username);
        etPass = findViewById(R.id.et_pass);
        etFullname = findViewById(R.id.et_fullName);
        spinnerRole = findViewById(R.id.spinner_role);
        etTeam = findViewById(R.id.et_team);
        etNic = findViewById(R.id.et_nic);
        etEemail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);
        etMobile = findViewById(R.id.et_mobileNo);


        btnReg.setOnClickListener(this);
        spinnerRole.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String role = spinnerRole.getSelectedItem().toString();
                if(NEW_ROLE.equals(role))
                    new checkRole().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reg: {
                addEmployee();
                break;
            }
            case R.id.spinner_role:{
                Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addEmployee(){
        username = etUsername.getText().toString().trim();
        pass = etPass.getText().toString().trim();
        fullname = etFullname.getText().toString().trim();
        role = spinnerRole.getSelectedItem().toString();
        team = etTeam.getText().toString();
        nic = etNic.getText().toString().trim();
        email = etEemail.getText().toString();
        address = etAddress.getText().toString();
        mobile = etMobile.getText().toString();

        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pass)&& !TextUtils.isEmpty(role)&& !TextUtils.isEmpty(email)&& !TextUtils.isEmpty(fullname)&& !TextUtils.isEmpty(nic)&& !TextUtils.isEmpty(address)&& !TextUtils.isEmpty(mobile)){

            new addSoldierAsync().execute();

        }else{
            Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
        }


    }

    class checkRole extends AsyncTask<Void, Void, Boolean> {

        Boolean CAN = true;

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);
            if(!CAN){
                TextView errorText = (TextView)spinnerRole.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("You can't register as MANAGER");
            }

        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            Server_JSONParser jsonParser = new Server_JSONParser();
            JSONArray all_list = jsonParser.getJSONFromUrl(getInfoURL);

            try {
                for (int i = 0; i < all_list.length(); i++) {
                    org.json.JSONObject list_item = all_list.getJSONObject(i);
                    String Rrole = list_item.getString("role");
                    if(NEW_ROLE.equals(Rrole)){
                        CAN = false;
                        break;
                    }
                }
                //  Toast.makeText(this, listName,Toast.LENGTH_LONG);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("--- REG ROLE ----",""+CAN);
            return CAN;
        }
    }


    class addSoldierAsync extends AsyncTask<String, String, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Object obj = JSONValue.parse(s);
            JSONObject jsonObject = (JSONObject) obj;

            Boolean success = (Boolean) jsonObject.get("success");
            Boolean isAvailable = (Boolean) jsonObject.get("isAvailable");

            if(isAvailable){
                if(success){
                    etNic.setText("");
                    etUsername.setText("");
                    etFullname.setText("");
                    etTeam.setText("");
                    etEemail.setText("");
                    etAddress.setText("");
                    etMobile.setText("");
                    etPass.setText("");
                }else {
                    Toast.makeText(emp_leave_emp_regiter.this, "Please check the network connection", Toast.LENGTH_SHORT).show();
                }
            }else{
                etUsername.setError("Username not available");
                Toast.makeText(emp_leave_emp_regiter.this, "User name is not available", Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        protected String doInBackground(String... strings) {


            // set data to send into the server
            String dataJson = null;
            try {
                dataJson = URLEncoder.encode("username", "UTF-8") + "="+ URLEncoder.encode(username, "UTF-8");
                dataJson += "&" + URLEncoder.encode("pass", "UTF-8") + "="+ URLEncoder.encode(pass, "UTF-8");
                dataJson += "&" + URLEncoder.encode("name", "UTF-8") + "="+ URLEncoder.encode(fullname, "UTF-8");
                dataJson += "&" + URLEncoder.encode("role", "UTF-8") + "="+ URLEncoder.encode(role, "UTF-8");
                dataJson += "&" + URLEncoder.encode("nic", "UTF-8") + "="+ URLEncoder.encode(nic, "UTF-8");
                dataJson += "&" + URLEncoder.encode("email", "UTF-8") + "="+ URLEncoder.encode(email, "UTF-8");
                dataJson += "&" + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8");
                dataJson += "&" + URLEncoder.encode("teamName", "UTF-8") + "=" + URLEncoder.encode(team, "UTF-8");
                dataJson += "&" + URLEncoder.encode("mobileNo", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // define URL where we send those data
            URL url = null;
            try {
                url = new URL(sendURL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String response = "";
            BufferedReader bufferedReader=null;

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