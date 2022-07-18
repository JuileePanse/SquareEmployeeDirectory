package com.android.square_employee_directory.network;

import com.android.square_employee_directory.model.EmployeeModel;
import com.android.square_employee_directory.model.Employees;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("employees.json")
    Call <Employees> getEmployeeDirectory();
}
