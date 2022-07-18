package com.android.square_employee_directory.model;

import java.util.List;

public class Employees {
    public List<EmployeeModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeModel> employees) {
        this.employees = employees;
    }

    private List<EmployeeModel> employees;

    public Employees(List<EmployeeModel> employees) {
        this.employees = employees;
    }


}
