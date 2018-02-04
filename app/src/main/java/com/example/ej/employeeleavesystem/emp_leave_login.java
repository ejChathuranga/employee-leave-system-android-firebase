package com.example.ej.employeeleavesystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.nio.file.Files;

public class emp_leave_login extends AppCompatActivity implements View.OnClickListener {

    private EditText empUsername, empPass;
    private TextView empForgotPass, empNewAcc;
    private Button empLogin;

    private String sendURL = "http://10.0.2.2/isuru_leave/leave_login_user.php";


    public static final String myPREF = "myPreferences";
    public static final String PREF_NAME = "myName";
    public static final String PREF_USERNAME = "myUserName";
    public static final String PREF_ROLE = "myRole";
    public static SharedPreferences myPreferences;
    public static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_leave_login);
        setTitle("Employee Login");

        empUsername = (EditText) findViewById(R.id.et_username);
        empPass = (EditText) findViewById(R.id.et_pass);
        empForgotPass = (TextView)findViewById(R.id.tv_forgot);
        empNewAcc = (TextView)findViewById(R.id.tv_firstNewAccount);
        empLogin = (Button) findViewById(R.id.btn_login);

        myPreferences = getSharedPreferences(myPREF, 0);
        editor = myPreferences.edit();

        empLogin.setOnClickListener(this);
        empNewAcc.setOnClickListener(this);
        empForgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:{
                if(!isEmpty(empUsername)|| !isEmpty(empPass)){
                    new addSoldierAsync().execute();
//                    startActivity(new Intent(emp_leave_login.this, emp_leave_home.class));

                }else{
                    empUsername.setError("Please fill-out");
                    empPass.setError("Please fill-out");
                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.tv_forgot:{
                startActivity(new Intent(emp_leave_login.this, emp_leave_forgot.class));
                break;
            }
            case R.id.tv_firstNewAccount:{
                startActivity( new Intent(emp_leave_login.this, emp_leave_emp_regiter.class));
                break;
            }
        }
    }

    class addSoldierAsync extends AsyncTask<String, String, String> {

        String username = empUsername.getText().toString();
        String pass = empPass.getText().toString();

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.length()==0){
                Toast.makeText(emp_leave_login.this, "Please enter valid details", Toast.LENGTH_SHORT).show();
            }else{
                Object obj = JSONValue.parse(s);
                JSONObject jsonObject = (JSONObject) obj;

                Boolean success = (Boolean) jsonObject.get("success");
                String name = (String) jsonObject.get("name");
                String role = (String) jsonObject.get("role");

                editor.putString(PREF_USERNAME,username);
                editor.putString(PREF_NAME,name);
                editor.putString(PREF_ROLE,role);
                editor.commit();

                if(success){
                    startActivity(new Intent(emp_leave_login.this, emp_leave_home.class));
                    Toast.makeText(emp_leave_login.this, "Successfully logged", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(emp_leave_login.this, "Please check network connection", Toast.LENGTH_SHORT).show();
                }

            }



        }

        @Override
        protected String doInBackground(String... strings) {


            // set data to send into the server
            String dataJson = null;
            try {
                dataJson = URLEncoder.encode("username", "UTF-8") + "="+ URLEncoder.encode(username, "UTF-8");
                dataJson += "&" + URLEncoder.encode("pass", "UTF-8") + "="+ URLEncoder.encode(pass, "UTF-8");

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

    private Boolean isEmpty(EditText editText){
        return editText.getText().toString().trim().length()==0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        empUsername.setText("");
        empPass.setText("");
    }
}
