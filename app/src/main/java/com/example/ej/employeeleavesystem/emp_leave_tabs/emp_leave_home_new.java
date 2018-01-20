package com.example.ej.employeeleavesystem.emp_leave_tabs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ej.employeeleavesystem.R;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ej on 12/14/2017.
 */

public class emp_leave_home_new extends Fragment implements View.OnClickListener{
    private EditText etReason, etLeaveStartingDate, etLeaveEndDate;
    private Button btnSubmit;
    private TextView tvToday;
    private Spinner spinnerLeaveType;
    private Calendar myCalendar;
    private Date currentTime;

    private Context context;

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    public emp_leave_home_new(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emp_leave_home_new, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myCalendar = Calendar.getInstance();
        etReason = view.findViewById(R.id.et_leaveReason);
        etLeaveStartingDate = view.findViewById(R.id.et_leaveStartingDate);
        etLeaveEndDate = view.findViewById(R.id.et_leaveEndDate);
        spinnerLeaveType = view.findViewById(R.id.spinner_leavType);
        btnSubmit = view.findViewById(R.id.btn_submit);
        tvToday = view.findViewById(R.id.tv_today);
        context = getContext();

        setDate();

        etLeaveStartingDate.setOnClickListener(this);
        etLeaveEndDate.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_leaveStartingDate:{
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                break;
            }
            case R.id.et_leaveEndDate:{
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            }
            case R.id.btn_submit:{
                if(isEmpty(etReason) || isEmpty(etLeaveStartingDate) ||isEmpty(etLeaveEndDate)){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{

                }
            }
        }
    }
//
//    class addSoldierAsync extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            String reason = etReason.getText().toString();
//            String startingDate = etLeaveStartingDate.getText().toString();
//            String endDate = etLeaveEndDate.getText().toString();
//            String leaveType = spinnerLeaveType.getSelectedItem().toString();
//
//            // set data to send into the server
//            String dataJson = null;
//            try {
//                dataJson = URLEncoder.encode("reason", "UTF-8") + "="+ URLEncoder.encode(reason, "UTF-8");
//                dataJson += "&" + URLEncoder.encode("startingDate", "UTF-8") + "="+ URLEncoder.encode(startingDate, "UTF-8");
//                dataJson += "&" + URLEncoder.encode("endDate", "UTF-8") + "="+ URLEncoder.encode(endDate, "UTF-8");
//                dataJson += "&" + URLEncoder.encode("leaveType", "UTF-8") + "="+ URLEncoder.encode(leaveType, "UTF-8");
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//            // define URL where we send those data
//            URL url = null;
//            try {
//                url = new URL(sendURL);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//            String response = "";
//            BufferedReader bufferedReader=null;
//
//            try {
//                // Send POST data request
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setRequestMethod("POST");
////                OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
//                OutputStream outputStream = httpURLConnection.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
//                Log.e("Send JSON data", dataJson);
//
//                bufferedWriter.write( dataJson );
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();
//
//                // Get the server response
//                InputStream inputStream = httpURLConnection.getInputStream();
//
//                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
//                String line = "";
//
//                // Read Server Response
//                while((line = bufferedReader.readLine()) != null)
//                {
//                    // Append server response in string
//                    response+=line+"\n";
//                }
//
//                inputStream.close();
//                httpURLConnection.disconnect();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            finally {
//                try {
//                    bufferedReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            Log.e("JSONParseHTTP ",response);
//
//            return null;
//        }
//    }
//

    public boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private void setDate(){
        currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//formatting according to my need
        String date = formatter.format(currentTime);
        tvToday.setText("Today: "+date);

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etLeaveStartingDate.setText(sdf.format(myCalendar.getTime()));
    }

}
