package com.example.ej.employeeleavesystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ej.employeeleavesystem.emp_leave_tabs.SectionPageAdapter;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_approve;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_lead_requests;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_new;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_old;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_my_approved_request;

public class emp_leave_home extends AppCompatActivity {
    private static final String  TAG = "emp_leave_home";


    private final static String USER_MANEGER = "Manager";
    private final static String USER_LEAD = "Lead";
    private final static String USER_EMPLOYEE = "Employee";
    private static String USER_ROLE = "";
    public SharedPreferences myPreferences;
    public FloatingActionButton fabLogout;

    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_leave_home);
        myPreferences = getSharedPreferences(emp_leave_login.myPREF,0);
        USER_ROLE = myPreferences.getString(emp_leave_login.PREF_ROLE,"");
        final SharedPreferences.Editor editor = myPreferences.edit();
        setTitle("Welcome "+USER_ROLE +": "+myPreferences.getString(emp_leave_login.PREF_USERNAME,""));

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        fabLogout = findViewById(R.id.fabLogout);
        setupViewPager(mViewPager);

        TabLayout tabLayout  = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(v.getContext());
                dialog.setTitle(R.string.alert_msg_logout_title);
                dialog.setMessage(R.string.alert_msg_logout);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.clear();
                        editor.commit();
                        Toast.makeText(emp_leave_home.this, "Logged out", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(emp_leave_home.this, emp_leave_login.class));
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

    private void setupViewPager(ViewPager mViewPager) {
//        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());

        switch (USER_ROLE){
            case USER_MANEGER:{
                mSectionPageAdapter.addFragment(new emp_leave_home_approve(), "Approved");
                mSectionPageAdapter.addFragment(new emp_leave_home_lead_requests(), "Request");
                break;
            }
            case USER_LEAD:{
                mSectionPageAdapter.addFragment(new emp_leave_home_approve(), "Approved");
                mSectionPageAdapter.addFragment(new emp_leave_home_new(), "New");
                mSectionPageAdapter.addFragment(new emp_leave_home_old(), "Old");
                mSectionPageAdapter.addFragment(new emp_leave_home_lead_requests(), "Request");
                break;
            }
            case USER_EMPLOYEE:{
                mSectionPageAdapter.addFragment(new emp_leave_home_old(), "Old");
                mSectionPageAdapter.addFragment(new emp_leave_home_new(), "New");
                mSectionPageAdapter.addFragment(new emp_leave_home_my_approved_request(), "Approved");
                break;
            }
        }
        mViewPager.setAdapter(mSectionPageAdapter);
    }
}