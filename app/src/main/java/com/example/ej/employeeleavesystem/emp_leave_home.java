package com.example.ej.employeeleavesystem;

import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.example.ej.employeeleavesystem.emp_leave_tabs.SectionPageAdapter;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_approve;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_lead_requests;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_new;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_old;

public class emp_leave_home extends AppCompatActivity {
    private static final String  TAG = "emp_leave_home";


    private final static String USER_MANEGER = "Manager";
    private final static String USER_LEAD = "Lead";
    private final static String USER_EMPLOYEE = "Employee";
    private static String USER_ROLE = "";
    public SharedPreferences myPreferences;

    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_leave_home);
        Log.d("TAG","onCreate: Loading..");
        myPreferences = getSharedPreferences(emp_leave_login.myPREF,0);
        USER_ROLE = myPreferences.getString(emp_leave_login.PREF_ROLE,"");

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout  = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {
//        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());

        switch (USER_ROLE){
            case USER_MANEGER:{

                break;
            }
            case USER_LEAD:{
                break;
            }
            case USER_EMPLOYEE:{
                break;
            }
        }

        mSectionPageAdapter.addFragment(new emp_leave_home_approve(), "Approved");
        mSectionPageAdapter.addFragment(new emp_leave_home_new(), "New");
        mSectionPageAdapter.addFragment(new emp_leave_home_old(), "Old");
        mSectionPageAdapter.addFragment(new emp_leave_home_lead_requests(), "Request");
        mViewPager.setAdapter(mSectionPageAdapter);
    }
}