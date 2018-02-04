package com.example.ej.employeeleavesystem.emp_leave_tabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ej.employeeleavesystem.R;
import com.example.ej.employeeleavesystem.emp_leave_home;

/**
 * Created by ej on 1/30/2018.
 */

public class emp_leave_home_my_approved_request extends Fragment {

    public emp_leave_home_my_approved_request(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emp_leave_home_my_approved_leave, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), "Loading My Approved List", Toast.LENGTH_SHORT).show();
    }
}
