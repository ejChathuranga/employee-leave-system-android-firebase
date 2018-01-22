package com.example.ej.employeeleavesystem.emp_leave_tabs;

/**
 * Created by ej on 1/21/2018.
 */

public class GridItem {
    public String listFullName;
    public String listReason;
    public String listType;
    public String listStartDate;
    public String listEndDate;

    public GridItem(String listFullName, String listReason, String listType, String listStartDate, String listEndDate) {
        this.listFullName = listFullName;
        this.listReason = listReason;
        this.listType = listType;
        this.listStartDate = listStartDate;
        this.listEndDate = listEndDate;

    }
}
