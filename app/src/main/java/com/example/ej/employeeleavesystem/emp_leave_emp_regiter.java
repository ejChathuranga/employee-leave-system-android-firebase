package com.example.ej.employeeleavesystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.file.Files;
import java.util.Date;

public class emp_leave_emp_regiter extends AppCompatActivity implements View.OnClickListener {
    private Button btnReg;
    private EditText etUsername, etPass, etFullname, etNic, etEemail, etAddress, etMobile;
    private Spinner spinnerRole;

    DatabaseReference databaseEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_leave_emp_regiter);
        setTitle("Employee Registration");

        databaseEmployee = FirebaseDatabase.getInstance().getReference("employees");

        btnReg = findViewById(R.id.btn_reg);
        etUsername = findViewById(R.id.et_username);
        etPass = findViewById(R.id.et_pass);
        etFullname = findViewById(R.id.et_fullName);
        spinnerRole = findViewById(R.id.spinner_role);
        etNic = findViewById(R.id.et_nic);
        etEemail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);
        etMobile = findViewById(R.id.et_mobileNo);


        btnReg.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reg: {
                addEmployee();
            }
        }
    }

    private void addEmployee(){
        String username = etUsername.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        String fullname = etFullname.getText().toString().trim();
        String role = spinnerRole.getSelectedItem().toString();
        String nic = etNic.getText().toString().trim();
        String email = etEemail.getText().toString();
        String address = etAddress.getText().toString();
        String mobile = etMobile.getText().toString();

        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pass)&& !TextUtils.isEmpty(role)&& !TextUtils.isEmpty(email)&& !TextUtils.isEmpty(fullname)&& !TextUtils.isEmpty(nic)&& !TextUtils.isEmpty(address)&& !TextUtils.isEmpty(mobile)){
            String id = databaseEmployee.push().getKey();

//            Log.e("emp_leave_emp_reg"," "+id+" "+username+" "+pass+" "+fullname+" "+role+" "+nic+" "+email+" "+address+" "+mobile);
            emp_leave_employee employee = new emp_leave_employee(id, username, pass, fullname, role, nic, email, address, mobile);

            databaseEmployee.child(id).setValue(employee);

            Toast.makeText(this, "Successfully added into database", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
        }


    }
}