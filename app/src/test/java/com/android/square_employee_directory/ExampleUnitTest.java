package com.android.square_employee_directory;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import com.android.square_employee_directory.model.EmployeeModel;
import com.android.square_employee_directory.model.Employees;
import com.android.square_employee_directory.network.APIService;
import com.android.square_employee_directory.network.RetrofitInstance;

import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    List<EmployeeModel> employeeList;

    @Test
    public void checkResponseCode() throws IOException {
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<Employees> call = apiService.getEmployeeDirectory();
        assertEquals(200, call.execute().raw().code());
    }

    @Test
    public void checkName() throws IOException {
        getResponse();
        SoftAssert softAssert = new SoftAssert();

        for (EmployeeModel employeeModel : getResponse())
        {
            Pattern pattern = Pattern.compile("[a-zA-Z]+",Pattern.CASE_INSENSITIVE);
            Matcher matcher;
            if (employeeModel.getFull_name()!=null){
                matcher = pattern.matcher(employeeModel.getFull_name());
                softAssert.assertEquals(matcher.find(), true);
            }
        }
        softAssert.assertAll();
    }

    @Test
    public void checkRequiredFieldsUUID() {
        for (EmployeeModel employeeModel : employeeList)
        {
            if (employeeModel.getUuid()==null){
                Assert.fail();
            }
        }
    }

    @Test
    public void checkRequiredFieldsName() {
        for (EmployeeModel employeeModel : employeeList)
        {
            if (employeeModel.getFull_name()==null){
                Assert.fail();
            }
        }
    }

    public List<EmployeeModel> getResponse() throws IOException {
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<Employees> call = apiService.getEmployeeDirectory();
        employeeList =  call.execute().body().getEmployees();
        return employeeList;
    }
}