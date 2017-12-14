package com.example.ej.employeeleavesystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Files;

public class emp_leave_login extends AppCompatActivity implements View.OnClickListener {

    private EditText empUsername, empPass;
    private TextView empForgotPass, empNewAcc;
    private Button empLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_leave_login);
        setTitle("Employee Login");

        empUsername = (EditText) findViewById(R.id.et_username);
        empPass = (EditText) findViewById(R.id.et_pass);
        empForgotPass = (TextView)findViewById(R.id.tv_forgot);
        empNewAcc = (TextView)findViewById(R.id.tv_newAccount);
        empLogin = (Button) findViewById(R.id.btn_login);

        empLogin.setOnClickListener(this);
        empNewAcc.setOnClickListener(this);
        empForgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:{
//                Toast.makeText(emp_leave_login.this, "Yeah", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(emp_leave_login.this, )

                break;
            }

            case R.id.tv_forgot:{
                Intent forgotIntent = new Intent(emp_leave_login.this, emp_leave_forgot.class);
                startActivity(forgotIntent);

                break;
            }

            case R.id.tv_newAccount:{
                Intent newAccIntent = new Intent(emp_leave_login.this, emp_leave_emp_regiter.class);
                startActivity(newAccIntent);
                break;
            }
        }
    }

}