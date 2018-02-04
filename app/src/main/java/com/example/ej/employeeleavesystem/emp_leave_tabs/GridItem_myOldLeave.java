package com.example.ej.employeeleavesystem.emp_leave_tabs;

/**
 * Created by ej on 1/24/2018.
 */

public class GridItem_myOldLeave {

    public String listFullName;
    public String listReason;
    public String listType;
    public String listStartDate;
    public String listEndDate;
    public String listApprovedBy;
    public String listApprove;

    public GridItem_myOldLeave(String listReason, String listType, String listStartDate, String listEndDate, String listApprove, String listApprovedBy) {
        this.listReason = listReason;
        this.listType = listType;
        this.listStartDate = listStartDate;
        this.listEndDate = listEndDate;
        this.listApprovedBy = listApprovedBy;
        this.listApprove = listApprove;

    }
}
