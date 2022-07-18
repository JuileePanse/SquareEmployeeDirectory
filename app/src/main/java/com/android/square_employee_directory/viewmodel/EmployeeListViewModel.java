package com.android.square_employee_directory.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.square_employee_directory.model.EmployeeModel;
import com.android.square_employee_directory.model.Employees;
import com.android.square_employee_directory.network.APIService;
import com.android.square_employee_directory.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListViewModel extends ViewModel {
    private MutableLiveData<List<EmployeeModel>> employeeList;
    public EmployeeListViewModel() {
        employeeList = new MutableLiveData<>();
    }

    public MutableLiveData<List<EmployeeModel>> getEmployeeListObserver(){
        return employeeList;
    }
    public void apiCall()
    {
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<Employees> call = apiService.getEmployeeDirectory();
        call.enqueue(new Callback<Employees>() {
            @Override
            public void onResponse(Call<Employees> call, Response<Employees> response) {
                employeeList.postValue(response.body().getEmployees());
            }

            @Override
            public void onFailure(Call<Employees> call, Throwable t) {

            }
        });
    }
}
