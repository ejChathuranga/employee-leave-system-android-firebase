package com.example.ej.employeeleavesystem;

/**
 * Created by ej on 1/3/2018.
 */

public class emp_leave_employee {
    String empId, etUsername, etPass, etFullname,spinnerRole, etNic, etEemail, etAddress, etMobile;


    public emp_leave_employee(){}

    public emp_leave_employee(String empId, String etUsername, String etPass) {
        this.empId = empId;
        this.etUsername = etUsername;
        this.etPass = etPass;
    }

    public emp_leave_employee(String empId, String etUsername, String etPass, String etFullname, String spinnerRole, String etNic, String etEemail, String etAddress, String etMobile) {
        this.empId = empId;
        this.etUsername = etUsername;
        this.etPass = etPass;
        this.etFullname = etFullname;
        this.spinnerRole = spinnerRole;
        this.etNic = etNic;
        this.etEemail = etEemail;
        this.etAddress = etAddress;
        this.etMobile = etMobile;

    }

    public String getEmpId() {
        return empId;
    }

    public String getEtUsername() {
        return etUsername;
    }

    public String getEtPass() {
        return etPass;
    }

    public String getEtFullname() {
        return etFullname;
    }

    public String getSpinnerRole() {
        return spinnerRole;
    }

    public String getEtNic() {
        return etNic;
    }

    public String getEtEemail() {
        return etEemail;
    }

    public String getEtAddress() {
        return etAddress;
    }

    public String getEtMobile() {
        return etMobile;
    }

}
