package com.example.ej.employeeleavesystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.nio.file.Files;

public class emp_leave_emp_regiter extends AppCompatActivity {
    private Button btnReg;
    private EditText etUsername, etPass, etFullname, etNic, etEemail, etAddress, etMobile, etBirthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_leave_emp_regiter);
        setTitle("Employee Registration");

        btnReg = findViewById(R.id.btn_reg);
        etUsername = findViewById(R.id.et_username);
        etPass = findViewById(R.id.et_pass);
        etFullname = findViewById(R.id.et_fullName);
        etNic = findViewById(R.id.et_nic);
        etEemail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);
        etMobile = findViewById(R.id.et_mobileNo);
        etBirthdate = findViewById(R.id.et_bday);



    }
}