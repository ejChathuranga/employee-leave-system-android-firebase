package com.example.ej.employeeleavesystem.emp_leave_tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ej.employeeleavesystem.R;


public class emp_leave_home_lead_requests extends Fragment {
    public emp_leave_home_lead_requests(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emp_leave_home_lead_requests, container, false);

        return rootView;
    }
}
